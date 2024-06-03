package uniandes.dpoo.estructuras.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Inventarios.Inventario;
import uniandes.dpoo.estructuras.Persistencia.Persistencia;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class PersistenciaUsuariosTest {
    private Inventario inventario = new Inventario();
    private Persistencia persistencia = new Persistencia();
    private Usuario usuarioBase = new Usuario(123456, "usuarioBase", "contra", "usuario", "123", "correo", Usuario.COMPRADOR+Usuario.EMPLEADO);
    private Comprador compradorBase = new Comprador(usuarioBase, 300, true, new ArrayList<>());
    private Empleado empleadoBase = new Empleado(usuarioBase, Empleado.EMPLEADO);

    @BeforeEach
    public void setUp() {
        inventario = new Inventario();
        persistencia = new Persistencia();
        try {
            inventario = persistencia.cargarData(inventario);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @AfterEach
    public void tearDown() {
        
        try {
            persistencia.eliminarUsuario(usuarioBase);
        } catch (Exception e) {
            System.out.println("Ya fue eliminado el usuario");
        }
        try {
            persistencia.eliminarComprador(compradorBase);
        } catch (Exception e) {
            System.out.println("Ya fue eliminado el comprador");
        }
        try {
            persistencia.eliminarEmpleado(empleadoBase);
        } catch (Exception e) {
            System.out.println("Ya fue eliminado el empleado");
        }
        inventario = null;
        persistencia = null;
    }

    @Test
    public void testGuardarUsuario() {
        try {
            persistencia.guardarUsuario(usuarioBase);
            persistencia.cargarData(inventario);
            Usuario loadedUsuario = inventario.buscarUsuarioPorId(usuarioBase.getId());
            persistencia.eliminarUsuario(loadedUsuario);
            Assert.assertEquals(usuarioBase, loadedUsuario);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGuardarComprador() {
        try {
            persistencia.guardarUsuario(usuarioBase);
            persistencia.guardarComprador(compradorBase);
            persistencia.cargarData(inventario);
            Comprador loadedComprador = inventario.buscarCompradorPorId(compradorBase.getId());
            persistencia.eliminarUsuario(usuarioBase);
            persistencia.eliminarComprador(loadedComprador);
            Assert.assertEquals(compradorBase, loadedComprador);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testGuardarEmpleado() {
        try {
            persistencia.guardarUsuario(usuarioBase);
            persistencia.guardarEmpleado(empleadoBase);
            persistencia.cargarData(inventario);
            Empleado loadedEmpleado = inventario.buscarEmpleadoPorId(empleadoBase.getId());
            persistencia.eliminarUsuario(usuarioBase);
            persistencia.eliminarEmpleado(loadedEmpleado);
            Assert.assertEquals(empleadoBase, loadedEmpleado);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarUsuario() {
        try {
            persistencia.guardarUsuario(usuarioBase);
            persistencia.eliminarUsuario(usuarioBase);
            persistencia.cargarData(inventario);
            Usuario loadedUsuario = inventario.buscarUsuarioPorId(usuarioBase.getId());
            Assert.assertNull(loadedUsuario);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarComprador() {
        try {
            persistencia.guardarUsuario(usuarioBase);
            persistencia.guardarComprador(compradorBase);
            persistencia.eliminarComprador(compradorBase);
            persistencia.cargarData(inventario);
            Comprador loadedComprador = inventario.buscarCompradorPorId(compradorBase.getId());
            persistencia.eliminarUsuario(usuarioBase);
            Assert.assertNull(loadedComprador);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarEmpleado() {
        try {
            persistencia.guardarUsuario(usuarioBase);
            persistencia.guardarEmpleado(empleadoBase);
            persistencia.eliminarEmpleado(empleadoBase);
            persistencia.cargarData(inventario);
            Empleado loadedEmpleado = inventario.buscarEmpleadoPorId(empleadoBase.getId());
            persistencia.eliminarUsuario(usuarioBase);
            Assert.assertNull(loadedEmpleado);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }
}