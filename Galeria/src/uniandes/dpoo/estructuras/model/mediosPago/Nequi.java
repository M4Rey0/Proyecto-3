package uniandes.dpoo.estructuras.model.mediosPago;

import uniandes.dpoo.estructuras.model.MedioDePago;

public class Nequi extends MedioDePago{
    private String numeroTelefono;
    public final static String TIPO = "Nequi";

    public Nequi(String numeroTelefono) {
        this.numeroTelefono = numeroTelefono;
        this.tipo = TIPO;
    }

    public Nequi(){
        this.tipo = TIPO;
    }


    public void cargar(String[] contenido){
        this.numeroTelefono = contenido[0];
    }

    public Nequi(String[] contenido){
        this.numeroTelefono = contenido[0];
        this.tipo = TIPO;
    }

    @Override
    public String toString() {
        return numeroTelefono + ",";
    }

}
