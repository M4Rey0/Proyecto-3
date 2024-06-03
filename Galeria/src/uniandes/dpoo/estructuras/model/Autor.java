package uniandes.dpoo.estructuras.model;

public class Autor {
    private String nombre;
    private String tipo;


    public Autor(String nombre, String tipo) {
        this.nombre = nombre;
        this.tipo = tipo;
    }

    @Override
    public String toString() {
        return nombre 
                + "," 
                + tipo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Autor){
            Autor autor = (Autor) obj;
            return autor.getNombre().equals(this.getNombre());
        }
        return false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
