package uniandes.dpoo.estructuras.Interfaz;

import javax.swing.JPanel;

import uniandes.dpoo.estructuras.Logica.Controlador;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;

public class ConsultarVentasPanel extends JPanel {
    private Map<Integer, Integer> ventasPorMes;

    public ConsultarVentasPanel(Controlador controlador, MenuPanel menuPanel) {
        this.ventasPorMes = controlador.buscarVentasPorMes();
        setPreferredSize(new Dimension(800, 600));
        setBackground(Color.WHITE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        int anchoPanel = getWidth();
        int altoPanel = getHeight();
        int padding = 40;
        int anchoGrafico = anchoPanel - 2 * padding;
        int altoGrafico = altoPanel - 2 * padding;
        int anchoBarra = anchoGrafico / 12;
        int maxGanancia = ventasPorMes.values().stream().max(Integer::compare).orElse(1);

        g.setColor(Color.BLACK);
        g.drawLine(padding, altoPanel - padding, anchoPanel - padding, altoPanel - padding);
        g.drawLine(padding, altoPanel - padding, padding, padding);

        for (int mes = 1; mes <= 12; mes++) {
            int ganancia = ventasPorMes.getOrDefault(mes, 0);
            int alturaBarra = (int) ((double) ganancia / maxGanancia * altoGrafico);
            int x = padding + (mes - 1) * anchoBarra;
            int y = altoPanel - padding - alturaBarra;

            g.setColor(Color.BLUE);
            g.fillRect(x, y, anchoBarra - 10, alturaBarra);

            g.setColor(Color.BLACK);
            g.drawRect(x, y, anchoBarra - 10, alturaBarra);

            String mesLabel = String.valueOf(mes);
            int labelWidth = g.getFontMetrics().stringWidth(mesLabel);
            g.drawString(mesLabel, x + (anchoBarra - 10) / 2 - labelWidth / 2, altoPanel - padding + 20);

            String gananciaLabel = String.valueOf(ganancia);
            int gananciaWidth = g.getFontMetrics().stringWidth(gananciaLabel);
            g.drawString(gananciaLabel, x + (anchoBarra - 10) / 2 - gananciaWidth / 2, y - 5);
        }
    }
}


