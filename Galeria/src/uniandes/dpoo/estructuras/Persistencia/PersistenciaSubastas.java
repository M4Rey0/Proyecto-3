package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.estructuras.Inventarios.InventarioSubastas;
import uniandes.dpoo.estructuras.model.Subasta;

public class PersistenciaSubastas {
    private static final String FILE_PATH = "data/Subastas/";

    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarSubasta(Subasta subasta) throws Exception {
        String path = FILE_PATH + subasta.getId() + ".txt";
        String contenido = subasta.toString();
        String clase = "Subasta";
        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public Subasta cargarSubasta(String texto) {
        String[] partes = texto.split(",");
        Subasta subasta = new Subasta();
        subasta.setId(Integer.parseInt(partes[0]));

        String[] piezas = partes[1].split("-");
        ArrayList<Integer> piezasSubastadas = new ArrayList<>();
        for (String pieza : piezas) {
            if (!pieza.equals("")) {
                piezasSubastadas.add(Integer.parseInt(pieza));
            }
        }
        subasta.setPiezas(piezasSubastadas);

        String[] compradores = partes[2].split("-");
        ArrayList<Integer> compradoresSubastas = new ArrayList<>();
        for (String comprador : compradores) {
            if (!comprador.equals("")) {
                compradoresSubastas.add(Integer.parseInt(comprador));
            }
        }

        subasta.setCompradores(compradoresSubastas);

        String[] valoresMinimos = partes[3].split("-");
        HashMap<Integer, Integer> valoresMinimosSubastas = new HashMap<>();
        for (int i = 0; i < valoresMinimos.length; i++) {
            String[] llaveValor = valoresMinimos[i].split(";");
            valoresMinimosSubastas.put(Integer.parseInt(llaveValor[0]), Integer.parseInt(llaveValor[1]));
        }

        subasta.setValoresMinimos(valoresMinimosSubastas);

        String[] valoresIniciales = partes[4].split("-");
        HashMap<Integer, Integer> valoresInicialesSubastas = new HashMap<>();
        for (int i = 0; i < valoresIniciales.length; i++) {
            if (!valoresIniciales[i].equals("")) {
                String[] llaveValor = valoresIniciales[i].split(";");
                if (llaveValor.length > 1){
                    valoresInicialesSubastas.put(Integer.parseInt(llaveValor[0]), Integer.parseInt(llaveValor[1]));
                }
            }
        }
        subasta.setValoresIniciales(valoresInicialesSubastas);

        return subasta;
    }

    public void eliminarSubasta(Subasta subasta) throws Exception {
        String path = FILE_PATH + subasta.getId() + ".txt";
        persistenciaUtils.eliminarElemento(path, "Subasta", Integer.toString(subasta.getId()));
    }

    public InventarioSubastas cargarSubastas(InventarioSubastas inventarioSubastas) throws RuntimeException {
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Subasta subasta = cargarSubasta(contenido);
                        inventarioSubastas.agregarSubasta(subasta);
                    }
                }
            }

            return inventarioSubastas;

        } catch (Exception e) {
            System.out.println("Error al cargar las subastas: " + e.getMessage());
            throw new RuntimeException("Error al cargar las subastas");
        }
    }

}
