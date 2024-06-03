package uniandes.dpoo.estructuras.Interfaz;

import javax.swing.*;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Usuario;
import java.awt.*;
import java.util.ArrayList;

public class CrearCompradorPanel extends JPanel {
    private Controlador controlador;
    private MenuPanel menuPanel;

    public CrearCompradorPanel(Controlador controlador, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.menuPanel = menuPanel;

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Login:"), gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Contraseña:"), gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Nombre:"), gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Teléfono:"), gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Email:"), gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Límite de compra:"), gbc);

        gbc.gridy++;
        formPanel.add(new JLabel("Verificado para subastas:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;

        JTextField txtLogin = new JTextField();
        txtLogin.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtLogin, gbc);

        gbc.gridy++;
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtPassword, gbc);

        gbc.gridy++;
        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtNombre, gbc);

        gbc.gridy++;
        JTextField txtTelefono = new JTextField();
        txtTelefono.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtTelefono, gbc);

        gbc.gridy++;
        JTextField txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtEmail, gbc);

        gbc.gridy++;
        JTextField txtLimiteCompra = new JTextField();
        txtLimiteCompra.setPreferredSize(new Dimension(200, 25));
        formPanel.add(txtLimiteCompra, gbc);

        gbc.gridy++;
        JComboBox<String> cmbVerificado = new JComboBox<>(new String[]{"Sí", "No"});
        cmbVerificado.setPreferredSize(new Dimension(200, 25));
        formPanel.add(cmbVerificado, gbc);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(120, 30));
        btnGuardar.addActionListener(e -> {
            String login = txtLogin.getText();
            String password = new String(txtPassword.getPassword());
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            int limiteCompra = Integer.parseInt(txtLimiteCompra.getText());
            boolean verificado = cmbVerificado.getSelectedItem().equals("Sí");

            guardarDatos(login, password, nombre, telefono, email, limiteCompra, verificado);

            JOptionPane.showMessageDialog(null, "Usuario creado satisfactoriamente", "Usuario creado", JOptionPane.INFORMATION_MESSAGE);
            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
            
        });

        add(formPanel, BorderLayout.CENTER);
        add(btnGuardar, BorderLayout.SOUTH);
    }

    private void guardarDatos(String login, String password, String nombre, String telefono, String email, int limiteCompra, boolean verificado) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(login);
        usuario.setPassword(password);
        usuario.setNombre(nombre);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setRol(Usuario.COMPRADOR);

        try {
            controlador.agregarUsuario(usuario);
            Comprador comprador = new Comprador(usuario, limiteCompra, verificado, new ArrayList<>());
            controlador.agregarComprador(comprador);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el comprador", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}



