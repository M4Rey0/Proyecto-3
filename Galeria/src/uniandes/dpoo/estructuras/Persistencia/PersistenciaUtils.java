package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class PersistenciaUtils {
    public void guardarElemento (String path, String contenido, String clase) throws Exception{
        try (PrintWriter writer = new PrintWriter(new FileWriter(path))) {
            writer.println(contenido);
        } catch (IOException e) {
            System.out.println("Error al guardar la pieza: " + e.getMessage());
            throw new Exception("Error al guardar la clase " + clase);
        }
    }

    public void eliminarElemento(String path, String clase, String id) throws Exception {
        File file = new File(path);
        if (!file.delete()) {
            System.out.println("El " + clase + " con id " + id + " no se encuentra en el inventario");
            throw new Exception("El elemento no se encuentra en el inventario");
        } else {
            System.out.println("Elemento eliminado");
        }
    }
}
