package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Inventarios.InventarioSubastas;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.Subasta;

import java.util.ArrayList;
import java.util.Map;

public class SubastaTest {

    private InventarioSubastas inventario = new InventarioSubastas();

    @BeforeEach
    public void setUp() {
        inventario = new InventarioSubastas();
    }

    @AfterEach
    public void tearDown() {
        inventario = null;
    }

    @Test
    public void testAgregarValorMinimo() {
        Subasta subasta = new Subasta();
        subasta.agregarValorMinimo(1, 100);
        subasta.agregarValorMinimo(2, 200);

        Map<Integer, Integer> valoresMinimos = subasta.getValoresMinimos();
        Assert.assertEquals(100, valoresMinimos.get(1).intValue());
        Assert.assertEquals(200, valoresMinimos.get(2).intValue());
    }

    @Test
    public void testAgregarValorInicial() {
        Subasta subasta = new Subasta();
        subasta.agregarValorInicial(1, 500);
        subasta.agregarValorInicial(2, 1000);

        Map<Integer, Integer> valoresIniciales = subasta.getValoresIniciales();
        Assert.assertEquals(500, valoresIniciales.get(1).intValue());
        Assert.assertEquals(1000, valoresIniciales.get(2).intValue());
    }

    @Test
    public void testQuitarComprador() {

        Subasta subasta = new Subasta();
        
        ArrayList<Comprador> compradores = new ArrayList<>();

        for (int i = 0; i < 3; i++) {
            Comprador comprador = new Comprador();
            comprador.setId(i);
            compradores.add(comprador);
            try {
                subasta.agregarComprador(comprador);
            } catch (Exception e) {
                Assert.fail("No debería lanzar excepción");
            }
        }
        subasta.quitarComprador(compradores.get(1));

        Assert.assertFalse(subasta.getCompradores().contains(1));
    }

    @Test
    public void testContienePieza() {

        Subasta subasta = new Subasta();

        ArrayList<Pieza> piezas = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            Pieza pieza = new Pieza();
            piezas.add(pieza);
            try {
                subasta.agregarPieza(pieza);
            } catch (Exception e) {
                Assert.fail("No debería lanzar excepción");
            }
        }
        for (int i = 0; i < 3; i++) {
            Assert.assertTrue(subasta.contienePieza(piezas.get(i)));
        }
        Assert.assertFalse(subasta.getPiezas().contains(new Pieza().getId()));
    }

    @Test
    public void testGuardarSubastaEnInventario() {
        Subasta subasta = new Subasta();
        subasta.setId(1);
        subasta.agregarValorMinimo(1, 100);
        subasta.agregarValorInicial(1, 500);

        inventario.agregarSubasta(subasta);

        Subasta subastaGuardada = inventario.buscarSubastaPorId(1);
        Assert.assertNotNull(subastaGuardada);
        Assert.assertEquals(subasta, subastaGuardada);
    }

    @Test
    public void testEliminarSubasta() {
        Subasta subasta = new Subasta();
        subasta.setId(1);
        subasta.agregarValorMinimo(1, 100);
        subasta.agregarValorInicial(1, 500);

        inventario.agregarSubasta(subasta);

        try {
            inventario.quitarSubasta(subasta.getId());
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        Assert.assertNull(inventario.buscarSubastaPorId(1));
    }
}
