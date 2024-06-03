package uniandes.dpoo.estructuras.Interfaz.utils;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import uniandes.dpoo.estructuras.Interfaz.utils.mediosPago.MasterCardPanel;
import uniandes.dpoo.estructuras.Interfaz.utils.mediosPago.NequiPanel;
import uniandes.dpoo.estructuras.Interfaz.utils.mediosPago.PayPalPanel;

public class PagarFrame extends JFrame{
    public PagarFrame(String medio, int valor, int idCompra){
        super("Pagar");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        switch (medio) {
            case "PayPal":
                this.add(new PayPalPanel(valor, this, idCompra));
                setVisible(true);
                break;

            case "Nequi":
                this.add(new NequiPanel(valor, this, idCompra));
                setVisible(true);
                break;

            case "Mastercard":
                this.add(new MasterCardPanel(valor, this, idCompra));
                setVisible(true);
                break;

            default:
                JOptionPane.showMessageDialog(this, "Medio de pago no soportado", "Error", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }
}
