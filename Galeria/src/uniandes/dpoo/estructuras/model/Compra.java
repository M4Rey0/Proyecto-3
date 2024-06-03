package uniandes.dpoo.estructuras.model;

import java.time.LocalDate;
import java.util.ArrayList;

import uniandes.dpoo.estructuras.Exceptions.SobreValorException;

public class Compra {

    private static int nextId = 1;

    private int id;

    private ArrayList<Integer> piezas = new ArrayList<>();

    private String medioDePago;

    private int valor = 0;

    public static final String SUBASTA = "subasta";
    public static final String VENTA_DIRECTA = "venta directa";

    private String mediante;

    private Integer comprador;

    private LocalDate fechaCompra;

    public Compra() {
        nextId++;
        this.id = nextId;
        this.fechaCompra = LocalDate.now();
    }
    // utilizado en las subastas
    public Compra(Pieza pieza, MedioDePago medioDePago, int valor, Comprador comprador){
        this.id = nextId++;
        this.piezas.add(pieza.getId());
        this.medioDePago = medioDePago.getTipo();
        this.mediante = SUBASTA;
        this.valor = valor;
        this.comprador = comprador.getId();
        this.fechaCompra = LocalDate.now();
    }

    public Compra(int id, ArrayList<Integer> piezas, MedioDePago medioDePago, int valor, String mediante) {
        this.id = id;
        this.piezas = piezas;
        this.medioDePago = medioDePago.getTipo();
        this.valor = valor;
        this.mediante = mediante;
        this.fechaCompra = LocalDate.now();
        nextId ++;
    }

    @Override
    public String toString() {
        String retorno = "";
        retorno += id + ",";
        for (Integer pieza : piezas) {
            retorno += pieza + "-";
        }
        retorno += ",";
        retorno += medioDePago + ",";
        retorno += valor + ",";
        retorno += mediante + ",";
        retorno += comprador + ",";
        retorno += fechaCompra + ",";
        return retorno;

    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Compra) {
            Compra compra = (Compra) obj;
            return compra.getId() == this.getId();
        }
        return false;
    }

    public void agregarPieza(Pieza pieza, Comprador comprador)throws SobreValorException, Exception{

        int precio = pieza.getValor();

        if (piezas.contains(pieza.getId())){
            throw new Exception("La pieza ya se encuentra en la compra.");
        }

        if (precio + valor > comprador.getLimiteCompra()){
            throw new SobreValorException("El valor de la compra supera el limite de compra del comprador.");
        } else{
            piezas.add(pieza.getId());
            valor += pieza.getValor();
        }
    }

    public void eliminarPieza(Pieza pieza){
        piezas.remove(pieza.getId());
        valor -= pieza.getValor();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<Integer> getPiezas() {
        return piezas;
    }

    public void setPiezas(ArrayList<Integer> piezas) {
        this.piezas = piezas;
    }

    public String getMedioDePago() {
        return medioDePago;
    }

    public void setMedioDePago(String medioDePago) {
        this.medioDePago = medioDePago;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public String getMediante() {
        return mediante;
    }

    public void setMediante(String mediante) {
        this.mediante = mediante;
    }

    public int getComprador() {
        return comprador;
    }

    public void setComprador(int comprador) {
        this.comprador = comprador;
    }
    public boolean contienePieza(Pieza pieza) {
        return piezas.contains(pieza.getId());
    }

    public LocalDate getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(LocalDate fechaCompra) {
        this.fechaCompra = fechaCompra;
    }
}
