package uniandes.dpoo.estructuras.tests;

import java.util.ArrayList;

import org.junit.Test;
import org.junit.jupiter.api.AfterEach;

import uniandes.dpoo.estructuras.Inventarios.InventarioPiezas;
import uniandes.dpoo.estructuras.Persistencia.PersistenciaPiezas;
import uniandes.dpoo.estructuras.model.Audiovisual;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Pieza;

public class PersistenciaPiezasTest {
    private PersistenciaPiezas persistenciaPiezas = new PersistenciaPiezas();
    private InventarioPiezas inventarioPiezas = new InventarioPiezas();

    public Pieza generarPieza(){
        Pieza pieza = new Pieza();
        pieza.setId(123455);
        pieza.setTitulo("Pieza 1");
        pieza.setValor(1000);
        pieza.setAnio(2000);
        ArrayList<String> autores = new ArrayList<>();
        autores.add("Autor 1");
        autores.add("Autor 2");
        pieza.setAutores(autores);

        return pieza;
    }

    public Escultura generarEscultura(){
        Pieza pieza = generarPieza();

        Escultura escultura = new Escultura(pieza, 10, 10, 10, "piedra caliza", true);
        return escultura;
    }

    public ObraEnPapel generarObraEnPapel(){
        Pieza pieza = generarPieza();

        ObraEnPapel obraEnPapel = new ObraEnPapel(pieza,"Pintura","Mural","Acuarela");
        return obraEnPapel;
    }

    public Audiovisual generarAudiovisual(){
        Pieza pieza = generarPieza();

        Audiovisual audiovisual = new Audiovisual(pieza, 200, "Video");
        return audiovisual;
    }

    @AfterEach
    public void tearDown() {
        try {
            persistenciaPiezas.eliminarPieza(generarPieza());
        } catch (Exception e) {
            System.out.println("Ya fue eliminada la pieza");
        }
        inventarioPiezas = null;
        persistenciaPiezas = null;
    }

    @Test
    public void testGuardarEscultura(){
        Escultura pieza = generarEscultura();
        try {
            persistenciaPiezas.guardarPieza(pieza);
            persistenciaPiezas.cargarPiezas(inventarioPiezas);
            Pieza loadedPieza = inventarioPiezas.buscarPiezaPorId(pieza.getId());
            persistenciaPiezas.eliminarPieza(pieza);
            assert(loadedPieza.equals(pieza));
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testGuardarObraEnPapel(){
        ObraEnPapel pieza = generarObraEnPapel();
        try {
            persistenciaPiezas.guardarPieza(pieza);
            persistenciaPiezas.cargarPiezas(inventarioPiezas);
            Pieza loadedPieza = inventarioPiezas.buscarPiezaPorId(pieza.getId());
            persistenciaPiezas.eliminarPieza(pieza);
            assert(loadedPieza.equals(pieza));
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testGuardarAudiovisual(){
        Audiovisual pieza = generarAudiovisual();
        try {
            persistenciaPiezas.guardarPieza(pieza);
            persistenciaPiezas.cargarPiezas(inventarioPiezas);
            Pieza loadedPieza = inventarioPiezas.buscarPiezaPorId(pieza.getId());
            persistenciaPiezas.eliminarPieza(pieza);
            assert(loadedPieza.equals(pieza));
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testEliminarEscultura(){
        Escultura pieza = generarEscultura();
        try {
            persistenciaPiezas.guardarPieza(pieza);
            persistenciaPiezas.eliminarPieza(pieza);
            persistenciaPiezas.cargarPiezas(inventarioPiezas);
            Pieza loadedPieza = inventarioPiezas.buscarPiezaPorId(pieza.getId());
            assert(loadedPieza == null);
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testEliminarObraEnPapel(){
        ObraEnPapel pieza = generarObraEnPapel();
        try {
            persistenciaPiezas.guardarPieza(pieza);
            persistenciaPiezas.eliminarPieza(pieza);
            persistenciaPiezas.cargarPiezas(inventarioPiezas);
            Pieza loadedPieza = inventarioPiezas.buscarPiezaPorId(pieza.getId());
            assert(loadedPieza == null);
        } catch (Exception e) {
            assert(false);
        }
    }

    @Test
    public void testEliminarAudiovisual(){
        Audiovisual pieza = generarAudiovisual();
        try {
            persistenciaPiezas.guardarPieza(pieza);
            persistenciaPiezas.eliminarPieza(pieza);
            persistenciaPiezas.cargarPiezas(inventarioPiezas);
            Pieza loadedPieza = inventarioPiezas.buscarPiezaPorId(pieza.getId());
            assert(loadedPieza == null);
        } catch (Exception e) {
            assert(false);
        }
    }

}
