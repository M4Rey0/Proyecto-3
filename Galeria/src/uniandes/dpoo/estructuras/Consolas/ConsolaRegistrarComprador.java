package uniandes.dpoo.estructuras.Consolas;

import java.util.ArrayList;
import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Usuario;

public class ConsolaRegistrarComprador {
    private Controlador controlador;
    Scanner scanner = new Scanner(System.in);

    public Controlador ejecutarRegistrarComprador(Controlador controlador) {
        this.controlador = controlador;
        System.out.println("El comprador a registrar es un usuario ya activo? (1. Si/ 2. No)");
        int opcion = 0;
        do {
            System.out.println("Ingrese una opción");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 2);

        if (opcion == 1) {
            System.out.println("Ingrese el id del usuario");
            int id = scanner.nextInt();
            Usuario usuario = controlador.buscarUsuarioPorId(id);
            try {
                usuario.agregarRol(Usuario.COMPRADOR);
                registrarComprador(usuario);
            } catch (Exception e) {
                System.out.println("El usuario ya es un comprador");
            }

        } else {
            ConsolaRegistrarUsuario consolaRegistrarUsuario = new ConsolaRegistrarUsuario();
            Usuario usuario = consolaRegistrarUsuario.ejecutarRegistrarComprador();
            try {
                controlador.agregarUsuario(usuario);
            } catch (Exception e) {
                System.out.println("No se pudo agregar al usuario, intente de nuevo.");
            }
            registrarComprador(usuario);
        }

        scanner.close();
        return this.controlador;
    }

    private void registrarComprador(Usuario usuario) {
        System.out.println("Ingrese el límite de compra");
        int limiteCompra = scanner.nextInt();
        System.out.println("El usuario está verificado para subastas? (1. Si/ 2. No)");
        boolean verificadoSubasta = false;
        int opcion = 0;
        do {
            System.out.println("Ingrese una opción");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 2);
        if (opcion == 1) {
            verificadoSubasta = true;
        }
        Comprador comprador = new Comprador(usuario, limiteCompra, verificadoSubasta,
                new ArrayList<Integer>());
        try {
            controlador.agregarComprador(comprador);
        } catch (Exception e) {
            System.out.println("Hubo un problema al guardar al comprador, intente de nuevo.");
        }
    }
}
