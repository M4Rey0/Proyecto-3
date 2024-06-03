package uniandes.dpoo.estructuras.model.mediosPago;

import uniandes.dpoo.estructuras.model.MedioDePago;

public class Mastercard extends MedioDePago {
    private String numeroTarjeta;
    private String cvv;
    private String fechaExpiracion;
    public final static String TIPO = "Mastercard";

    public Mastercard(String[] contenido){
        this.numeroTarjeta = contenido[0];
        this.cvv = contenido[1];
        this.fechaExpiracion = contenido[2];
        this.tipo = TIPO;
    }

    public Mastercard(){
        this.tipo = TIPO;
    }

    public void cargar(String[] contenido){
        this.numeroTarjeta = contenido[0];
        this.cvv = contenido[1];
        this.fechaExpiracion = contenido[2];
    }

    @Override
    public String toString() {
        return numeroTarjeta + "," + cvv + "," + fechaExpiracion + ",";
    }

}
