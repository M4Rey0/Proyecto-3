package uniandes.dpoo.estructuras.Logica;

import java.util.ArrayList;
import java.util.HashMap;
import uniandes.dpoo.estructuras.model.Pieza;

public class LogicaSubastas {
    private HashMap<Integer, Pieza> piezasAgregadas = new HashMap<Integer, Pieza>();
    private HashMap<Integer, Integer> valoresMinimos = new HashMap<Integer, Integer>();
    FormatUtils formatUtils = new FormatUtils();

    public String cadenaPiezasAgregadas() {
        String cadena = "";
        for (Integer id : piezasAgregadas.keySet()) {
            cadena += piezasAgregadas.get(id).toString() + "\n";
        }
        return cadena;
    }

    public void agregarPieza(Pieza pieza, int valorInicial, int valorMinimo) {
        piezasAgregadas.put(pieza.getId(), pieza);
        valoresMinimos.put(pieza.getId(), valorMinimo);
    }

    public ArrayList<Pieza> getPiezasAgregadas() {
        ArrayList<Pieza> piezas = new ArrayList<Pieza>();
        for (Integer id : piezasAgregadas.keySet()) {
            piezas.add(piezasAgregadas.get(id));
        }
        return piezas;
    }

    public String getMostradorPiezas(){
        String cadena = "";
        for (Integer id : piezasAgregadas.keySet()) {
            cadena += formatUtils.mostradorPieza(piezasAgregadas.get(id));
            cadena += "\n";
        }
        return cadena;
    }
}
