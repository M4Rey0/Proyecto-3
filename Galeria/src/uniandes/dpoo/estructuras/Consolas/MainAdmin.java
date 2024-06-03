package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class MainAdmin {

    Controlador controlador;
    Usuario usuarioActual;
    Scanner scanner = new Scanner(System.in);
    MainUtils utils = new MainUtils();

    public static void main(String[] args) {
        MainAdmin main = new MainAdmin();
        main.ejecutar();
    }

    private void ejecutar () {
        controlador = new Controlador();
        controlador.cargarDatos();
        login();

        
            System.out.println("Bienvenido " + usuarioActual.getNombre());
            System.out.println("1. Menu piezas");
            System.out.println("2. Consultar compradores");
            System.out.println("3. Crear y hacer subasta");
            System.out.println("4. Añadir usuarios");
            System.out.println("5. Consulta inventario");
            System.out.println("6. Vender pieza");
            System.out.println("7. Salir");

            int opcion = 0;
            boolean ejecutando = true;
            while (ejecutando){
            do {
                System.out.println("Ingrese una opción");
                opcion = scanner.nextInt();
            } while (opcion < 1 || opcion > 7);

            switch (opcion) {
                case 1:
                    menuPiezas();
                    break;
                case 2:
                    controlador = new ConsolaConsultarComprador().ejecutarConsulta(controlador);
                    break;
                case 3:
                    controlador = new ConsolaSubastas().ejecutarConsolaSubastas(controlador);
                    break;
                case 4:
                    menuAgregarUsuario();
                    break;
                case 5:
                    controlador = new ConsolaConsultarPiezas().ejecutarConsultar(controlador);
                    break;
                case 6:
                    controlador = new ConsolaVenderPiezas().ejecutar(controlador);
                    break;
                case 7:
                    ejecutando = false;
                    System.exit(0);
                    break;
            }
        }
    }

    private void login(){
        System.out.println("Ingrese su login");
        String login = scanner.next();
        System.out.println("Ingrese su contraseña");
        String contrasena = scanner.next();
        usuarioActual = controlador.login(login, contrasena);
        if (usuarioActual == null) {
            System.out.println("Usuario no encontrado");
            login();
        } else{
            Empleado empleado = controlador.buscarEmpleadoPorId(usuarioActual.getId());
            if (empleado == null) {
                System.out.println("Usuario no encontrado");
                login();
            }
    
            if (!empleado.getCargo().equals("admin")) {
                System.out.println("Usuario no autorizado");
                login();
            }
        }
        
    }

    private void menuPiezas() {
        ConsolaAgregarQuitarPiezas consolaAdicional = new ConsolaAgregarQuitarPiezas();
        this.controlador = consolaAdicional.ejecutar(controlador);
    }

    private void menuAgregarUsuario(){
        System.out.println("Que tipo de usuario desea crear?");
        System.out.println("1. Empleado");
        System.out.println("2. Comprador");

        int opcion = 0;
        do {
            System.out.println("Ingrese una opción");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 2);

        switch (opcion) {
            case 1:
                this.controlador = new ConsolaRegistrarEmpleado().ejecutarRegistrarEmpleado(controlador);
                break;
            case 2:
                this.controlador = new ConsolaRegistrarComprador().ejecutarRegistrarComprador(controlador);
                break;
        }
    }
}
