package uniandes.dpoo.estructuras.Inventarios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.RangoFechas;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Audiovisual;

public class FactoryPiezas {

    private static final String ESCULTURA = "Escultura";
    private static final String OBRA_EN_PAPEL = "ObraEnPapel";
    private static final String AUDIOVISUAL = "Audiovisual";

    public Pieza generarPieza(String contenido) {
        String[] partes = contenido.split(",");
        Pieza pieza = new Pieza();
        pieza.setId(Integer.parseInt(partes[0]));
        pieza.setTitulo(partes[1]);
        pieza.setAnio(Integer.parseInt(partes[2]));
        pieza.setLugarCreacion(partes[3]);
        pieza.setValor(Integer.parseInt(partes[4]));
        pieza.setDisponible(Boolean.parseBoolean(partes[5]));
        pieza.setEstado(partes[6]);
        ArrayList<Integer> compras = new ArrayList<>();
        String[] comprasArray = partes[7].split("-");
        for (String compra : comprasArray) {
            if (!compra.equals("")) {
                compras.add(Integer.parseInt(compra));
            }
        }
        pieza.setCompras(compras);
        ArrayList<String> autores = new ArrayList<>();
        String[] autoresArray = partes[8].split("-");
        for (String autor : autoresArray) {
            if (!autor.equals("")) {
                autores.add(autor);
            }
        }
        pieza.setAutores(autores);

        String[] propietariosArray = partes[9].split("/");
        HashMap<RangoFechas, Integer> propietarios = new HashMap<>();

        for (String propietario : propietariosArray) {
            if (!propietario.equals("")) {
                String[] propietarioArray = propietario.split(";");
                String[] fechas = propietarioArray[1].split("_");
                LocalDate fechaInicial = null; 
                LocalDate fechaFinal = null;
                try{
                    fechaInicial = LocalDate.parse(fechas[0]);
                    fechaFinal = LocalDate.parse(fechas[1]);
                } catch (Exception e) {

                    if (fechas.length ==1){
                        fechaInicial = LocalDate.parse(fechas[0]);
                        fechaFinal = null;
                    }

                    System.out.println("Error al parsear la fecha");
                }
                RangoFechas rango = new RangoFechas(fechaInicial, fechaFinal);
                propietarios.put(rango, Integer.parseInt(propietarioArray[0]));
            }
        }

        pieza.setPropietarios(propietarios);

        switch (partes[10]) {
            case ESCULTURA:
                pieza = new Escultura(pieza, Integer.parseInt(partes[11]), Integer.parseInt(partes[12]),
                        Integer.parseInt(partes[13]), partes[14], Boolean.parseBoolean(partes[15]));
                break;
            case OBRA_EN_PAPEL:
                pieza = new ObraEnPapel(pieza, partes[11], partes[12], partes[13]);
                break;
            case AUDIOVISUAL:
                pieza = new Audiovisual(pieza, Integer.parseInt(partes[11]), partes[12]);
                break;
        }

        return pieza;
    }
}
