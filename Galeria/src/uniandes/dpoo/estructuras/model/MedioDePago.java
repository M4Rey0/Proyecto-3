package uniandes.dpoo.estructuras.model;

public abstract class MedioDePago {
    protected String tipo;

    public MedioDePago(String tipo) {
        this.tipo = tipo;
    }

    public MedioDePago(){
    }

    public abstract void cargar(String[] partes);

    @Override
    public abstract String toString();

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof MedioDePago){
            MedioDePago medioDePago = (MedioDePago) obj;
            return medioDePago.getTipo().equals(this.getTipo());
        }
        return false;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
