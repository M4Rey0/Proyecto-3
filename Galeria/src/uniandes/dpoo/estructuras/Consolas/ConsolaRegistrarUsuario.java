package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.model.Usuario;

public class ConsolaRegistrarUsuario {
    
    public Usuario ejecutarRegistrarComprador(){

        Usuario usuario = registrarUsuario();

        usuario.setRol(Usuario.COMPRADOR);

        return usuario;
    }

    public Usuario ejecutarRegistrarEmpleado(){

        Usuario usuario = registrarUsuario();

        usuario.setRol(Usuario.EMPLEADO);

        return usuario;

    }

    private Usuario registrarUsuario(){
        Scanner scanner = new Scanner(System.in);

        Usuario usuario = new Usuario();

        System.out.println("Ingrese el login del usuario: ");
        usuario.setNombreUsuario(scanner.nextLine());
        System.out.println("Ingrese la contraseña del usuario: ");
        usuario.setPassword(scanner.nextLine());
        System.out.println("Ingrese el nombre del usuario: ");
        usuario.setNombre(scanner.nextLine());
        System.out.println("Ingrese el teléfono del usuario: ");
        usuario.setTelefono(scanner.nextLine());
        System.out.println("Ingrese el email del usuario: ");
        usuario.setEmail(scanner.nextLine());

        scanner.close();
        return usuario;
    }
}
