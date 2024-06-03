package uniandes.dpoo.estructuras.tests;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Inventarios.InventarioSubastas;
import uniandes.dpoo.estructuras.Persistencia.PersistenciaSubastas;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.Subasta;

public class PersistenciaSubastasTest {
    PersistenciaSubastas persistencia = new PersistenciaSubastas();
    InventarioSubastas inventario = new InventarioSubastas();

    public Subasta generarSubasta(){
        Subasta subasta = new Subasta();
        subasta.setId(1);
        try {
            subasta.agregarComprador(new Comprador());
            subasta.agregarComprador(new Comprador());
            subasta.agregarComprador(new Comprador());
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            subasta.agregarPieza(new Pieza());
            subasta.agregarPieza(new Pieza());
            subasta.agregarPieza(new Pieza());
        } catch (Exception e) {
            e.printStackTrace();
        }

        subasta.agregarValorInicial(1, 1);
        subasta.agregarValorInicial(2, 2);
        subasta.agregarValorInicial(3, 3);

        subasta.agregarValorMinimo(1, 2);
        subasta.agregarValorMinimo(2, 3);
        subasta.agregarValorMinimo(3, 4);

        return subasta;
    }

    @BeforeEach
    public void setUp() {
        inventario = new InventarioSubastas();
        persistencia = new PersistenciaSubastas();
    }

    @AfterEach
    public void tearDown() {
        try {
            persistencia.eliminarSubasta(generarSubasta());
        } catch (Exception e) {
            System.out.println("Ya fue eliminada la subasta");
        }
        inventario = null;
        persistencia = null;
    }

    @Test
    public void testGuardarSubasta() {
        Subasta subasta = generarSubasta();
        try {
            persistencia.guardarSubasta(subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        inventario = persistencia.cargarSubastas(inventario);
        Subasta subastaCargada = inventario.buscarSubastaPorId(1);
        assert subastaCargada != null;
    }

    @Test
    public void testCargarSubasta() {
        Subasta subasta = generarSubasta();
        try {
            persistencia.guardarSubasta(subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        persistencia.cargarSubastas(inventario);
        try {
            persistencia.eliminarSubasta(subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Subasta subastaCargada = inventario.buscarSubastaPorId(1);
        assert subastaCargada != null;
    }

    @Test
    public void testEliminarSubasta() {
        Subasta subasta = generarSubasta();
        try {
            persistencia.guardarSubasta(subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            persistencia.eliminarSubasta(subasta);
        } catch (Exception e) {
            e.printStackTrace();
        }
        persistencia.cargarSubastas(inventario);
        
        Subasta subastaCargada = inventario.buscarSubastaPorId(1);
        assert subastaCargada == null;
    }
}
