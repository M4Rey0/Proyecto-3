package uniandes.dpoo.estructuras.Inventarios;

import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class InventarioUsuarios {
    private Map<Integer, Usuario> usuarios = new HashMap<>();

    private Map<String, Integer> usuariosPorLogin = new HashMap<>();

    private Map<Integer, Comprador> compradores = new HashMap<>();


    private Map<Integer, Empleado> empleados = new HashMap<>();

    public InventarioUsuarios() {
    }

    public boolean login (String login, String password) {
        Usuario usuario = buscarUsuarioPorLogin(login);
        if (usuario != null) {
            return usuario.getPassword().equals(password);
        }
        return false;
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
        usuariosPorLogin.put(usuario.getNombreUsuario(), usuario.getId());
    }

    public void quitarUsuario(Usuario usuario) throws Exception {
        if (!usuarios.containsKey(usuario.getId())) {
            throw new Exception("El usuario no se encuentra en el inventario");
        } else {
            usuarios.remove(usuario.getId());
            usuariosPorLogin.remove(usuario.getNombreUsuario());
        }
    }

    public void actualizarUsuario(Usuario usuario) throws Exception {
        if (!usuarios.containsKey(usuario.getId())) {
            throw new Exception("El usuario no se encuentra en el inventario");
        } else {
            usuarios.put(usuario.getId(), usuario);
            usuariosPorLogin.put(usuario.getNombreUsuario(), usuario.getId());
        }
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.get(id);
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        return usuarios.get(usuariosPorLogin.get(login));
    }

    public void agregarComprador(Comprador comprador) {
        compradores.put(comprador.getId(), comprador);
    }

    public void quitarComprador(Comprador comprador) throws Exception {
        if (!compradores.containsKey(comprador.getId())) {
            throw new Exception("El comprador no se encuentra en el inventario");
        } else {
            compradores.remove(comprador.getId());
        }
    }

    public Comprador buscarCompradorPorId(int id) {
        return compradores.get(id);
    }

    public void agregarEmpleado(Empleado empleado) {
        empleados.put(empleado.getId(), empleado);
    }

    public void quitarEmpleado(Empleado empleado) throws Exception {
        if (!empleados.containsKey(empleado.getId())) {
            throw new Exception("El empleado no se encuentra en el inventario");
        } else {
            empleados.remove(empleado.getId());
        }
    }

    public void actualizarEmpleado(Empleado empleado) throws Exception {
        if (!empleados.containsKey(empleado.getId())) {
            throw new Exception("El empleado no se encuentra en el inventario");
        } else {
            empleados.put(empleado.getId(), empleado);
        }
    }

    public Empleado buscarEmpleadoPorId(int id) {
        return empleados.get(id);
    }

    public void borrarPiezaDePropietario(int id) throws Exception {
        for (Comprador propietario : compradores.values()) {
            propietario.eliminarPieza(id);
            compradores.put(propietario.getId(), propietario);
        }
    }

}
