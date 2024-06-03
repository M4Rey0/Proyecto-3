package uniandes.dpoo.estructuras.model.mediosPago;

import uniandes.dpoo.estructuras.model.MedioDePago;

public class PayPal extends MedioDePago{
    private String email;
    private String password;
    public final static String TIPO = "PayPal";

    public PayPal(String email, String password) {
        this.email = email;
        this.password = password;
        this.tipo = TIPO;
    }

    public PayPal(){
        this.tipo = TIPO;
    }

    public PayPal(String[] contenido){
        this.email = contenido[0];
        this.password = contenido[1];
        this.tipo = TIPO;
    }

    public void cargar(String[] contenido){
        this.email = contenido[0];
        this.password = contenido[1];
    }

    @Override
    public String toString() {
        return email + "," + password + ",";
    }
}
