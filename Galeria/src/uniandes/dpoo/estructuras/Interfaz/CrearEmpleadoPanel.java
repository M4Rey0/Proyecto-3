package uniandes.dpoo.estructuras.Interfaz;

import javax.swing.*;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

import java.awt.*;

public class CrearEmpleadoPanel extends JPanel {
    Controlador controlador;
    MenuPanel menuPanel;
    JFrame frame;

    public CrearEmpleadoPanel(Controlador controlador, JFrame frame, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.frame = frame;
        this.menuPanel = menuPanel;

        setLayout(new BorderLayout());

        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel lblLogin = new JLabel("Login:");
        JTextField txtLogin = new JTextField();
        txtLogin.setPreferredSize(new Dimension(200, 20));

        JLabel lblPassword = new JLabel("Contraseña:");
        JPasswordField txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(200, 20));

        JLabel lblNombre = new JLabel("Nombre:");
        JTextField txtNombre = new JTextField();
        txtNombre.setPreferredSize(new Dimension(200, 20));

        JLabel lblTelefono = new JLabel("Teléfono:");
        JTextField txtTelefono = new JTextField();
        txtTelefono.setPreferredSize(new Dimension(200, 20));

        JLabel lblEmail = new JLabel("Email:");
        JTextField txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(200, 20));

        JLabel lblCargo = new JLabel("Cargo:");
        JComboBox<String> cmbCargo = new JComboBox<>(new String[]{Empleado.ADMIN, Empleado.EMPLEADO});
        cmbCargo.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblLogin, gbc);
        gbc.gridy++;
        formPanel.add(lblPassword, gbc);
        gbc.gridy++;
        formPanel.add(lblNombre, gbc);
        gbc.gridy++;
        formPanel.add(lblTelefono, gbc);
        gbc.gridy++;
        formPanel.add(lblEmail, gbc);
        gbc.gridy++;
        formPanel.add(lblCargo, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        formPanel.add(txtLogin, gbc);
        
        gbc.gridy++;
        formPanel.add(txtPassword, gbc);
        gbc.gridy++;
        formPanel.add(txtNombre,gbc);
        gbc.gridy++;
        formPanel.add(txtTelefono,gbc);
        gbc.gridy++;
        formPanel.add(txtEmail,gbc);
        gbc.gridy++;
        formPanel.add(cmbCargo,gbc);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(120, 40));
        btnGuardar.addActionListener(e -> {
            String login = txtLogin.getText();
            String password = new String(txtPassword.getPassword());
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            String email = txtEmail.getText();
            String cargo = (String) cmbCargo.getSelectedItem();

            guardarDatos(login, password, nombre, telefono, email, cargo);
            JOptionPane.showMessageDialog(null, "Empleado guardado exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnGuardar);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void guardarDatos(String login, String password, String nombre, String telefono, String email, String cargo) {
        Usuario usuario = new Usuario();
        usuario.setNombreUsuario(login);
        usuario.setPassword(password);
        usuario.setNombre(nombre);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setRol(Usuario.EMPLEADO);

        try {
            controlador.agregarUsuario(usuario);
            Empleado empleado = new Empleado(usuario, cargo);
            controlador.agregarEmpleado(empleado);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "No se pudo guardar el empleado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

