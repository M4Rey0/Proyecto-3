package uniandes.dpoo.estructuras.model;

public class Empleado extends Usuario{
    private String cargo;

    public Empleado(int id, String nombreUsuario, String password, String nombre, String telefono, String email, String rol, String cargo) {
        super(id, nombreUsuario, password, nombre, telefono, email, rol);
        this.cargo = cargo;
    }

    public Empleado (Usuario usuario, String cargo){
        super(usuario.getId(), usuario.getNombreUsuario(), usuario.getPassword(), usuario.getNombre(), usuario.getTelefono(), usuario.getEmail(), usuario.getRol());
        this.cargo = cargo;
    }

    @Override
    public String toString(){
        return id + "," + cargo;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}
