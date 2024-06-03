package uniandes.dpoo.estructuras.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import uniandes.dpoo.estructuras.Interfaz.utils.TablaCompradores;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.Subasta;

public class EjecutarSubastaPanel extends JPanel {

    private Controlador controlador;
    private MenuPanel menuPanel;
    private Subasta subasta;
    private Pieza piezaActual;
    private List<Integer> piezas;
    private int ultimoValor = 0;
    private Comprador ganadorActual;
    private int i = 0;
    TablaCompradores tablaCompradores = new TablaCompradores();

    public EjecutarSubastaPanel(Subasta subasta, Controlador controlador, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.menuPanel = menuPanel;
        this.subasta = subasta;
        this.piezas = subasta.getPiezas();

        setPiezaActual(piezas.get(i));

        ultimoValor = piezaActual.getValor();

        for (int id : subasta.getCompradores()) {
            tablaCompradores.agregarComprador(controlador.buscarCompradorPorId(id));
        }

        setLayout(new BorderLayout());
        actualizarPanel();
    }

    private void actualizarPanel() {
        removeAll();
        add(infoPieza(), BorderLayout.NORTH);
        add(new JScrollPane(tablaCompradores), BorderLayout.CENTER);
        add(panelPujar(), BorderLayout.SOUTH);
        revalidate();  // Importante para revalidar el layout
        repaint();     // Importante para repintar el panel
    }

    private void setPiezaActual(int id) {
        this.piezaActual = controlador.buscarPiezaPorId(id);
    }

    private JPanel infoPieza() {
        JPanel infoPieza = new JPanel(new GridBagLayout());

        GridBagConstraints gbcInfo = new GridBagConstraints();
        gbcInfo.insets = new Insets(5, 5, 5, 5);
        gbcInfo.anchor = GridBagConstraints.WEST;

        gbcInfo.gridx = 0;
        gbcInfo.gridy = 0;
        infoPieza.add(new JLabel("ID: " + piezaActual.getId()), gbcInfo);
        gbcInfo.gridy++;
        infoPieza.add(new JLabel("Titulo: " + piezaActual.getTitulo()), gbcInfo);
        gbcInfo.gridy++;
        infoPieza.add(new JLabel("Autor: " + piezaActual.getAutoresString()), gbcInfo);
        gbcInfo.gridy++;
        infoPieza.add(new JLabel("Valor: " + piezaActual.getValor()), gbcInfo);
        gbcInfo.gridy++;
        infoPieza.add(new JLabel("Estado: " + piezaActual.getEstado()), gbcInfo);

        infoPieza.setBorder(BorderFactory.createTitledBorder("Información de la pieza"));

        return infoPieza;
    }

    private JPanel panelPujar() {
        JPanel panelPujar = new JPanel(new GridBagLayout());

        GridBagConstraints gbcPujar = new GridBagConstraints();
        gbcPujar.insets = new Insets(5, 5, 5, 5);
        gbcPujar.anchor = GridBagConstraints.LINE_END;

        JLabel lblPujar = new JLabel("Valor de la puja:");
        JTextField txtPujar = new JTextField();
        txtPujar.setPreferredSize(new Dimension(200, 20));
        JLabel lblComprador = new JLabel("Comprador: ");
        JTextField txtComprador = new JTextField();
        txtComprador.setPreferredSize(new Dimension(200,20));
        
        JButton btnPujar = new JButton("Pujar");


        btnPujar.addActionListener(e -> {
            int valor = Integer.parseInt(txtPujar.getText());
            try {
                if (valor > ultimoValor) {
                    ganadorActual = controlador.buscarCompradorPorId(Integer.parseInt(txtComprador.getText()));
                    ultimoValor = valor;
                } else {
                    JOptionPane.showMessageDialog(this, "El valor de la puja debe ser mayor al último valor", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            txtPujar.setText("");
            txtComprador.setText("");
        });

        JButton btnTerminarSubasta = new JButton("Terminar Subasta");
        btnTerminarSubasta.addActionListener(e -> {
            try {
                if (ganadorActual != null) {
                    JOptionPane.showMessageDialog(this, "Subasta terminada", "Subasta terminada", JOptionPane.INFORMATION_MESSAGE);
                    menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
                } else {
                    JOptionPane.showMessageDialog(this, "Debe haber un ganador para terminar la subasta", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JButton btnSiguienteProducto = new JButton("Siguiente Producto");
        btnSiguienteProducto.addActionListener(e -> {
            try {
                if (i < piezas.size() - 1) {
                    i++;
                    setPiezaActual(piezas.get(i));
                    ultimoValor = piezaActual.getValor();
                    ganadorActual = null;
                    actualizarPanel();
                    JOptionPane.showMessageDialog(this, "Siguiente producto", "Siguiente producto", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "No hay más productos", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                JOptionPane.showMessageDialog(this, "Error: " + e1.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbcPujar.gridx = 0;
        gbcPujar.gridy = 0;
        panelPujar.add(lblComprador, gbcPujar);
        gbcPujar.gridx++;
        panelPujar.add(txtComprador, gbcPujar);
        gbcPujar.gridx = 0;
        gbcPujar.gridy++;
        panelPujar.add(lblPujar, gbcPujar);
        gbcPujar.gridx++;
        panelPujar.add(txtPujar, gbcPujar);
        gbcPujar.gridx++;
        panelPujar.add(btnPujar, gbcPujar);

        gbcPujar.gridx = 0;
        gbcPujar.gridy++;
        gbcPujar.gridwidth = 3;
        panelPujar.add(btnSiguienteProducto, gbcPujar);
        gbcPujar.gridy++;
        panelPujar.add(btnTerminarSubasta, gbcPujar);

        return panelPujar;
    }
}
