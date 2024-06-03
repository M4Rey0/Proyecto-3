package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Inventarios.InventarioPiezas;
import uniandes.dpoo.estructuras.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;





public class PiezaTest {

    private InventarioPiezas inventario = new InventarioPiezas();

    @BeforeEach
    public void setUp() {
        inventario = new InventarioPiezas();
    }

    @AfterEach
    public void tearDown() {
        inventario = null;
    }

    @Test
    public void testCrearPieza() {
        Pieza pieza = new Pieza();
        inventario.agregarPieza(pieza);
        Assert.assertNotNull(inventario.buscarPiezaPorId(pieza.getId()));
    }

    @Test
    public void testCrearPiezaConParametros() {
        Pieza pieza = new Pieza(1, "La noche estrellada", 1889, "Países Bajos", 1000000, true, Pieza.EXHIBIDA, new ArrayList<>(), new ArrayList<>());
        inventario.agregarPieza(pieza);
        Assert.assertEquals(pieza, inventario.buscarPiezaPorId(1));
    }

    @Test
    public void testAgregarAutor() {
        Pieza pieza = new Pieza();
        Autor autor = new Autor("Vincent van Gogh", "Visual");
        try {
            pieza.agregarAutor(autor);
            inventario.agregarPieza(pieza);
            Assert.assertEquals(1, inventario.buscarPiezaPorId(pieza.getId()).getAutores().size());
            Assert.assertEquals("Vincent van Gogh", inventario.buscarPiezaPorId(pieza.getId()).getAutores().get(0));
        } catch (Exception e) {
            Assert.fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarPropietario() {
        Pieza pieza = new Pieza();
        Comprador propietario = new Comprador();
        RangoFechas rangoFechas = new RangoFechas(LocalDate.parse("2022-10-12"), LocalDate.parse("2022-10-15"));
        try {
            pieza.agregarPropietario(propietario, rangoFechas);
            inventario.agregarPieza(pieza);
            Map<RangoFechas, Integer> propietarios = new HashMap<>();
            propietarios.put(rangoFechas, propietario.getId());
            Assert.assertEquals(propietarios, inventario.buscarPiezaPorId(pieza.getId()).getPropietarios());
        } catch (Exception e) {
            Assert.fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarCompra() {
        Pieza pieza = new Pieza();
        Compra compra = new Compra();
        try {
            pieza.agregarCompra(compra);
            inventario.agregarPieza(pieza);
            Assert.assertEquals(1, inventario.buscarPiezaPorId(pieza.getId()).getCompras().size());
            Assert.assertEquals(1, (int) inventario.buscarPiezaPorId(pieza.getId()).getCompras().get(0));
        } catch (Exception e) {
            Assert.fail("No se esperaba una excepción: " + e.getMessage());
        }
    }

    @Test
    public void testAgregarCompraExistente(){
        Pieza pieza = new Pieza();
        Compra compra = new Compra();
        try {
            pieza.agregarCompra(compra);
            pieza.agregarCompra(compra);
            inventario.agregarPieza(pieza);
            Assert.assertEquals(1, inventario.buscarPiezaPorId(pieza.getId()).getCompras().size());
            Assert.assertEquals(1, (int) inventario.buscarPiezaPorId(pieza.getId()).getCompras().get(0));
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testAgregarAutorExistente(){
        Pieza pieza = new Pieza();
        Autor autor = new Autor("Vincent van Gogh", "Visual");
        try {
            pieza.agregarAutor(autor);
            pieza.agregarAutor(autor);
            inventario.agregarPieza(pieza);
            Assert.assertEquals(1, inventario.buscarPiezaPorId(pieza.getId()).getAutores().size());
            Assert.assertEquals("Vincent van Gogh", inventario.buscarPiezaPorId(pieza.getId()).getAutores().get(0));
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }

    @Test
    public void testSobreponerPropietarios(){
        Pieza pieza = new Pieza();
        Comprador propietario = new Comprador();
        RangoFechas rangoFechas = new RangoFechas(LocalDate.parse("2022-10-12"), LocalDate.parse("2022-10-15"));
        RangoFechas rangoFechas2 = new RangoFechas(LocalDate.parse("2022-10-12"), LocalDate.parse("2022-10-15"));
        try {
            pieza.agregarPropietario(propietario, rangoFechas);
            pieza.agregarPropietario(propietario, rangoFechas2);
            inventario.agregarPieza(pieza);
            Map<RangoFechas, Integer> propietarios = new HashMap<>();
        } catch (Exception e) {
            Assert.assertTrue(true);
        }
    }
}
