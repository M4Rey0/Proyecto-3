package uniandes.dpoo.estructuras.tests;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import uniandes.dpoo.estructuras.Inventarios.InventarioAutores;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.Persistencia.PersistenciaAutores;

public class PersistenciaAutoresTest {

    private PersistenciaAutores persistenciaAutores;
    private InventarioAutores inventarioAutores;
    Autor autor1 = new Autor("John Doe", "musico");
    Autor autor2 = new Autor("Jane Smith", "musico");

    @BeforeEach
    public void setUp() {
        persistenciaAutores = new PersistenciaAutores();
        inventarioAutores = new InventarioAutores();
    }

    @AfterEach
    public void tearDown() {
        try {
            persistenciaAutores.eliminarAutor(autor1);
            persistenciaAutores.eliminarAutor(autor2);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        persistenciaAutores = null;
        inventarioAutores = null;
    }

    @Test
    public void testGuardarAutor() {
        Autor autor = autor1;
        try {
            persistenciaAutores.guardarAutor(autor);
            persistenciaAutores.cargarAutores(inventarioAutores);
            Autor loadedAutor = inventarioAutores.buscarAutorPorNombre(autor.getNombre());
            Assert.assertEquals(autor, loadedAutor);
        } catch (Exception e) {
            Assert.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testCargarAutor() {
        Autor autor = autor2;
        try {
            persistenciaAutores.guardarAutor(autor);
            persistenciaAutores.cargarAutores(inventarioAutores);
            Autor loadedAutor = inventarioAutores.buscarAutorPorNombre(autor.getNombre());
            Assert.assertEquals(autor, loadedAutor);
        } catch (Exception e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    public void testEliminarAutor() {
        Autor autor = autor1;
        try {
            persistenciaAutores.guardarAutor(autor);
            persistenciaAutores.eliminarAutor(autor);
            persistenciaAutores.cargarAutores(inventarioAutores);
            Autor loadedAutor = inventarioAutores.buscarAutorPorNombre(autor.getNombre());
            Assert.assertNull(loadedAutor);
        } catch (Exception e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    @Test
    public void testCargarAutores() {
        try {
            persistenciaAutores.guardarAutor(autor1);
            persistenciaAutores.guardarAutor(autor2);
            persistenciaAutores.cargarAutores(inventarioAutores);
            Assertions.assertNotNull(inventarioAutores.buscarAutorPorNombre(autor1.getNombre()));
            Assertions.assertNotNull(inventarioAutores.buscarAutorPorNombre(autor2.getNombre()));
        } catch (RuntimeException e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        } catch (Exception e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        }
    }
}