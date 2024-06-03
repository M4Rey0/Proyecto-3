package uniandes.dpoo.estructuras.Consolas;

import java.util.ArrayList;
import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.Logica.FormatUtils;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;

public class ConsolaConsultarMisPiezas {
    Controlador controlador;
    Scanner scanner = new Scanner(System.in);
    Comprador usuario;
    FormatUtils formatUtils = new FormatUtils();

    public Controlador ejecutar(Controlador controlador, Comprador usuario){
        this.controlador = controlador;
        this.usuario = usuario;
        System.out.println("Mis piezas son :");
        ArrayList<Pieza> piezas = new ArrayList<>();

        for (int idPieza : usuario.getPiezas()) {
            Pieza pieza = controlador.buscarPiezaPorId(idPieza);
            piezas.add(pieza);
        }

        for (Pieza pieza : piezas) {
            System.out.println(formatUtils.mostradorPieza(pieza));
        }

        return this.controlador;
    }
}
