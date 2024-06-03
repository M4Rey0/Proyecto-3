package uniandes.dpoo.estructuras.Interfaz;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

import uniandes.dpoo.estructuras.Exceptions.SobreValorException;
import uniandes.dpoo.estructuras.Interfaz.utils.PagarFrame;
import uniandes.dpoo.estructuras.Interfaz.utils.TablaPiezas;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.model.Pieza;

public class CrearVentaPanel extends JPanel{

    private Controlador controlador;
    private JFrame frame;
    private MenuPanel menuPanel;
    private ArrayList<Pieza> listaPiezas = new ArrayList<Pieza>();

    public CrearVentaPanel(Controlador controlador, JFrame frame, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.frame = frame;
        this.menuPanel = menuPanel;
        
        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel lblComprador = new JLabel("Comprador:");
        JTextField txtComprador = new JTextField();
        txtComprador.setPreferredSize(new Dimension(200, 20));

        JLabel lblMedioPago = new JLabel("Medio de Pago:");
        JComboBox<String> cmbMedioPago = new JComboBox<String>(controlador.getMediosPagoNombre());

        JLabel lblPiezas = new JLabel("ID Pieza:");
        JTextField txtPiezas = new JTextField();
        txtPiezas.setPreferredSize(new Dimension(200, 20));

        JButton btnAgregarPieza = new JButton("Agregar");

        TablaPiezas tablaPiezas = new TablaPiezas();
    
        btnAgregarPieza.addActionListener(e -> {
            int idPieza = Integer.parseInt(txtPiezas.getText());
            Pieza pieza = controlador.buscarPiezaPorId(idPieza);
            if (pieza != null) {
                listaPiezas.add(pieza);
                tablaPiezas.agregarPieza(pieza);
                txtPiezas.setText("");
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró la pieza con el ID ingresado", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        JPanel piezasPanel = new JPanel(new BorderLayout());
        piezasPanel.add(txtPiezas, BorderLayout.WEST);
        piezasPanel.add(btnAgregarPieza, BorderLayout.EAST);

        JButton btnGuardar = new JButton("Guardar");

        btnGuardar.addActionListener(e -> {
            guardarDatos(Integer.parseInt(txtComprador.getText()), (String) cmbMedioPago.getSelectedItem(), listaPiezas);
        });

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblComprador, gbc);
        gbc.gridx++;
        formPanel.add(txtComprador, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(lblMedioPago, gbc);
        gbc.gridx++;
        formPanel.add(cmbMedioPago, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(lblPiezas, gbc);
        gbc.gridx++;
        formPanel.add(piezasPanel, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        formPanel.add(tablaPiezas, gbc);
        gbc.gridy++;
        gbc.gridx = 1;
        formPanel.add(btnGuardar, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    public void guardarDatos(int idComprador, String medioPago, ArrayList<Pieza> piezas){
        MedioDePago medio = controlador.buscarMedioPago(medioPago);
        int valor = 0;
        Compra compra = new Compra();
        Comprador comprador = controlador.buscarCompradorPorId(idComprador);
        compra.setComprador(idComprador);
        compra.setMedioDePago(medio.getTipo());
        compra.setMediante(Compra.VENTA_DIRECTA);

        
        try {
            for (Pieza pieza : piezas) {
                valor += pieza.getValor();
                compra.agregarPieza(pieza, comprador);
            }

            compra.setValor(valor);

            controlador.agregarCompra(compra);
            PagarFrame pagarFrame = new PagarFrame(compra.getMedioDePago(), compra.getValor(), compra.getId());

            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());

        } catch (SobreValorException e) {
            JOptionPane.showMessageDialog(frame, "El valor de la compra sobrepasa el limite del cliente", "Error", JOptionPane.ERROR_MESSAGE);
            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error al agregar las piezas", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }

}
