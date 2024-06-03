package uniandes.dpoo.estructuras.model;

public class Escultura extends Pieza{
    private int altura;
    private int anchura;
    private int profundidad;
    private String material;
    private Boolean electricidad;

    public Escultura (){
        super();
    }

    public Escultura(Pieza pieza, int altura, int anchura, int profundidad, String material, Boolean electricidad){
        super(pieza);
        this.altura = altura;
        this.anchura = anchura;
        this.profundidad = profundidad;
        this.material = material;
        this.electricidad = electricidad;
    }

    @Override
    public String toString() {
        return super.toString() + ",Escultura," + altura + "," + anchura + "," + profundidad + "," + material + "," + electricidad;
    }

    public int getAltura() {
        return altura;
    }

    public int getAnchura() {
        return anchura;
    }

    public int getProfundidad() {
        return profundidad;
    }

    public String getMaterial() {
        return material;
    }

    public Boolean getElectricidad() {
        return electricidad;
    }

    // Setters
    public void setAltura(int altura) {
        this.altura = altura;
    }

    public void setAnchura(int anchura) {
        this.anchura = anchura;
    }

    public void setProfundidad(int profundidad) {
        this.profundidad = profundidad;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public void setElectricidad(Boolean electricidad) {
        this.electricidad = electricidad;
    }
}
