package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;

public class ConsolaConsultarPiezas {
    private Controlador controlador;
    Scanner scanner = new Scanner(System.in);
    
    public Controlador ejecutarConsultar(Controlador controlador){
        this.controlador = controlador;
        System.out.println("Consultar piezas");
        System.out.println("1. Consultar historia de una pieza");
        System.out.println("2. Consultar historia de un autor");

        int opcion = 0;
        do {
            System.out.println("Ingrese una opción");
            opcion = scanner.nextInt();
        } while (opcion < 1 || opcion > 2);

        switch (opcion) {
            case 1:
                consultarHistoriaPieza();
                break;
            case 2:
                consultarHistoriaAutor();
                break;
        }


        return this.controlador;
    }

    private void consultarHistoriaPieza() {
        System.out.println("Ingrese el id de la pieza que desea consultar");
        int idPieza = scanner.nextInt();
        try {
            System.out.println(controlador.consultarHistoriaPieza(idPieza));
        } catch (Exception e) {
            System.out.println("No se encontró la pieza");
        }
    }

    private void consultarHistoriaAutor() {
        System.out.println("Ingrese el nombre del autor que desea consultar");
        String nombreAutor = scanner.next();
        try {
            System.out.println(controlador.consultarHistoriaAutor(nombreAutor));
        } catch (Exception e) {
            System.out.println("No se encontró el autor");
        }
    }
}
