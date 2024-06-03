package uniandes.dpoo.estructuras.Interfaz;

import javax.swing.*;

import uniandes.dpoo.estructuras.Logica.Controlador;
import java.awt.*;

public class Main {

    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        controlador.cargarDatos();
        
        JFrame frame = new JFrame("Pantalla de Inicio");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(screenSize.width, screenSize.height);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        LoginPanel loginPanel = new LoginPanel(controlador, frame);
        frame.setContentPane(loginPanel);

        frame.setVisible(true);
    }
}


