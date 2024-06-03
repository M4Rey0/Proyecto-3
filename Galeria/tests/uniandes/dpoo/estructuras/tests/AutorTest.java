package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import uniandes.dpoo.estructuras.Inventarios.InventarioAutores;
import uniandes.dpoo.estructuras.model.Autor;


public class AutorTest {
    private InventarioAutores inventarioAutor = new InventarioAutores();

    @BeforeEach
    public void setUp() {
        inventarioAutor = new InventarioAutores();
    }

    @AfterEach
    public void tearDown() {
        inventarioAutor = null;
    }

    @Test
    public void testAgregarAutor() {
        Autor autor = new Autor("John Doe", "Escritor");
        inventarioAutor.agregarAutor(autor);
        Assert.assertTrue(autor.equals(inventarioAutor.buscarAutorPorNombre(autor.getNombre())));
    }

    @Test
    public void testEliminarAutor() {
        Autor autor = new Autor("Jane Smith", "Ilustrador");
        inventarioAutor.agregarAutor(autor);
        try {
            inventarioAutor.quitarAutor(autor);
        } catch (Exception e) {
            Assert.fail("No debería lanzar excepción");
        }
        Assert.assertNull(inventarioAutor.buscarAutorPorNombre(autor.getNombre()));
    }

    @Test
    public void testBuscarAutorExistente() {
        Autor autor = new Autor("John Doe", "Escritor");
        inventarioAutor.agregarAutor(autor);
        Autor autorEncontrado = inventarioAutor.buscarAutorPorNombre("John Doe");
        Assert.assertNotNull(autorEncontrado);
        Assert.assertEquals(autor, autorEncontrado);
    }

    @Test
    public void testBuscarAutorNoExistente() {
        Autor autor = new Autor("John Doe", "Escritor");
        inventarioAutor.agregarAutor(autor);
        Autor autorEncontrado = inventarioAutor.buscarAutorPorNombre("Jane Smith");
        Assert.assertNull(autorEncontrado);
    }
}
