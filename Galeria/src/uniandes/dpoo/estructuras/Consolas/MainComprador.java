package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Usuario;

public class MainComprador {
    Controlador controlador;
    Usuario usuarioActual = null;
    Comprador compradorActual = null;
    Scanner scanner = new Scanner(System.in);
    MainUtils utils = new MainUtils();

    public static void main(String[] args) {
        MainComprador main = new MainComprador();
        main.ejecutar();
    }

    private void ejecutar () {
        controlador = new Controlador();
        controlador.cargarDatos();
        login();
        boolean ejecutando = true;
        while (ejecutando){

        if (usuarioActual.getRol().contains(Usuario.COMPRADOR)) {
            System.out.println("Bienvenido " + usuarioActual.getNombre());
            System.out.println("1. Consultar mis piezas");
            System.out.println("2. Consulta inventario");
            System.out.println("3. Salir");
            
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
                    controlador = new ConsolaConsultarMisPiezas().ejecutar(controlador, compradorActual);
                    break;
                case 3:
                    System.exit(0);
                    break;
            }
        } else {
            System.out.println("Usuario no autorizado");
        }
    }
    }

    private void login(){
        System.out.println("Ingrese su login");
        String login = scanner.next();
        System.out.println("Ingrese su contraseña");
        String contrasena = scanner.next();
        usuarioActual = controlador.login(login, contrasena);
        compradorActual = controlador.buscarCompradorPorId(usuarioActual.getId());
        if (usuarioActual == null) {
            System.out.println("Usuario no encontrado");
            login();
        }

        if (compradorActual == null) {
            System.out.println("Usuario no encontrado");
            login();
        }
    }
}
