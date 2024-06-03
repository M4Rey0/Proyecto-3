package uniandes.dpoo.estructuras.Interfaz.utils.mediosPago;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MasterCardPanel extends JPanel{

    public MasterCardPanel(int valor, JFrame frame, int idCompra) {

        setLayout(new BorderLayout());

        JLabel label = new JLabel("Pago con MasterCard por valor de: " + valor);
        add(label, BorderLayout.NORTH);

        JPanel southPanel = new JPanel();
        southPanel.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 0;

        JLabel label2 = new JLabel("Ingrese los datos de su tarjeta");
        southPanel.add(label2);
        gbc.gridy++;

        JLabel lblTarjeta = new JLabel("Numero de tarjeta");
        southPanel.add(lblTarjeta, gbc);
        gbc.gridy++;

        JTextField txtTarjeta = new JTextField();
        txtTarjeta.setPreferredSize(new Dimension(200,20));
        southPanel.add(txtTarjeta, gbc);

        JButton btnPagar = new JButton("Pagar");
        btnPagar.addActionListener(e -> {
            System.out.println("Pago realizado con Mastercard");
            JOptionPane.showMessageDialog(this, "Pago realizado con Mastercard", "Pago", JOptionPane.INFORMATION_MESSAGE);
            // aqui iria toda la logica con la API para hacer el pago real
            MediosPagoUtils.guardar("Mastercard", idCompra);
            frame.dispose();
        });

        gbc.gridx++;
        southPanel.add(btnPagar, gbc);

        add(southPanel, BorderLayout.CENTER);

        setVisible(true);
    }
}
