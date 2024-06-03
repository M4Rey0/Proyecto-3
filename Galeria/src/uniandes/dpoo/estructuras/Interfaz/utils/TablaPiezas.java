package uniandes.dpoo.estructuras.Interfaz.utils;

import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import uniandes.dpoo.estructuras.model.Pieza;

import java.awt.*;

public class TablaPiezas extends JPanel {

    private JTable tabla;
    private DefaultTableModel modelo;

    public TablaPiezas() {
        setLayout(new BorderLayout());

        // Crear el modelo de la tabla
        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Título");
        modelo.addColumn("Año");
        modelo.addColumn("Lugar de creación");
        modelo.addColumn("Valor");
        modelo.addColumn("Disponible");
        modelo.addColumn("Estado");

        // Crear la tabla con el modelo
        tabla = new JTable(modelo);

        // Agregar la tabla a un JScrollPane para permitir el desplazamiento
        JScrollPane scrollPane = new JScrollPane(tabla);
        add(new JLabel("Piezas"), BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }

    public void agregarPieza(Pieza pieza) {
        Object[] fila = new Object[]{
                pieza.getId(),
                pieza.getTitulo(),
                pieza.getAnio(),
                pieza.getLugarCreacion(),
                pieza.getValor(),
                pieza.isDisponible(),
                pieza.getEstado()
        };
        modelo.addRow(fila);
        modelo.fireTableDataChanged();
    }

    public void agregarPiezas(ArrayList<Pieza> piezas) {
        for (Pieza pieza : piezas) {
            agregarPieza(pieza);
        }
    }

    public void limpiar(){
        modelo.setRowCount(0);
        modelo.fireTableDataChanged();
    }
}
