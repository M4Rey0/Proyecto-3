package uniandes.dpoo.estructuras.Persistencia;

import uniandes.dpoo.estructuras.Inventarios.Inventario;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.Subasta;
import uniandes.dpoo.estructuras.model.Usuario;

public class Persistencia {

    //necesarios para la persistencia sin relaciones
    private PersistenciaMediosPago persistenciaMediosPago = new PersistenciaMediosPago();
    private PersistenciaUsuarios persistenciaUsuarios = new PersistenciaUsuarios();
    private PersistenciaAutores persistenciaInventario = new PersistenciaAutores();
    private PersistenciaCompradores persistenciaCompradores = new PersistenciaCompradores();
    private PersistenciaEmpleados persistenciaEmpleados = new PersistenciaEmpleados();
    private PersistenciaCompras persistenciaCompras = new PersistenciaCompras();
    private PersistenciaPiezas persistenciaPiezas = new PersistenciaPiezas();
    private PersistenciaSubastas persistenciaSubastas = new PersistenciaSubastas();

    

    public Inventario cargarData(Inventario inventario) throws Exception {

        inventario.setMediosPago(persistenciaMediosPago.cargarMediosDePago(inventario.getMediosPago()));
        inventario.setUsuarios(persistenciaUsuarios.cargarUsuarios(inventario.getUsuarios()));
        inventario.setAutores(persistenciaInventario.cargarAutores(inventario.getAutores()));
        inventario.setUsuarios(persistenciaCompradores.cargarCompradores(inventario.getUsuarios()));
        inventario.setUsuarios(persistenciaEmpleados.cargarEmpleados(inventario.getUsuarios()));
        inventario.setCompras(persistenciaCompras.cargarCompras(inventario.getCompras()));
        inventario.setPiezas(persistenciaPiezas.cargarPiezas(inventario.getPiezas()));
        inventario.setSubastas(persistenciaSubastas.cargarSubastas(inventario.getSubastas()));
        
        return inventario;
    }

    public void guardarMedioDePago(MedioDePago medioDePago) throws Exception {
        persistenciaMediosPago.guardarMedioDePago(medioDePago);
    }

    public void guardarUsuario(Usuario usuario) throws Exception {
        persistenciaUsuarios.guardarUsuario(usuario);
    }

    public void guardarAutor(Autor autor) throws Exception {
        persistenciaInventario.guardarAutor(autor);
    }

    public void guardarComprador(Comprador comprador) throws Exception {
        persistenciaCompradores.guardarComprador(comprador);
    }

    public void guardarEmpleado(Empleado empleado) throws Exception {
        persistenciaEmpleados.guardarEmpleado(empleado);
    }

    public void eliminarMedioDePago(MedioDePago medioDePago) throws Exception {
        persistenciaMediosPago.eliminarMedioDePago(medioDePago);
    }

    public void eliminarUsuario(Usuario usuario) throws Exception {
        persistenciaUsuarios.eliminarUsuario(usuario);
    }

    public void eliminarAutor(Autor autor) throws Exception {
        persistenciaInventario.eliminarAutor(autor);
    }

    public void eliminarComprador(Comprador comprador) throws Exception {
        persistenciaCompradores.eliminarComprador(comprador);
    }

    public void eliminarEmpleado(Empleado empleado) throws Exception {
        persistenciaEmpleados.eliminarEmpleado(empleado);
    }

    public void guardarCompra(Compra compra) throws Exception {
        persistenciaCompras.guardarCompra(compra);
    }

    public void eliminarCompra(Compra compra) throws Exception {
        persistenciaCompras.eliminarCompra(compra);
    }

    public void guardarPieza(Pieza pieza) throws Exception {
        persistenciaPiezas.guardarPieza(pieza);
    }

    public void eliminarPieza(Pieza pieza) throws Exception {
        persistenciaPiezas.eliminarPieza(pieza);
    }

    public void guardarSubasta(Subasta subasta) throws Exception {
        persistenciaSubastas.guardarSubasta(subasta);
    }

    public void eliminarSubasta(Subasta subasta) throws Exception {
        persistenciaSubastas.eliminarSubasta(subasta);
    }
}
