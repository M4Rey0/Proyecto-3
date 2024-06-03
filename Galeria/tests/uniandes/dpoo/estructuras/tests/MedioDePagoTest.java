package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.model.mediosPago.PayPal;
import uniandes.dpoo.estructuras.Inventarios.InventarioMediosPago;




public class MedioDePagoTest {

    private InventarioMediosPago inventario = new InventarioMediosPago();

    @BeforeEach
    public void setUp() {
        inventario = new InventarioMediosPago();
    }

    @Test
    public void testAgregarMedioDePago() {
        MedioDePago medioDePago = new PayPal();
        inventario.agregarMedioPago(medioDePago);
        Assert.assertEquals(medioDePago, inventario.buscarMedioPago("PayPal"));
    }

    @Test
    public void testEliminarMedioDePago() {
        MedioDePago medioDePago = new PayPal();
        inventario.agregarMedioPago(medioDePago);
        try {
            inventario.quitarMedioPago(medioDePago);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        Assert.assertNull(inventario.buscarMedioPago("Efectivo"));
    }

    @Test
    public void testBuscarMedioDePagoExistente() {
        MedioDePago medioDePago = new PayPal();
        inventario.agregarMedioPago(medioDePago);
        MedioDePago medioEncontrado = inventario.buscarMedioPago("PayPal");
        Assert.assertNotNull(medioEncontrado);
        Assert.assertEquals(medioDePago, medioEncontrado);
    }

    @Test
    public void testBuscarMedioDePagoNoExistente() {
        MedioDePago medioDePago = new PayPal();
        inventario.agregarMedioPago(medioDePago);
        MedioDePago medioEncontrado = inventario.buscarMedioPago("Tarjeta de crédito");
        Assert.assertNull(medioEncontrado);
    }

    @AfterEach
    public void tearDown() {
        inventario = null;
    }
}
