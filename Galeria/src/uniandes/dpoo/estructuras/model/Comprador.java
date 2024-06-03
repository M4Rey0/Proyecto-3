package uniandes.dpoo.estructuras.model;

import java.util.ArrayList;
import java.util.List;

public class Comprador extends Usuario{
    private int limiteCompra;
    private boolean verificadoSubasta;
    private List<Integer> piezas = new ArrayList<>();

    public Comprador(int id, String nombreUsuario, String password, String nombre, String telefono, String email, String rol, int limiteCompra, boolean verificadoSubasta) {
        super(id, nombreUsuario, password, nombre, telefono, email, rol);
        this.limiteCompra = limiteCompra;
        this.verificadoSubasta = verificadoSubasta;
    }

    public Comprador(){
        super();
    }

    public Comprador (Usuario usuario, int limiteCompra, boolean verificadoSubasta, List<Integer> piezas){
        super(usuario.getId(), usuario.getNombreUsuario(), usuario.getPassword(), usuario.getNombre(), usuario.getTelefono(), usuario.getEmail(), usuario.getRol());
        this.limiteCompra = limiteCompra;
        this.verificadoSubasta = verificadoSubasta;
        this.piezas = piezas;
    }

    @Override 
    public String toString(){
        String retorno = "";
        retorno += id + ",";
        retorno += limiteCompra + ",";
        retorno += verificadoSubasta + ",";
        for (Integer pieza: piezas){
            retorno += pieza + "-";
        }
        retorno += ",";
        return retorno;
    }

    public int getLimiteCompra() {
        return limiteCompra;
    }

    public void setLimiteCompra(int limiteCompra) {
        this.limiteCompra = limiteCompra;
    }

    public boolean isVerificadoSubasta() {
        return verificadoSubasta;
    }

    public void setVerificadoSubasta(boolean verificadoSubasta) {
        this.verificadoSubasta = verificadoSubasta;
    }

    public List<Integer> getPiezas() {
        return piezas;
    }

    public void setPiezas(List<Integer> piezas) {
        this.piezas = piezas;
    }

    public void agregarPieza(int idPieza) throws Exception{
        if (piezas.contains(idPieza)){
            throw new Exception("La pieza ya se encuentra en la lista de piezas del comprador.");
        } else{
            piezas.add(idPieza);
        }
    }

    public void eliminarPieza(int idPieza) throws Exception{
        if (!piezas.contains(idPieza)){
            throw new Exception("La pieza no se encuentra en la lista de piezas del comprador.");
        }
        piezas.remove(idPieza);
    }
}
