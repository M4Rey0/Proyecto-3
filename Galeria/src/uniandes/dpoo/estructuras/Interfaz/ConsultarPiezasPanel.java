package uniandes.dpoo.estructuras.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import uniandes.dpoo.estructuras.Interfaz.utils.TablaCompradores;
import uniandes.dpoo.estructuras.Interfaz.utils.TablaCompras;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Pieza;

public class ConsultarPiezasPanel extends JPanel {

    private Controlador controlador;
    private MenuPanel menuPanel;

    public ConsultarPiezasPanel(Controlador controlador, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.menuPanel = menuPanel;

        setLayout(new BorderLayout());

        // Panel superior para la consulta
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 0;
        topPanel.add(new JLabel("ID de la pieza:"), gbc);

        gbc.gridx++;
        JTextField txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(200, 25));
        topPanel.add(txtId, gbc);

        JButton btnConsultar = new JButton("Consultar");
        gbc.gridx++;
        topPanel.add(btnConsultar, gbc);

        add(topPanel, BorderLayout.NORTH);

        // Panel central para la información de la pieza y las tablas
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Panel para la información de la pieza
        JPanel infoPieza = new JPanel(new GridBagLayout());
        infoPieza.setVisible(false);
        infoPieza.setBorder(BorderFactory.createTitledBorder("Información de la pieza"));
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.anchor = GridBagConstraints.WEST;

        centerPanel.add(infoPieza, BorderLayout.NORTH);

        // Panel para las tablas
        JPanel tablas = new JPanel(new BorderLayout());
        tablas.setVisible(false);

        TablaCompras tablaCompras = new TablaCompras();
        JScrollPane scrollCompras = new JScrollPane(tablaCompras);

        TablaCompradores tablaCompradores = new TablaCompradores();
        JScrollPane scrollCompradores = new JScrollPane(tablaCompradores);

        tablas.add(scrollCompradores, BorderLayout.NORTH);
        tablas.add(scrollCompras, BorderLayout.CENTER);

        centerPanel.add(tablas, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        btnConsultar.addActionListener(e -> {
            try {

                tablaCompras.limpiar();
                tablaCompradores.limpiar();

                Pieza pieza = controlador.buscarPiezaPorId(Integer.parseInt(txtId.getText()));
                tablaCompras.agregarCompras(controlador.buscarComprasPorPieza(pieza.getId()));
                tablaCompradores.agregarCompradores(controlador.buscarCompradoresPorPieza(pieza.getId()));

                infoPieza.removeAll();

                gbcInfo.gridx = 0;
                gbcInfo.gridy = 0;
                infoPieza.add(new JLabel("ID: " + pieza.getId()), gbcInfo);
                gbcInfo.gridy++;
                infoPieza.add(new JLabel("Titulo: " + pieza.getTitulo()), gbcInfo);
                gbcInfo.gridy++;
                infoPieza.add(new JLabel("Autor: " + pieza.getAutoresString()), gbcInfo);
                gbcInfo.gridy++;
                infoPieza.add(new JLabel("Valor: " + pieza.getValor()), gbcInfo);
                gbcInfo.gridy++;
                infoPieza.add(new JLabel("Estado: " + pieza.getEstado()), gbcInfo);

                infoPieza.setVisible(true);
                tablas.setVisible(true);

                revalidate();
                repaint();

                txtId.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}
