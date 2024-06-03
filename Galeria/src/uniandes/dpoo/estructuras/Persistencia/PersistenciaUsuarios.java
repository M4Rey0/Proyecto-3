package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import uniandes.dpoo.estructuras.Inventarios.InventarioUsuarios;
import uniandes.dpoo.estructuras.model.Usuario;

public class PersistenciaUsuarios {
    private static final String FILE_PATH = "data/Usuarios/";
    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarUsuario(Usuario usuario) throws Exception {
        String path = FILE_PATH + usuario.getId() + ".txt";
        String contenido = usuario.toString();
        String clase = "Usuario";

        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public Usuario cargarUsuario(String texto) {
        String[] partes = texto.split(",");
        Usuario usuario = new Usuario();
        usuario.setId(Integer.parseInt(partes[0]));
        usuario.setNombreUsuario(partes[1]);
        usuario.setPassword(partes[2]);
        usuario.setNombre(partes[3]);
        usuario.setTelefono(partes[4]);
        usuario.setEmail(partes[5]);
        usuario.setRol(partes[6]);
        return usuario;
    }

    public void eliminarUsuario(Usuario usuario) throws Exception {
        String path = FILE_PATH + usuario.getId() + ".txt";
        persistenciaUtils.eliminarElemento(path, "Usuario", Integer.toString(usuario.getId()));
    }

    public InventarioUsuarios cargarUsuarios(InventarioUsuarios inventarioUsuarios) throws RuntimeException {
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Usuario usuario = cargarUsuario(contenido);
                        inventarioUsuarios.agregarUsuario(usuario);
                    }
                }
            }
            return inventarioUsuarios;
        } catch (IOException e) {
            System.out.println("Error al cargar los usuarios: " + e.getMessage());
            throw new RuntimeException("Error al cargar los usuarios");
        }
    }
}
