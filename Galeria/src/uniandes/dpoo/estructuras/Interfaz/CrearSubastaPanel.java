package uniandes.dpoo.estructuras.Interfaz;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import uniandes.dpoo.estructuras.Interfaz.utils.TablaCompradores;
import uniandes.dpoo.estructuras.Interfaz.utils.TablaPiezas;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.Subasta;

public class CrearSubastaPanel extends JPanel {

    private Controlador controlador;
    private JFrame frame;
    private MenuPanel menuPanel;
    private ArrayList<Pieza> listaPiezas = new ArrayList<Pieza>();
    private Subasta subasta = new Subasta();

    public CrearSubastaPanel(Controlador controlador, JFrame frame, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.frame = frame;
        this.menuPanel = menuPanel;

        setLayout(new BorderLayout());
        
        // Panel superior para la entrada de datos
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel lblPieza = new JLabel("Pieza:");
        JTextField txtPieza = new JTextField();
        txtPieza.setPreferredSize(new Dimension(200, 20));

        JLabel lblPrecioInicial = new JLabel("Precio inicial: ");
        JTextField txtPrecioInicial = new JTextField();
        txtPrecioInicial.setPreferredSize(new Dimension(200, 20));

        JLabel lblPrecioMinimo = new JLabel("Precio minimo: ");
        JTextField txtPrecioMinimo = new JTextField();
        txtPrecioMinimo.setPreferredSize(new Dimension(200, 20));

        JButton btnAgregarPieza = new JButton("Agregar");

        TablaPiezas tablaPiezas = new TablaPiezas();
        JScrollPane scrollPiezas = new JScrollPane(tablaPiezas);

        btnAgregarPieza.addActionListener(e -> {
            int idPieza = Integer.parseInt(txtPieza.getText());
            Pieza pieza = controlador.buscarPiezaPorId(idPieza);
            if (pieza != null) {
                try {
                    subasta.agregarPieza(pieza);
                    subasta.agregarValorInicial(idPieza, Integer.parseInt(txtPrecioInicial.getText()));
                    subasta.agregarValorMinimo(idPieza, Integer.parseInt(txtPrecioMinimo.getText()));
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "No se pudo guardar la pieza en la subasta", "Error", JOptionPane.ERROR_MESSAGE);
                }
                tablaPiezas.agregarPieza(pieza);
                txtPrecioMinimo.setText("");
                txtPrecioInicial.setText("");
                txtPieza.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró la pieza con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel piezasPanel = new JPanel(new BorderLayout());
        piezasPanel.add(txtPieza, BorderLayout.CENTER);
        piezasPanel.add(btnAgregarPieza, BorderLayout.EAST);

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblPrecioMinimo, gbc);
        gbc.gridx++;
        formPanel.add(txtPrecioMinimo, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(lblPrecioInicial, gbc);
        gbc.gridx++;
        formPanel.add(txtPrecioInicial, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(lblPieza, gbc);
        gbc.gridx++;
        formPanel.add(piezasPanel, gbc);
        
        JPanel tablasPanel = new JPanel(new BorderLayout());
        tablasPanel.add(scrollPiezas, BorderLayout.CENTER);

        // Panel para la sección de compradores
        JPanel compradorPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbcComprador = new GridBagConstraints();
        gbcComprador.insets = new Insets(5, 5, 5, 5);
        gbcComprador.anchor = GridBagConstraints.LINE_END;

        JLabel lblComprador = new JLabel("Comprador:");
        JTextField txtComprador = new JTextField();
        txtComprador.setPreferredSize(new Dimension(200, 20));
        JButton btnBuscarComprador = new JButton("Buscar");

        TablaCompradores tablaCompradores = new TablaCompradores();
        JScrollPane scrollCompradores = new JScrollPane(tablaCompradores);

        btnBuscarComprador.addActionListener(e -> {
            int idComprador = Integer.parseInt(txtComprador.getText());
            Comprador comprador = controlador.buscarCompradorPorId(idComprador);
            if (comprador != null) {
                tablaCompradores.agregarComprador(comprador);
                try {
                    subasta.agregarComprador(comprador);
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(frame, "No se pudo guardar el comprador en la subasta", "Error", JOptionPane.ERROR_MESSAGE);
                }
                txtComprador.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró el comprador con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel panelComprador = new JPanel(new BorderLayout());
        panelComprador.add(txtComprador, BorderLayout.CENTER);
        panelComprador.add(btnBuscarComprador, BorderLayout.EAST);

        gbcComprador.gridx = 0;
        gbcComprador.gridy = 0;
        compradorPanel.add(lblComprador, gbcComprador);
        gbcComprador.gridx++;
        compradorPanel.add(panelComprador, gbcComprador);

        JPanel compradorTablePanel = new JPanel(new BorderLayout());
        compradorTablePanel.add(scrollCompradores, BorderLayout.CENTER);

        // Botón de guardar
        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> {
            guardarDatos();
            JOptionPane.showMessageDialog(frame, "Subasta guardada exitosamente", "Subasta guardada", JOptionPane.INFORMATION_MESSAGE);
            menuPanel.cambiarPanel(new EjecutarSubastaPanel(subasta, controlador, menuPanel));
        });

        // Agregar todo al panel principal
        add(formPanel, BorderLayout.NORTH);
        add(tablasPanel, BorderLayout.CENTER);
        add(compradorPanel, BorderLayout.WEST);
        add(compradorTablePanel, BorderLayout.EAST);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    public void guardarDatos(){
        try {
            controlador.agregarSubasta(subasta);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar la subasta", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
