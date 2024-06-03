package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import uniandes.dpoo.estructuras.Inventarios.InventarioUsuarios;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Usuario;

public class PersistenciaCompradores {
    private static final String FILE_PATH = "data/Compradores/";

    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarComprador(Comprador comprador) throws Exception {
        String path = FILE_PATH + comprador.getId() + ".txt";
        String contenido = comprador.toString();
        String clase = "Comprador";

        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public Comprador cargarComprador(String texto, InventarioUsuarios inventarioUsuarios) {
        String[] partes = texto.split(",");
        Usuario usuarioBase = inventarioUsuarios.buscarUsuarioPorId(Integer.parseInt(partes[0]));
        ArrayList<Integer> piezasCompradas = new ArrayList<>();
        if (partes.length > 3){
            String[] piezas = partes[3].split("-");
            for (String pieza : piezas) {
                if (!pieza.equals("")) {
                    piezasCompradas.add(Integer.parseInt(pieza));
                }
            }
        }
        Comprador comprador = new Comprador(usuarioBase, Integer.parseInt(partes[1]), Boolean.parseBoolean(partes[2]), piezasCompradas);
        return comprador;
    }

    public void eliminarComprador(Comprador comprador) throws Exception {
        String path = FILE_PATH + comprador.getId() + ".txt";
        persistenciaUtils.eliminarElemento(path, "Comprador", Integer.toString(comprador.getId()));
    }

    public InventarioUsuarios cargarCompradores(InventarioUsuarios inventarioUsuarios) throws RuntimeException {
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Comprador comprador = cargarComprador(contenido, inventarioUsuarios);
                        inventarioUsuarios.agregarComprador(comprador);
                    }
                }
            }
            return inventarioUsuarios;
        } catch (IOException e) {
            System.out.println("Error al cargar los compradores: " + e.getMessage());
            throw new RuntimeException("Error al cargar los compradores");
        }
    }

}
