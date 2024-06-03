package uniandes.dpoo.estructuras.Interfaz;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Usuario;

import javax.swing.*;
import java.awt.*;

public class MenuPanel extends JPanel {

    private Controlador controlador;
    private JFrame frame;
    private Usuario usuario;
    private JPanel panelPlaceholder;

    public MenuPanel(Controlador controlador, JFrame frame, Usuario usuario) {
        this.controlador = controlador;
        this.frame = frame;
        this.usuario = usuario;

        setLayout(new BorderLayout());

        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridBagLayout());
        sidebar.setPreferredSize(new Dimension((int) (Toolkit.getDefaultToolkit().getScreenSize().width * 0.15), getHeight()));
        sidebar.setBackground(Color.LIGHT_GRAY);

        JButton crearButton = new JButton("Crear");
        JButton consultarButton = new JButton("Consultar");

        Dimension buttonSize = new Dimension(120, 40);
        crearButton.setPreferredSize(buttonSize);
        consultarButton.setPreferredSize(buttonSize);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        sidebar.add(crearButton, gbc);

        gbc.gridy = 1;
        sidebar.add(consultarButton, gbc);

        add(sidebar, BorderLayout.WEST);

        JLabel welcomeLabel = new JLabel("Galeria", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 36));
        add(welcomeLabel, BorderLayout.CENTER);

        panelPlaceholder = new JPanel();
        panelPlaceholder.setBackground(Color.WHITE);
        add(panelPlaceholder, BorderLayout.CENTER);

        crearButton.addActionListener(e -> {
            JPanel panelCrear = crearPanelCrear();
            cambiarPanel(panelCrear);
        });

        consultarButton.addActionListener(e -> {
            JPanel panelConsultar = crearPanelConsultar();
            cambiarPanel(panelConsultar);
        });
    }

    public JPanel crearPanelCrear() {
        JPanel panelCrear = new JPanel();
        panelCrear.setLayout(new GridBagLayout());
        panelCrear.setBackground(Color.WHITE);

        JButton btnCrearComprador = new JButton("Crear comprador");
        JButton btnCrearPieza = new JButton("Crear pieza");
        JButton btnCrearEmpleado = new JButton("Crear empleado");
        JButton btnCrearVenta = new JButton("Crear venta");
        JButton btnCrearSubasta = new JButton("Crear subasta");

        Dimension buttonSize = new Dimension(200, 40);
        btnCrearComprador.setPreferredSize(buttonSize);
        btnCrearPieza.setPreferredSize(buttonSize);
        btnCrearEmpleado.setPreferredSize(buttonSize);
        btnCrearVenta.setPreferredSize(buttonSize);
        btnCrearSubasta.setPreferredSize(buttonSize);

        //listeners
        btnCrearComprador.addActionListener(e -> {
            JPanel panelCrearComprador = new CrearCompradorPanel(controlador, this);
            cambiarPanel(panelCrearComprador);
        });

        btnCrearEmpleado.addActionListener(e ->{
            JPanel panelCrearEmpleado = new CrearEmpleadoPanel(controlador, frame, this);
            cambiarPanel(panelCrearEmpleado);
        });

        btnCrearPieza.addActionListener(e -> {
            JPanel panelCrearPieza = new CrearPiezaPanel(controlador, frame, this);
            cambiarPanel(panelCrearPieza);
        });

        btnCrearVenta.addActionListener(e -> {
            JPanel panelCrearVenta = new CrearVentaPanel(controlador, frame, this);
            cambiarPanel(panelCrearVenta);
        });

        btnCrearSubasta.addActionListener(e -> {
            JPanel panelCrearSubasta = new CrearSubastaPanel(controlador, frame, this);
            cambiarPanel(panelCrearSubasta);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelCrear.add(btnCrearComprador, gbc);

        gbc.gridy = 1;
        panelCrear.add(btnCrearPieza, gbc);

        gbc.gridy = 2;
        panelCrear.add(btnCrearEmpleado, gbc);

        gbc.gridy = 3;
        panelCrear.add(btnCrearVenta, gbc);

        gbc.gridy = 4;
        panelCrear.add(btnCrearSubasta, gbc);

        return panelCrear;
    }

    public JPanel crearPanelConsultar() {
        JPanel panelConsultar = new JPanel();
        panelConsultar.setLayout(new GridBagLayout());
        panelConsultar.setBackground(Color.WHITE);

        JButton btnConsultarAutor = new JButton("Consultar autor");
        JButton btnConsultarComprador = new JButton("Consultar comprador");
        JButton btnConsultarPiezas = new JButton("Consultar piezas");
        JButton btnConsultarVentas = new JButton("Consultar ventas");

        Dimension buttonSize = new Dimension(200, 40);
        btnConsultarAutor.setPreferredSize(buttonSize);
        btnConsultarComprador.setPreferredSize(buttonSize);
        btnConsultarPiezas.setPreferredSize(buttonSize);
        btnConsultarVentas.setPreferredSize(buttonSize);

        btnConsultarPiezas.addActionListener(e -> {
            JPanel panelConsultarPiezas = new ConsultarPiezasPanel(controlador, this);
            cambiarPanel(panelConsultarPiezas);
        });

        btnConsultarAutor.addActionListener(e -> {
            JPanel panelConsultarAutor = new ConsultarAutorPanel(controlador, this);
            cambiarPanel(panelConsultarAutor);
        });

        btnConsultarComprador.addActionListener(e -> {
            JPanel panelConsultarComprador = new ConsultarCompradorPanel(controlador, this);
            cambiarPanel(panelConsultarComprador);
        });

        btnConsultarVentas.addActionListener(e -> {
            JPanel panelConsultarVentas = new ConsultarVentasPanel(controlador, this);
            cambiarPanel(panelConsultarVentas);
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;

        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        panelConsultar.add(btnConsultarAutor, gbc);

        gbc.gridy = 1;
        panelConsultar.add(btnConsultarComprador, gbc);

        gbc.gridy = 2;
        panelConsultar.add(btnConsultarPiezas, gbc);

        gbc.gridy = 3;
        panelConsultar.add(btnConsultarVentas, gbc);

        return panelConsultar;
    }

    public void cambiarPanel(JPanel nuevoPanel) {
        frame.getContentPane().remove(panelPlaceholder);
        frame.getContentPane().add(nuevoPanel, BorderLayout.CENTER);
        panelPlaceholder = nuevoPanel;
        frame.revalidate();
        frame.repaint();
    }
}



