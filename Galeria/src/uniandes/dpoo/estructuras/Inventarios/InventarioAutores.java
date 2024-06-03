package uniandes.dpoo.estructuras.Inventarios;

import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.estructuras.model.Autor;

public class InventarioAutores {

    private Map<String, Autor> autores = new HashMap<>();

    public void agregarAutor(Autor autor) {
        autores.put(autor.getNombre(), autor);
    }

    public void quitarAutor(Autor autor) throws Exception {
        if (!autores.containsKey(autor.getNombre())) {
            throw new Exception("El autor no se encuentra en el inventario");
        } else {
            autores.remove(autor.getNombre());
        }
    }

    public Autor buscarAutorPorNombre(String nombre) {
        return autores.get(nombre);
    }
    
}
