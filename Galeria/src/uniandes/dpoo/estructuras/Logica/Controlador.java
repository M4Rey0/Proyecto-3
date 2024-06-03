package uniandes.dpoo.estructuras.Logica;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.estructuras.Inventarios.Inventario;
import uniandes.dpoo.estructuras.Persistencia.Persistencia;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.RangoFechas;
import uniandes.dpoo.estructuras.model.Subasta;
import uniandes.dpoo.estructuras.model.Usuario;

public class Controlador {
    private Inventario inventario;
    private Persistencia persistencia;
    private FormatUtils formatUtils = new FormatUtils();

    public Controlador() {
        inventario = new Inventario();
        persistencia = new Persistencia();
    }

    public void cargarDatos(){
        try {
            inventario = persistencia.cargarData(inventario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void agregarAutor(Autor autor) throws Exception{
        inventario.agregarAutor(autor);
        persistencia.guardarAutor(autor);
    }

    public Autor buscarAutorPorNombre(String nombre){
        return inventario.buscarAutor(nombre);
    }

    public void quitarAutor(Autor autor) throws Exception{
        inventario.quitarAutor(autor);
        persistencia.eliminarAutor(autor);
    }

    public void agregarCompra(Compra compra) throws Exception{
        inventario.agregarCompra(compra);
        Comprador comprador = inventario.buscarCompradorPorId(compra.getComprador());
        for (Integer idPieza : compra.getPiezas()) {
            Pieza pieza = inventario.buscarPiezaPorId(idPieza);
            comprador.agregarPieza(pieza.getId());
            pieza.agregarCompra(compra);
            pieza.agregarPropietario(comprador, new RangoFechas(LocalDate.now(), null));
            pieza.setEstado(Pieza.VENDIDA);
            persistencia.guardarPieza(pieza);
        }
        persistencia.guardarComprador(comprador);
        persistencia.guardarCompra(compra);
    }

    public void quitarCompra(Compra compra) throws Exception{
        inventario.quitarCompra(compra);
        persistencia.eliminarCompra(compra);
    }

    public Compra buscarCompraPorId(int id){
        return inventario.buscarCompraPorId(id);
    }

    public void agregarComprador(Comprador comprador) throws Exception{
        inventario.agregarComprador(comprador);
        persistencia.guardarComprador(comprador);
    }

    public Comprador buscarCompradorPorId(int id){
        return inventario.buscarCompradorPorId(id);
    }

    public void quitarComprador(Comprador comprador) throws Exception{
        inventario.quitarComprador(comprador);
        persistencia.eliminarComprador(comprador);
    }

    public void agregarEmpleado(Empleado empleado) throws Exception{
        inventario.agregarEmpleado(empleado);
        persistencia.guardarEmpleado(empleado);
    }

    public Empleado buscarEmpleadoPorId(int id){
        return inventario.buscarEmpleadoPorId(id);
    }

    public void quitarEmpleado(Empleado empleado) throws Exception{
        inventario.quitarEmpleado(empleado);
        persistencia.eliminarEmpleado(empleado);
    }

    public void agregarMedioPago(MedioDePago medioPago) throws Exception{
        inventario.agregarMedioPago(medioPago);
        persistencia.guardarMedioDePago(medioPago);
    }

    public void quitarMedioPago(MedioDePago medioPago) throws Exception{
        inventario.quitarMedioPago(medioPago);
        persistencia.eliminarMedioDePago(medioPago);
    }

    public String[] getMediosPagoNombre(){
        return inventario.getMediosPagoNombre();
    }

    public MedioDePago buscarMedioPago(String tipo){
        return inventario.buscarMedioPago(tipo);
    }

    public void agregarPieza(Pieza pieza) throws Exception{
        inventario.agregarPieza(pieza);
        persistencia.guardarPieza(pieza);
    }
    
    public void quitarPieza(Pieza pieza) throws Exception{
        inventario.quitarPieza(pieza);
        persistencia.eliminarPieza(pieza);
    }

    public Pieza buscarPiezaPorId(int id){
        return inventario.buscarPiezaPorId(id);
    }

    public void agregarSubasta(Subasta subasta) throws Exception{
        inventario.agregarSubasta(subasta);
        persistencia.guardarSubasta(subasta);
    }

    public void quitarSubasta(Subasta subasta) throws Exception{
        inventario.quitarSubasta(subasta);
        persistencia.eliminarSubasta(subasta);
    }

    public Subasta buscarSubastaPorId(int id){
        return inventario.buscarSubastaPorId(id);
    }

    public Usuario buscarUsuarioPorLogin(String login){
        return inventario.buscarUsuarioPorLogin(login);
    }

    public Usuario buscarUsuarioPorId(int id){
        return inventario.buscarUsuarioPorId(id);
    }

    public Usuario login(String login, String password){
        if (inventario.login(login, password)){
            return inventario.buscarUsuarioPorLogin(login);
        } else {
            return null;
        }
    }

    public void agregarUsuario(Usuario usuario) throws Exception{
        inventario.agregarUsuario(usuario);
        persistencia.guardarUsuario(usuario);
    }

    public void quitarUsuario(Usuario usuario) throws Exception{
        inventario.quitarUsuario(usuario);
        persistencia.eliminarUsuario(usuario);
    }

    public ArrayList<String> getTiposMediosPago(){
        return inventario.getTiposMediosPago();
    }

    public String consultarHistoriaPieza(int idPieza) {
        Pieza pieza = inventario.buscarPiezaPorId(idPieza);
        String cadena = formatUtils.mostradorPieza(pieza);

        cadena += "Compras: \n";
        for (Integer idCompra : pieza.getCompras()) {
            Compra compra = inventario.buscarCompraPorId(idCompra);
            cadena += formatUtils.mostradorCompra(compra);
        }

        HashMap<RangoFechas, Integer> propietarios = pieza.getPropietarios();

        cadena += "Propietarios: \n";
        for (RangoFechas rango : propietarios.keySet()) {
            cadena += "Propietario: " + inventario.buscarCompradorPorId(propietarios.get(rango)).getNombre() + "\n";
            if (rango.getFechaInicio() != null) {
                cadena += "Fecha inicio: " + rango.getFechaInicio() + "\n";
            }
            if (rango.getFechaFin() != null) {
                cadena += "Fecha fin: " + rango.getFechaFin() + "\n";
            }
        }

        return cadena;
    }

    public String consultarHistoriaAutor(String nombreAutor) {
        Autor autor = inventario.buscarAutor(nombreAutor);
        String cadena = formatUtils.mostradorAutor(autor);

        cadena += "Piezas: \n";
        ArrayList<Pieza> piezas = inventario.getPiezasPorAutor(autor);
        for (Pieza pieza : piezas) {
            cadena += formatUtils.mostradorPieza(pieza);
            cadena += "Compras de la pieza: \n";
            for (Integer idCompra : pieza.getCompras()) {
                Compra compra = inventario.buscarCompraPorId(idCompra);
                cadena += formatUtils.mostradorCompra(compra);
            }
        }

        return cadena;
    }

    public String consultarHistoriaComprador(int idComprador) {
        String cadena = "";

        Comprador comprador = inventario.buscarCompradorPorId(idComprador);

        cadena += formatUtils.mostradorComprador(comprador);

        int valorColeccion = 0;

        for (int idPieza : comprador.getPiezas()) {
            Pieza pieza = inventario.buscarPiezaPorId(idPieza);
            cadena += formatUtils.mostradorPieza(pieza);
            valorColeccion += pieza.getValor();
        }

        cadena += "Valor total de la colección: " + valorColeccion + "\n";

        return cadena;
    }

    public ArrayList<Compra> buscarComprasPorPieza(int piezaId) {
        return inventario.buscarComprasPorPieza(piezaId);
    }

    public ArrayList<Comprador> buscarCompradoresPorPieza(int idPieza) {
        return inventario.buscarCompradoresPorPieza(idPieza);
    }

    public ArrayList<Pieza> buscarPiezasPorAutor(String text) {
        return inventario.getPiezasPorAutor(inventario.buscarAutor(text));
    }

    public ArrayList<Pieza> buscarPiezasPorComprador(int id) {
        return inventario.buscarPiezasPorComprador(id);
    }

    public ArrayList<Compra> buscarComprasPorComprador(int id) {
        return inventario.buscarComprasPorComprador(id);
    }

    public Map<Integer, Integer> buscarVentasPorMes() {
        return inventario.buscarVentasPorMes();
    }
}
