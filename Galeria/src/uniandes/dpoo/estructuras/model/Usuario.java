package uniandes.dpoo.estructuras.model;

public class Usuario {

    protected static int nextId = 1;

    protected int id;
    protected String nombreUsuario;
    protected String password;
    protected String nombre;
    protected String telefono;
    protected String email;

    public static final String ADMIN = "admin";
    public static final String EMPLEADO = "usuario";
    public static final String PROPIETARIO = "propietario";
    public static final String COMPRADOR = "comprador";

    private String rol = "";

    public Usuario(int id, String nombreUsuario, String password, String nombre, String telefono, String email, String rol) {
        this.id = id;
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.rol = rol;
        nextId ++;
    }

    public Usuario(){
        this.id = nextId++;
    }

    public void agregarRol(String rol)throws Exception{
        if (this.rol.contains(rol)){
            throw new Exception("El usuario ya tiene el rol");
        }
        this.rol += rol;
    }

    public boolean verificarRol(String rol){
        if (this.rol.contains(rol)){
            return true;
        } else {
            return false;
        }
    }

    public boolean verificarLogin(String nombreUsuario, String password){
        if (this.nombreUsuario.equals(nombreUsuario) && this.password.equals(password)){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return
                id +
                "," + nombreUsuario +
                "," + password +
                "," + nombre +
                "," + telefono +
                "," + email +
                "," + rol ;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Usuario){
            Usuario usuario = (Usuario) obj;
            return usuario.getId() == this.id;
        } else {
            return false;
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

}
