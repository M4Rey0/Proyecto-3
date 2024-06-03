package uniandes.dpoo.estructuras.Interfaz.utils;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uniandes.dpoo.estructuras.model.Comprador;

public class TablaCompradores extends JPanel{
    private JTable tabla;
    private DefaultTableModel modelo;

    public TablaCompradores() {
        setLayout(new BorderLayout());


        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Teléfono");
        modelo.addColumn("Email");

        tabla = new JTable(modelo);

        JScrollPane scrollPane = new JScrollPane(tabla);
        add(new JLabel("Compradores"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarComprador(Comprador comprador) {
        Object[] fila = new Object[]{
                comprador.getId(),
                comprador.getNombre(),
                comprador.getTelefono(),
                comprador.getEmail()
        };
        modelo.addRow(fila);
        modelo.fireTableDataChanged();
    }

    public void agregarCompradores(ArrayList<Comprador> compradores) {
        for (Comprador comprador : compradores) {
            agregarComprador(comprador);
        }
    }

    public void limpiar(){
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
    }
}
