package uniandes.dpoo.estructuras.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subasta {
    private static int nextId = 1;
    private int id;
    private Map<Integer, Integer> valoresMinimos = new HashMap<>();
    private Map<Integer, Integer> valoresIniciales = new HashMap<>();
    private List<Integer> piezas = new ArrayList<>();
    private List<Integer> compradores = new ArrayList<>();

    public Subasta() {
        this.id = nextId++;
    }

    public Subasta(int id, Map<Integer, Integer> valoresMinimos, Map<Integer, Integer> valoresIniciales, List<Integer> piezas, List<Integer> compradores) {
        this.valoresMinimos = valoresMinimos;
        this.valoresIniciales = valoresIniciales;
        this.piezas = piezas;
        this.compradores = compradores;
        this.id = id;
    }

    public void agregarPieza(Pieza pieza) throws Exception {
        if (piezas.contains(pieza.getId())) {
            throw new Exception("La pieza ya se encuentra en la lista");
        }
        piezas.add(pieza.getId());
    }

    public void quitarPieza(Pieza pieza) {
        piezas.remove(pieza.getId());
    }

    public void agregarComprador(Comprador comprador) throws Exception {
        if (compradores.contains(comprador.getId())) {
            throw new Exception("El comprador ya se encuentra en la lista");
        }
        compradores.add(comprador.getId());
    }

    public int getValorMinimo(int idPieza) {
        return valoresMinimos.get(idPieza);
    }

    public int getValorInicial(int idPieza) {
        return valoresIniciales.get(idPieza);
    }

    @Override 
    public String toString() {
        String retorno = "";
        retorno += id + ",";
        for (Integer pieza : piezas) {
            retorno += pieza + "-";
        }

        retorno += ",";

        for (Integer comprador : compradores) {
            retorno += comprador + "-";
        }

        retorno += ",";
        for (Map.Entry<Integer, Integer> entry : valoresMinimos.entrySet()) {
            retorno += entry.getKey() + ";" + entry.getValue() + "-";
        }

        retorno += ",";
        for (Map.Entry<Integer, Integer> entry : valoresIniciales.entrySet()) {
            retorno += entry.getKey() + ";" + entry.getValue() + "-";
        }

        return retorno; 
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Subasta) {
            Subasta subasta = (Subasta) obj;
            return subasta.getId() == this.getId();
        }
        return false;
    }

    public void quitarComprador(Comprador comprador) {
        compradores.remove(comprador.getId());
    }

    public void agregarValorMinimo(int idPieza, int valorMinimo) {
        valoresMinimos.put(idPieza, valorMinimo);
    }

    public void agregarValorInicial(int idPieza, int valorInicial) {
        valoresIniciales.put(idPieza, valorInicial);
    }

    public Map<Integer, Integer> getValoresMinimos() {
        return valoresMinimos;
    }

    public void setValoresMinimos(Map<Integer, Integer> valoresMinimos) {
        this.valoresMinimos = valoresMinimos;
    }

    public Map<Integer, Integer> getValoresIniciales() {
        return valoresIniciales;
    }

    public void setValoresIniciales(Map<Integer, Integer> valoresIniciales) {
        this.valoresIniciales = valoresIniciales;
    }

    public List<Integer> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<Integer> piezas) {
        this.piezas = piezas;
    }

    public List<Integer> getCompradores() {
        return compradores;
    }

    public void setCompradores(List<Integer> compradores) {
        this.compradores = compradores;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean contienePieza(Pieza buscarPiezaPorId) {
        if (piezas.contains(buscarPiezaPorId.getId())) {
            return true;
        } else {
            return false;
        }
    }
}
