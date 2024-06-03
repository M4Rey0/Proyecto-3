package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import uniandes.dpoo.estructuras.Inventarios.InventarioUsuarios;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class PersistenciaEmpleados {
    private static final String FILE_PATH = "data/Empleados/";
    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();
    
    public void guardarEmpleado(Empleado empleado) throws Exception {
        String path = FILE_PATH + empleado.getId() + ".txt";
        String contenido = empleado.toString();
        String clase = "Empleado";

        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public Empleado cargarEmpleado(String texto, InventarioUsuarios usuarios) {
        String[] partes = texto.split(",");
        Usuario usuarioBase = usuarios.buscarUsuarioPorId(Integer.parseInt(partes[0]));
        Empleado empleado = new Empleado(usuarioBase, partes[1].trim());
        return empleado;
    }

    public void eliminarEmpleado(Empleado empleado) throws Exception {
        String path = FILE_PATH + empleado.getId() + ".txt";
        persistenciaUtils.eliminarElemento(path, "Empleado", Integer.toString(empleado.getId()));
    }

    public InventarioUsuarios cargarEmpleados(InventarioUsuarios inventarioUsuarios){
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Empleado empleado = cargarEmpleado(contenido, inventarioUsuarios);
                        inventarioUsuarios.agregarEmpleado(empleado);
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
