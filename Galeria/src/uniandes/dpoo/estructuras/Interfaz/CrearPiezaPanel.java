package uniandes.dpoo.estructuras.Interfaz;

import javax.swing.*;
import java.awt.*;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Audiovisual;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Pieza;

public class CrearPiezaPanel extends JPanel {

    Controlador controlador;
    MenuPanel menuPanel;
    JFrame frame;

    public CrearPiezaPanel(Controlador controlador, JFrame frame, MenuPanel menuPanel) {
        this.controlador = controlador;
        this.frame = frame;
        this.menuPanel = menuPanel;

        setLayout(new BorderLayout());
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        // Existing fields
        JLabel lblId = new JLabel("ID:");
        JTextField txtId = new JTextField();
        txtId.setPreferredSize(new Dimension(200, 20));

        JLabel lblTitulo = new JLabel("Nombre:");
        JTextField txtTitulo = new JTextField();
        txtTitulo.setPreferredSize(new Dimension(200, 20));

        JLabel lblFecha = new JLabel("Fecha de creación:");
        JPanel datePanel = new JPanel(new GridLayout(1, 3, 5, 0));

        JComboBox<String> cmbDia = new JComboBox<>();
        for (int i = 1; i <= 31; i++) {
            cmbDia.addItem(String.valueOf(i));
        }
        JComboBox<String> cmbMes = new JComboBox<>();
        for (int i = 1; i <= 12; i++) {
            cmbMes.addItem(String.valueOf(i));
        }
        JComboBox<String> cmbAno = new JComboBox<>();
        for (int i = 1500; i <= 2024; i++) {
            cmbAno.addItem(String.valueOf(i));
        }

        datePanel.add(cmbDia);
        datePanel.add(cmbMes);
        datePanel.add(cmbAno);

        JLabel lblAutor = new JLabel("Autor:");
        JTextField txtAutor = new JTextField();
        txtAutor.setPreferredSize(new Dimension(200, 20));

        // New fields
        JLabel lblLugar = new JLabel("Lugar de creación:");
        JTextField txtLugar = new JTextField();
        txtLugar.setPreferredSize(new Dimension(200, 20));

        JLabel lblValor = new JLabel("Valor:");
        JTextField txtValor = new JTextField();
        txtValor.setPreferredSize(new Dimension(200, 20));

        JLabel lblDisponibilidad = new JLabel("Disponibilidad para venta:");
        JComboBox<String> cmbDisponibilidad = new JComboBox<>(new String[]{"Sí", "No"});

        JLabel lblEstado = new JLabel("Estado:");
        JComboBox<String> cmbEstado = new JComboBox<>(new String[]{"Vendida", "Bodega", "Exhibida"});

        JLabel lblTipo = new JLabel("Tipo de pieza:");
        JComboBox<String> cmbTipo = new JComboBox<>(new String[]{"Escultura", "Obra en papel", "Audiovisual"});

        // Adding components to formPanel
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(lblId, gbc);
        gbc.gridx = 1;
        formPanel.add(txtId, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(lblTitulo, gbc);
        gbc.gridx = 1;
        formPanel.add(txtTitulo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(lblFecha, gbc);
        gbc.gridx = 1;
        formPanel.add(datePanel, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(lblAutor, gbc);
        gbc.gridx = 1;
        formPanel.add(txtAutor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        formPanel.add(lblLugar, gbc);
        gbc.gridx = 1;
        formPanel.add(txtLugar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        formPanel.add(lblValor, gbc);
        gbc.gridx = 1;
        formPanel.add(txtValor, gbc);

        gbc.gridx = 0;
        gbc.gridy = 6;
        formPanel.add(lblDisponibilidad, gbc);
        gbc.gridx = 1;
        formPanel.add(cmbDisponibilidad, gbc);

        gbc.gridx = 0;
        gbc.gridy = 7;
        formPanel.add(lblEstado, gbc);
        gbc.gridx = 1;
        formPanel.add(cmbEstado, gbc);

        gbc.gridx = 0;
        gbc.gridy = 8;
        formPanel.add(lblTipo, gbc);
        gbc.gridx = 1;
        formPanel.add(cmbTipo, gbc);

        // Save button
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(100, 30));
        btnGuardar.addActionListener(e -> {
            String id = txtId.getText();
            String titulo = txtTitulo.getText();
            int dia = Integer.parseInt((String) cmbDia.getSelectedItem());
            int mes = Integer.parseInt((String) cmbMes.getSelectedItem());
            int ano = Integer.parseInt((String) cmbAno.getSelectedItem());
            String autor = txtAutor.getText();
            String lugar = txtLugar.getText();
            double valor = Double.parseDouble(txtValor.getText());
            boolean disponibilidad = cmbDisponibilidad.getSelectedItem().equals("Sí");
            String estado = (String) cmbEstado.getSelectedItem();
            String tipo = (String) cmbTipo.getSelectedItem();

            guardarDatos(id, titulo, dia, mes, ano, autor, lugar, valor, disponibilidad, estado, tipo);
        });

        gbc.gridx = 0;
        gbc.gridy = 9;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        formPanel.add(btnGuardar, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void guardarDatos(String id, String titulo, int dia, int mes, int ano, String autor, String lugar, double valor, boolean disponibilidad, String estado, String tipo) {
        Pieza pieza = new Pieza();
        pieza.setId(Integer.parseInt(id));
        pieza.setTitulo(titulo);
        pieza.setAnio(ano);
        pieza.setLugarCreacion(lugar);
        pieza.setValor((int) valor);
        pieza.setDisponible(disponibilidad);
        pieza.setEstado(estado);

        Autor autorIngresar = controlador.buscarAutorPorNombre(autor);

        if (autorIngresar == null) {
            autorIngresar = new Autor(autor, "Desconocido");
            try {
                controlador.agregarAutor(autorIngresar);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(frame, "Error al guardar el autor", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        try {
            pieza.agregarAutor(autorIngresar);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Error al guardar el autor", "Error", JOptionPane.ERROR_MESSAGE);
        }
        

        if (tipo.equals("Escultura")){
            panelEscultura(pieza);
        } else if (tipo.equals("Obra en papel")){   
            panelObraEnPapel(pieza);
        } else if (tipo.equals("Audiovisual")){
            panelAudiovisual(pieza);
        }
    }

    private void panelEscultura(Pieza pieza){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel lblMaterial = new JLabel("Material:");
        JTextField txtMaterial = new JTextField();
        txtMaterial.setPreferredSize(new Dimension(200, 20));

        JLabel lblAltura = new JLabel("Altura:");
        JTextField txtAltura = new JTextField();
        txtAltura.setPreferredSize(new Dimension(200, 20));

        JLabel lblAnchura = new JLabel("Anchura:");
        JTextField txtAnchura = new JTextField();
        txtAnchura.setPreferredSize(new Dimension(200, 20));

        JLabel lblProfundidad = new JLabel("Profundidad:");
        JTextField txtProfundidad = new JTextField();
        txtProfundidad.setPreferredSize(new Dimension(200, 20));

        JLabel lblElectricidad = new JLabel("Requiere electricidad:");
        JComboBox<String> cmbElectricidad = new JComboBox<>(new String[]{"Sí", "No"});
        cmbElectricidad.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;  
        panel.add(lblMaterial, gbc);
        gbc.gridx ++;
        panel.add(txtMaterial, gbc);
        
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblAltura, gbc);
        gbc.gridx++;
        panel.add(txtAltura, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblAnchura, gbc);
        gbc.gridx++;
        panel.add(txtAnchura, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblProfundidad, gbc);
        gbc.gridx++;
        panel.add(txtProfundidad, gbc);
        
        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblElectricidad, gbc);
        gbc.gridx++;
        panel.add(cmbElectricidad, gbc);
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(100, 30));

        btnGuardar.addActionListener(e -> {
            String material = txtMaterial.getText();
            int altura = Integer.parseInt(txtAltura.getText());
            int anchura = Integer.parseInt(txtAnchura.getText());
            int profundidad = Integer.parseInt(txtProfundidad.getText());
            boolean electricidad = cmbElectricidad.getSelectedItem().equals("Sí");

            Escultura escultura = new Escultura(pieza, altura, anchura, profundidad, material, electricidad);

            try {
                controlador.agregarPieza(escultura);
                JOptionPane.showMessageDialog(frame, "Pieza guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al guardar la pieza", "Error", JOptionPane.ERROR_MESSAGE);
            }

            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        menuPanel.cambiarPanel(panel);

    }

    private void panelObraEnPapel(Pieza pieza){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel lblTipo = new JLabel("Tipo:");
        JTextField txtTipo = new JTextField();
        txtTipo.setPreferredSize(new Dimension(200, 20));

        JLabel lblFormato = new JLabel("Formato:");
        JTextField txtFormato = new JTextField();
        txtFormato.setPreferredSize(new Dimension(200, 20));

        JLabel lblMaterial = new JLabel("Material:");
        JTextField txtMaterial = new JTextField();
        txtMaterial.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblTipo, gbc);
        gbc.gridx++;
        panel.add(txtTipo, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblFormato, gbc);
        gbc.gridx++;
        panel.add(txtFormato, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblMaterial, gbc);
        gbc.gridx++;
        panel.add(txtMaterial, gbc);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(100, 30));

        btnGuardar.addActionListener(e -> {
            String tipo = txtTipo.getText();
            String formato = txtFormato.getText();
            String material = txtMaterial.getText();

            ObraEnPapel obraEnPapel = new ObraEnPapel(pieza, tipo, formato, material);

            try {
                controlador.agregarPieza(obraEnPapel);
                JOptionPane.showMessageDialog(frame, "Pieza guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al guardar la pieza", "Error", JOptionPane.ERROR_MESSAGE);
            }

            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        menuPanel.cambiarPanel(panel);
    }

    private void panelAudiovisual(Pieza pieza){
        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.LINE_END;

        JLabel lblDuracion = new JLabel("Duración:");
        JTextField txtDuracion = new JTextField();
        txtDuracion.setPreferredSize(new Dimension(200, 20));

        JLabel lblFormato = new JLabel("Formato:");
        JTextField txtFormato = new JTextField();
        txtFormato.setPreferredSize(new Dimension(200, 20));

        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(lblDuracion, gbc);
        gbc.gridx++;
        panel.add(txtDuracion, gbc);

        gbc.gridy++;
        gbc.gridx = 0;
        panel.add(lblFormato, gbc);
        gbc.gridx++;
        panel.add(txtFormato, gbc);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.setPreferredSize(new Dimension(100, 30));

        btnGuardar.addActionListener(e -> {
            int duracion = Integer.parseInt(txtDuracion.getText());
            String formato = txtFormato.getText();

            Audiovisual audiovisual = new Audiovisual(pieza, duracion, formato);

            try {
                controlador.agregarPieza(audiovisual);
                JOptionPane.showMessageDialog(frame, "Pieza guardada exitosamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Error al guardar la pieza", "Error", JOptionPane.ERROR_MESSAGE);
            }

            menuPanel.cambiarPanel(menuPanel.crearPanelCrear());
        });

        gbc.gridy++;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(btnGuardar, gbc);

        menuPanel.cambiarPanel(panel);
    }
}
