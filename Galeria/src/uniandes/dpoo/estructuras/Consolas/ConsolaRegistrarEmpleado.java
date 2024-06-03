package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class ConsolaRegistrarEmpleado {
    private Controlador controlador;
    
    private Scanner scanner = new Scanner(System.in);

    public Controlador ejecutarRegistrarEmpleado(Controlador controlador){
        this.controlador = controlador;
        System.out.println("Es un usuario ya existente? (1. Si/ 2. No)");
        int opcion = 0;
        do {
            System.out.println("Ingrese una opción");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 2);

        Usuario usuario;

        if (opcion == 1) {
            System.out.println("Ingrese el id del usuario");
            int id = scanner.nextInt();
            usuario = controlador.buscarUsuarioPorId(id);
            try {
                usuario.agregarRol(Usuario.EMPLEADO);
                registrarEmpleado(usuario);
            } catch (Exception e) {
                System.out.println("El usuario ya es un empleado");
            }
        } else {
            ConsolaRegistrarUsuario consolaRegistrarUsuario = new ConsolaRegistrarUsuario();
            usuario = consolaRegistrarUsuario.ejecutarRegistrarEmpleado();
            try {
                controlador.agregarUsuario(usuario);
                registrarEmpleado(usuario);
            } catch (Exception e) {
                System.out.println("No se pudo agregar al usuario, intente de nuevo.");
            }
        }
        return this.controlador;
    }

    private void registrarEmpleado(Usuario usuario){
        System.out.println("Ingrese el cargo del empleado");
        System.out.println("1. Admin\n2. Empleado");
        int opcion = 0;
        do {
            System.out.println("Ingrese una opción");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 2);

        Empleado empleado;
        if (opcion == 1) {
            empleado = new Empleado(usuario, Empleado.ADMIN);
        } else {
            empleado = new Empleado(usuario, Empleado.EMPLEADO);
        }

        try {
            controlador.agregarEmpleado(empleado);
        } catch (Exception e) {
            System.out.println("No se pudo agregar al empleado, intente de nuevo.");
        }
    }
}
