package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Exceptions.SobreValorException;
import uniandes.dpoo.estructuras.Inventarios.InventarioCompras;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;

public class CompraTest {

    private InventarioCompras inventario = new InventarioCompras();

    @BeforeEach
    public void setUp() {
        inventario = new InventarioCompras();
    }

    @AfterEach
    public void tearDown() {
        inventario = null;
    }

    @Test
    public void testAgregarCompra() {
        Compra compra = new Compra();
        compra.setComprador(1);
        inventario.agregarCompra(compra);
        Assert.assertEquals(compra, inventario.getCompraPorId(compra.getId()));
    }

    @Test
    public void testEliminarCompra() {
        Compra compra = new Compra();
        compra.setComprador(1);
        inventario.agregarCompra(compra);
        try {
            inventario.quitarCompra(compra);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        Assert.assertNull(inventario.getCompraPorId(compra.getId()));
    }

    @Test
    public void agregarMismaPiezaTest() {
        Compra compra = new Compra();
        Pieza pieza = new Pieza();
        pieza.setValor(100);
        Comprador comprador = new Comprador();
        comprador.setLimiteCompra(300);
        try {
            for (int i = 0; i < 4; i++) {
                compra.agregarPieza(pieza, comprador);
            }
        } catch (SobreValorException e) {
            Assert.fail("No debería lanzar excepción");
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void agregarPiezaSobreValorTest() {
        Compra compra = new Compra();
        Pieza pieza = new Pieza();
        pieza.setValor(500);
        Comprador comprador = new Comprador();
        comprador.setLimiteCompra(300);
        try {
            compra.agregarPieza(pieza, comprador);
        } catch (SobreValorException e) {
            Assert.assertTrue(true);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
    }
}
