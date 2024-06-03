package uniandes.dpoo.estructuras.Interfaz.utils;

import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import uniandes.dpoo.estructuras.model.Compra;

public class TablaCompras extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    public TablaCompras() {
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Fecha");
        modelo.addColumn("Valor");

        // Crear la tabla con el modelo
        tabla = new JTable(modelo);

        // Agregar la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(new JLabel("Compras"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarCompra(Compra compra) {
        Object[] fila = new Object[]{
                compra.getId(),
                compra.getFechaCompra(),
                compra.getValor()
        };
        modelo.addRow(fila);
        modelo.fireTableDataChanged();
    }

    public void agregarCompras(ArrayList<Compra> compras) {
        for (Compra compra : compras) {
            agregarCompra(compra);
        }
    }

    public void limpiar(){
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
    }
}
