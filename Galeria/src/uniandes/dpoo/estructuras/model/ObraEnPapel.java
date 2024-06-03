package uniandes.dpoo.estructuras.model;

public class ObraEnPapel extends Pieza{
    private String tipo;
    private String formato;
    private String material;

    public ObraEnPapel (){
        super();
    }

    public ObraEnPapel(Pieza pieza, String tipo, String formato, String material){
        super(pieza);
        this.tipo = tipo;
        this.formato = formato;
        this.material = material;
    }

    @Override
    public String toString() {
        return super.toString() + ",ObraEnPapel," + tipo + "," + formato + "," + material;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}
