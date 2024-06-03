package uniandes.dpoo.estructuras.Consolas;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class AgregarUsuarios {

    Controlador controlador = new Controlador();
    
    public static void main(String[] args) {
        AgregarUsuarios agregarUsuarios = new AgregarUsuarios();
        agregarUsuarios.ejecutar();
    }

    public void ejecutar(){
        Usuario admin = new Usuario();
        admin.setNombreUsuario("admin");
        admin.setPassword("admin");
        admin.setNombre("admin");
        admin.setTelefono("1234567");
        admin.setEmail("correo");
        admin.setRol(Usuario.ADMIN);
        try {
            controlador.agregarUsuario(admin);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Empleado adminEmpleado = new Empleado(admin, Empleado.ADMIN);
        try {
            controlador.agregarEmpleado(adminEmpleado);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
