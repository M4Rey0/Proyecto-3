package uniandes.dpoo.estructuras.model;

public class Audiovisual extends Pieza {
    private int duracion;
    private String formato;

    public Audiovisual (){
        super();
    }

    public Audiovisual(Pieza pieza, int duracion, String formato){
        super(pieza);
        this.duracion = duracion;
        this.formato = formato;
    }

    @Override
    public String toString() {
        return super.toString() + ",Audiovisual," + duracion + "," + formato;
    }

    public int getDuracion() {
        return duracion;
    }

    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }
}
