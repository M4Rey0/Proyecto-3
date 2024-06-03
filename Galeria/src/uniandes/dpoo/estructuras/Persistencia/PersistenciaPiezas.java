package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.nio.file.Files;

import uniandes.dpoo.estructuras.Inventarios.FactoryPiezas;
import uniandes.dpoo.estructuras.Inventarios.InventarioPiezas;
import uniandes.dpoo.estructuras.model.Audiovisual;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Pieza;

public class PersistenciaPiezas {
    private static final String FILE_PATH = "data/Piezas/";
    private FactoryPiezas factoryPiezas = new FactoryPiezas();
    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarPieza(Pieza pieza) throws Exception {
        String path = FILE_PATH + pieza.getId() + ".txt";
        String contenido = getContenidoPieza(pieza); 
        String clase = "Pieza";

        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public String getContenidoPieza(Pieza pieza) {
        if (pieza instanceof Audiovisual) {
            return ((Audiovisual) pieza).toString();
        } else if (pieza instanceof Escultura) {
            return ((Escultura) pieza).toString();
        } else if (pieza instanceof ObraEnPapel) {
            return ((ObraEnPapel) pieza).toString();
        } else {
            return "";
        }
    }

    public void eliminarPieza(Pieza pieza) throws Exception {
        String path = FILE_PATH + pieza.getId() + ".txt";
        persistenciaUtils.eliminarElemento(path, "Pieza", Integer.toString(pieza.getId()));
    }

    public Pieza cargarPieza(String texto) {
        return factoryPiezas.generarPieza(texto);
    }

    public InventarioPiezas cargarPiezas(InventarioPiezas inventarioPiezas)throws RuntimeException{
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Pieza pieza = cargarPieza(contenido);
                        inventarioPiezas.agregarPieza(pieza);
                    }
                }
            }

            return inventarioPiezas;
        } catch (Exception e) {
            System.out.println("Error al cargar los propietarios: " + e.getMessage());
            throw new RuntimeException("Error al cargar los propietarios");
        }
    }

    
}
