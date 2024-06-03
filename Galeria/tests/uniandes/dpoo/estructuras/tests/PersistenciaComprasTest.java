package uniandes.dpoo.estructuras.tests;

import java.util.ArrayList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import uniandes.dpoo.estructuras.Persistencia.PersistenciaCompras;
import uniandes.dpoo.estructuras.Inventarios.InventarioCompras;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.MedioDePago;




public class PersistenciaComprasTest {

    private PersistenciaCompras persistenciaCompras = new PersistenciaCompras();
    private InventarioCompras inventarioCompras = new InventarioCompras();

    public Compra generarCompra(){
        Compra retorno = new Compra();
        retorno.setId(123456);
        ArrayList<Integer> piezas = new ArrayList<>();
        piezas.add(1);
        piezas.add(2);
        retorno.setPiezas(piezas);
        
        retorno.setMedioDePago("Visa");
        retorno.setValor(1000);
        retorno.setMediante(Compra.VENTA_DIRECTA);
        retorno.setComprador(1);

        return retorno;
    }

    @Test
    public void testGuardarCompra() {
        Compra compra = generarCompra();
        try {
            persistenciaCompras.guardarCompra(compra);
            persistenciaCompras.cargarCompras(inventarioCompras);
            persistenciaCompras.eliminarCompra(compra);
            Compra loadedCompra = inventarioCompras.getCompraPorId(compra.getId());
            Assert.assertEquals(compra, loadedCompra);
        } catch (Exception e) {
            Assert.fail("Exception thrown: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarCompra() {
        Compra compra = generarCompra();
        try {
            persistenciaCompras.guardarCompra(compra);
            persistenciaCompras.eliminarCompra(compra);
            persistenciaCompras.cargarCompras(inventarioCompras);
            Compra loadedCompra = inventarioCompras.getCompraPorId(123456);
            Assert.assertNull(loadedCompra);
        } catch (Exception e) {
            Assert.fail("Exception thrown: " + e.getMessage());
        }
    }
}
