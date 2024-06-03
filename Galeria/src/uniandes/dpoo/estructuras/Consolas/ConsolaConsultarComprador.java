package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;

public class ConsolaConsultarComprador {
    private Controlador controlador;
    private Scanner scanner = new Scanner(System.in);

    public Controlador ejecutarConsulta(Controlador controlador){
        this.controlador = controlador;

        System.out.println("Consultar historia de un comprador");

        System.out.println("Ingrese el id del comprador que desea consultar");
        int idComprador = scanner.nextInt();
        try {
            System.out.println(controlador.consultarHistoriaComprador(idComprador));
        } catch (Exception e) {
            System.out.println("No se encontró el comprador");
        }

        scanner.close();
        return this.controlador;
    }
}
