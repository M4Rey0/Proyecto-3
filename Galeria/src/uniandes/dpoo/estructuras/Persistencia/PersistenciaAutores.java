package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import uniandes.dpoo.estructuras.Inventarios.InventarioAutores;
import uniandes.dpoo.estructuras.model.Autor;




public class PersistenciaAutores {
    private static final String FILE_PATH = "data/Autores/";
    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarAutor(Autor autor) throws Exception {

        String contenido = autor.toString();
        String clase = "Autor";
        String path = FILE_PATH + autor.getNombre() + ".txt";

        persistenciaUtils.guardarElemento(path, contenido, clase);
        
    }

    public Autor cargarAutor(String texto){
        String[] partes = texto.split(",");
        Autor autor = new Autor(partes[0], partes[1]);
        return autor;
    }

    public void eliminarAutor(Autor autor) throws Exception {
        if (autor.getNombre().equals("Anónimo")){
            throw new Exception("No se puede eliminar el autor");
        } else {
            String path = FILE_PATH + autor.getNombre() + ".txt";
            persistenciaUtils.eliminarElemento(path, "Autor", autor.getNombre());
        }
    }

    public InventarioAutores cargarAutores(InventarioAutores inventarioAutores) throws RuntimeException {
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
             if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Autor autor = cargarAutor(contenido);
                        inventarioAutores.agregarAutor(autor);
                    }
                }
            }
            return inventarioAutores;
        } catch (IOException e) {
            System.out.println("Error al cargar los autores: " + e.getMessage());
            throw new RuntimeException("Error al cargar los autores");
        }
    }

}
