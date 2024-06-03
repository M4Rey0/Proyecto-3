package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Exceptions.MedioPagoException;
import uniandes.dpoo.estructuras.Inventarios.InventarioMediosPago;
import uniandes.dpoo.estructuras.Persistencia.PersistenciaMediosPago;
import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.model.mediosPago.PayPal;

public class PersistenciaMediosPagoTest {
    PersistenciaMediosPago persistencia = new PersistenciaMediosPago();
    InventarioMediosPago inventario = new InventarioMediosPago();

    @AfterEach
    public void tearDown() {
        try {

        } catch (Exception e) {
            System.out.println("Ya fue eliminado el medio de pago");
        }
        inventario = null;
        persistencia = null;
    }

    @BeforeEach
    public void setUp() {
        inventario = new InventarioMediosPago();
        persistencia = new PersistenciaMediosPago();
        try {
            inventario = persistencia.cargarMediosDePago(inventario);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testGuardarMedioPago() throws RuntimeException, MedioPagoException {
        MedioDePago medioPago = new PayPal("correo","contraseña");
        try {
            persistencia.guardarMedioDePago(medioPago);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        inventario = persistencia.cargarMediosDePago(inventario);
        MedioDePago medioPagoCargado = inventario.buscarMedioPago("PayPal");
        Assert.assertNotNull(medioPagoCargado);
    }

    @Test
    public void testCargarMedioPago() throws RuntimeException, MedioPagoException {
        MedioDePago medioPago = new PayPal("correo", "contraseña");
        try {
            persistencia.guardarMedioDePago(medioPago);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        persistencia.cargarMediosDePago(inventario);
        try {
            persistencia.eliminarMedioDePago(medioPago);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        MedioDePago medioPagoCargado = inventario.buscarMedioPago("PayPal");
        Assert.assertEquals(medioPago, medioPagoCargado);
    }
}
