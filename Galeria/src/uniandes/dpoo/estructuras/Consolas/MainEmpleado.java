package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class MainEmpleado {
    Controlador controlador;
    Usuario usuarioActual;
    Scanner scanner = new Scanner(System.in);
    MainUtils utils = new MainUtils();

    public static void main(String[] args) {
        MainEmpleado main = new MainEmpleado();
        main.ejecutar();
    }

    private void ejecutar() {
        controlador = new Controlador();
        controlador.cargarDatos();
        login();

        boolean ejecutando = true;

        while (ejecutando) {

            if (usuarioActual.getRol().contains(Usuario.EMPLEADO)) {
                System.out.println("Bienvenido " + usuarioActual.getNombre());
                System.out.println("1. Crear y hacer subasta");
                System.out.println("2. Consulta inventario");
                System.out.println("3. Vender pieza");
                System.out.println("4. Salir");
                int opcion = 0;
                do {
                    System.out.println("Ingrese una opción");
                    opcion = scanner.nextInt();
                } while (opcion < 1 || opcion > 4);

                switch (opcion) {
                    case 1:
                        controlador = new ConsolaSubastas().ejecutarConsolaSubastas(controlador);
                        break;
                    case 2:
                        controlador = new ConsolaConsultarPiezas().ejecutarConsultar(controlador);
                        break;
                    case 3:
                        controlador = new ConsolaVenderPiezas().ejecutar(controlador);
                        break;
                    case 4:
                        System.exit(0);
                        break;
                }
            } else {
                System.out.println("Usuario no autorizado");
            }
        }
    }

    private void login() {
        System.out.println("Ingrese su login");
        String login = scanner.next();
        System.out.println("Ingrese su contraseña");
        String contrasena = scanner.next();
        usuarioActual = controlador.login(login, contrasena);
        Empleado empleado = controlador.buscarEmpleadoPorId(usuarioActual.getId());
        if (usuarioActual == null) {
            System.out.println("Usuario no encontrado");
            login();
        }

        if (empleado == null) {
            System.out.println("Usuario no encontrado");
            login();
        }

        if (!empleado.getRol().equals(Usuario.EMPLEADO)) {
            System.out.println("Usuario no autorizado");
            login();
        }
    }
}
