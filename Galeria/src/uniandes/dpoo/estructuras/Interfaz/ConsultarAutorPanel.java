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
import uniandes.dpoo.estructuras.Interfaz.utils.TablaPiezas;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Pieza;

public class ConsultarAutorPanel extends JPanel{

    private Controlador controlador;
    private MenuPanel menuPanel;

    public ConsultarAutorPanel(Controlador controlador, MenuPanel menuPanel) {
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
        topPanel.add(new JLabel("Nombre del autor:"), gbc);

        gbc.gridx++;
        JTextField txtAutor = new JTextField();
        txtAutor.setPreferredSize(new Dimension(200, 25));
        topPanel.add(txtAutor, gbc);

        JButton btnConsultar = new JButton("Consultar");
        gbc.gridx++;
        topPanel.add(btnConsultar, gbc);

        add(topPanel, BorderLayout.NORTH);

        // Panel central para la información de la pieza y las tablas
        JPanel centerPanel = new JPanel(new BorderLayout());

        // Panel para la información de la pieza
        JPanel infoPieza = new JPanel(new GridBagLayout());
        infoPieza.setVisible(false);
        infoPieza.setBorder(BorderFactory.createTitledBorder("Información del autor"));
        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.anchor = GridBagConstraints.WEST;

        centerPanel.add(infoPieza, BorderLayout.NORTH);

        // Panel para las tablas
        JPanel tablas = new JPanel(new BorderLayout());
        tablas.setVisible(false);

        TablaPiezas tablaPiezas = new TablaPiezas();
        JScrollPane scrollPiezas = new JScrollPane(tablaPiezas);

        tablas.add(scrollPiezas, BorderLayout.CENTER);

        centerPanel.add(tablas, BorderLayout.CENTER);

        add(centerPanel, BorderLayout.CENTER);

        btnConsultar.addActionListener(e -> {
            try {

                tablaPiezas.limpiar();
                
                Autor autor = controlador.buscarAutorPorNombre(txtAutor.getText());

                tablaPiezas.agregarPiezas(controlador.buscarPiezasPorAutor(txtAutor.getText()));

                infoPieza.removeAll();

                gbcInfo.gridx = 0;
                gbcInfo.gridy = 0;
                infoPieza.add(new JLabel("Nombre: " + autor.getNombre()), gbcInfo);
                gbcInfo.gridy++;
                infoPieza.add(new JLabel("Tipo: " + autor.getTipo()), gbcInfo);

                infoPieza.setVisible(true);
                tablas.setVisible(true);

                revalidate();
                repaint();

                txtAutor.setText("");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}   
