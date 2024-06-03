package uniandes.dpoo.estructuras.Inventarios;

import java.util.ArrayList;
import java.util.Map;

import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.Subasta;
import uniandes.dpoo.estructuras.model.Usuario;


public class Inventario {

    private InventarioPiezas piezas;
    private InventarioCompras compras;
    private InventarioSubastas subastas;
    private InventarioUsuarios usuarios;
    private InventarioAutores autores;
    private InventarioMediosPago mediosPago;

    public Inventario() {
        piezas = new InventarioPiezas();
        compras = new InventarioCompras();
        subastas = new InventarioSubastas();
        usuarios = new InventarioUsuarios();
        autores = new InventarioAutores();
        mediosPago = new InventarioMediosPago();
    }

    public void agregarPieza(Pieza pieza) {
        piezas.agregarPieza(pieza);
    }

    public void quitarPieza(Pieza pieza) throws Exception {
        piezas.quitarPieza(pieza);
        usuarios.borrarPiezaDePropietario(pieza.getId());
    }

    public void actualizarPieza(Pieza pieza) throws Exception {
        piezas.actualizarPieza(pieza);
    }

    public Pieza buscarPiezaPorId(int id) {
        return piezas.buscarPiezaPorId(id);
    }

    public Pieza buscarPiezaPorTitulo(String titulo) {
        return piezas.buscarPiezaPorTitulo(titulo);
    }

    public ArrayList<Pieza> buscarPiezasPorAutor(Autor autor) {
        return piezas.buscarPiezasPorAutor(autor);
    }
    
    public void agregarCompra(Compra compra) {
        compras.agregarCompra(compra);
    }

    public void quitarCompra(Compra compra) throws Exception {
        compras.quitarCompra(compra);
    }

    public Compra buscarCompraPorId(int id) {
        return compras.getCompraPorId(id);
    }

    public void agregarSubasta(Subasta subasta) {
        subastas.agregarSubasta(subasta);
    }

    public void quitarSubasta(Subasta subasta) throws Exception {
        subastas.quitarSubasta(subasta.getId());
    }

    public Subasta buscarSubastaPorId(int id) {
        return subastas.buscarSubastaPorId(id);
    }

    public void agregarUsuario(Usuario usuario) {
        usuarios.agregarUsuario(usuario);
    }

    public void quitarUsuario(Usuario usuario) throws Exception {
        usuarios.quitarUsuario(usuario);
    }

    public void actualizarUsuario(Usuario usuario) throws Exception {
        usuarios.actualizarUsuario(usuario);
    }

    public Usuario buscarUsuarioPorId(int id) {
        return usuarios.buscarUsuarioPorId(id);
    }

    public Usuario buscarUsuarioPorLogin(String login) {
        return usuarios.buscarUsuarioPorLogin(login);
    }

    public boolean login (String login, String password) {
        return usuarios.login(login, password);
    }

    public void agregarAutor(Autor autor) {
        autores.agregarAutor(autor);
    }

    public void quitarAutor(Autor autor) throws Exception {
        autores.quitarAutor(autor);
    }

    public Autor buscarAutor(String nombre){
        return autores.buscarAutorPorNombre(nombre);
    }

    public void agregarMedioPago(MedioDePago medioPago) {
        mediosPago.agregarMedioPago(medioPago);
    }

    public void quitarMedioPago(MedioDePago medioPago) throws Exception {
        mediosPago.quitarMedioPago(medioPago);
    }

    public MedioDePago buscarMedioPago(String tipo) {
        return mediosPago.buscarMedioPago(tipo);
    }

    public void agregarComprador(Comprador comprador){
        usuarios.agregarComprador(comprador);
    }

    public void quitarComprador(Comprador comprador) throws Exception {
        usuarios.quitarComprador(comprador);
    }

    public Comprador buscarCompradorPorId(int id) {
        return usuarios.buscarCompradorPorId(id);
    }

    public void agregarEmpleado(Empleado empleado) {
        usuarios.agregarEmpleado(empleado);
    }

    public void quitarEmpleado(Empleado empleado) throws Exception {
        usuarios.quitarEmpleado(empleado);
    }

    public void actualizarEmpleado(Empleado empleado) throws Exception {
        usuarios.actualizarEmpleado(empleado);
    }

    public Empleado buscarEmpleadoPorId(int id) {
        return usuarios.buscarEmpleadoPorId(id);
    }

    public InventarioPiezas getPiezas() {
        return piezas;
    }

    public void setPiezas(InventarioPiezas piezas) {
        this.piezas = piezas;
    }

    public InventarioCompras getCompras() {
        return compras;
    }

    public void setCompras(InventarioCompras compras) {
        this.compras = compras;
    }

    public InventarioSubastas getSubastas() {
        return subastas;
    }

    public void setSubastas(InventarioSubastas subastas) {
        this.subastas = subastas;
    }

    public InventarioUsuarios getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(InventarioUsuarios usuarios) {
        this.usuarios = usuarios;
    }

    public InventarioAutores getAutores() {
        return autores;
    }

    public void setAutores(InventarioAutores autores) {
        this.autores = autores;
    }

    public InventarioMediosPago getMediosPago() {
        return mediosPago;
    }

    public void setMediosPago(InventarioMediosPago mediosPago) {
        this.mediosPago = mediosPago;
    }

    public ArrayList<String> getTiposMediosPago() {
        return (ArrayList<String>) mediosPago.getTiposMediosPago();
    }

    public ArrayList<Pieza> getPiezasPorAutor(Autor autor) {
        return piezas.buscarPiezasPorAutor(autor);
    }

    public String[] getMediosPagoNombre() {
        return mediosPago.getMediosPagoNombre();
    }

    public ArrayList<Compra> buscarComprasPorPieza(int piezaId) {
        Pieza pieza = piezas.buscarPiezaPorId(piezaId);
        ArrayList<Compra> retorno = new ArrayList<>();
        for (Integer idCompra : pieza.getCompras()) {
            retorno.add(compras.getCompraPorId(idCompra));
        }

        return retorno;
    }

    public ArrayList<Comprador> buscarCompradoresPorPieza(int idPieza) {
        Pieza pieza = piezas.buscarPiezaPorId(idPieza);
        ArrayList<Comprador> retorno = new ArrayList<>();
        for (Integer idComprador : pieza.getPropietarios().values()) {
            Comprador comprador = usuarios.buscarCompradorPorId(idComprador);
            retorno.add(comprador);
        }

        return retorno;
    }

    public ArrayList<Pieza> buscarPiezasPorComprador(int id) {
        ArrayList<Pieza> retorno = new ArrayList<>();
        Comprador comprador = usuarios.buscarCompradorPorId(id);

        for (int idPieza : comprador.getPiezas()) {
            retorno.add(piezas.buscarPiezaPorId(idPieza));
        }

        return retorno;
    }

    public ArrayList<Compra> buscarComprasPorComprador(int id) {
        return compras.buscarComprasPorComprador(id);
    }

    public Map<Integer, Integer> buscarVentasPorMes() {
        return compras.buscarVentasPorMes();
    }
}
