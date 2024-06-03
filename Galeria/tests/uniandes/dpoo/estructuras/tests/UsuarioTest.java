package uniandes.dpoo.estructuras.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Inventarios.InventarioUsuarios;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Empleado;
import uniandes.dpoo.estructuras.model.Usuario;

public class UsuarioTest {

    InventarioUsuarios inventario = new InventarioUsuarios();

    @BeforeEach
    public void setUp() {
        inventario = new InventarioUsuarios();
    }

    @AfterEach
    public void tearDown() {
        inventario = null;
    }

    @Test
    public void testAgregarRol() {
        InventarioUsuarios inventario = new InventarioUsuarios();
        Usuario usuario = new Usuario();
        try {
            inventario.agregarUsuario(usuario);
            usuario.agregarRol(Usuario.ADMIN);
            Assert.assertTrue(usuario.verificarRol(Usuario.ADMIN));
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        
    }

    @Test
    public void testVerificarLogin() {
        InventarioUsuarios inventario = new InventarioUsuarios();
        Usuario usuario = new Usuario(1, "juand", "password123", "Juan", "1234567890", "juan@example.com",
                Usuario.EMPLEADO);
        inventario.agregarUsuario(usuario);
        Assert.assertTrue(usuario.verificarLogin("juand", "password123"));
        Assert.assertFalse(usuario.verificarLogin("juand", "wrongpassword"));
        Assert.assertFalse(usuario.verificarLogin("wrongusername", "password123"));
    }

    @Test
    public void testSettersAndGetters() {
        InventarioUsuarios inventario = new InventarioUsuarios();
        Usuario usuario = new Usuario();
        inventario.agregarUsuario(usuario);
        usuario.setId(1);
        usuario.setNombreUsuario("juand");
        usuario.setPassword("password123");
        usuario.setNombre("Juan");
        usuario.setTelefono("1234567890");
        usuario.setEmail("juan@example.com");
        usuario.setRol(Usuario.EMPLEADO);

        Assert.assertEquals(1, usuario.getId());
        Assert.assertEquals("juand", usuario.getNombreUsuario());
        Assert.assertEquals("password123", usuario.getPassword());
        Assert.assertEquals("Juan", usuario.getNombre());
        Assert.assertEquals("1234567890", usuario.getTelefono());
        Assert.assertEquals("juan@example.com", usuario.getEmail());
        Assert.assertEquals(Usuario.EMPLEADO, usuario.getRol());
    }

    @Test
    public void testAgregarEmpleado() {
        Usuario usuario = new Usuario();
        Empleado empleado = new Empleado(usuario, Empleado.EMPLEADO);
        inventario.agregarEmpleado(empleado);

        Assert.assertEquals(empleado, inventario.buscarEmpleadoPorId(empleado.getId()));
    }

    @Test
    public void testEliminarEmpleado() {
        Usuario usuario = new Usuario();
        Empleado empleado = new Empleado(usuario, Empleado.EMPLEADO);
        inventario.agregarEmpleado(empleado);
        try {
            inventario.quitarEmpleado(empleado);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        Assert.assertNull(inventario.buscarEmpleadoPorId(empleado.getId()));
    }

    @Test
    public void testAgregarComprador(){
        Usuario usuario = new Usuario();
        Comprador comprador = new Comprador(usuario, 100, true, new ArrayList<Integer>());
        inventario.agregarComprador(comprador);

        Assert.assertEquals(comprador, inventario.buscarCompradorPorId(comprador.getId()));
    }

    @Test
    public void testEliminarComprador(){
        Usuario usuario = new Usuario();
        Comprador comprador = new Comprador(usuario, 100, true, new ArrayList<Integer>());
        inventario.agregarComprador(comprador);
        try {
            inventario.quitarComprador(comprador);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        Assert.assertNull(inventario.buscarCompradorPorId(comprador.getId()));
    }
}
