package uniandes.dpoo.estructuras.Inventarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.estructuras.model.Audiovisual;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Pieza;

public class InventarioPiezas {

    private Map<Integer, Pieza> piezas = new HashMap<>();
    private Map<String, Integer> piezasPorNombre = new HashMap<>();
    private List<Integer> piezasAudiovisuales = new ArrayList<>();
    private List<Integer> piezasEnPapel = new ArrayList<>();
    private List<Integer> piezasEsculturas = new ArrayList<>();
    

    public InventarioPiezas() {
    }

    public void agregarPieza(Pieza pieza){

        piezas.put(pieza.getId(), pieza);
        if (pieza instanceof Audiovisual){
            piezasAudiovisuales.add(pieza.getId());
        } else if (pieza instanceof ObraEnPapel){
            piezasEnPapel.add(pieza.getId());
        } else if (pieza instanceof Escultura){
            piezasEsculturas.add(pieza.getId());
        }

        piezasPorNombre.put(pieza.getTitulo(), pieza.getId());
    }

    public void quitarPieza(Pieza pieza)throws Exception{
        if (!piezas.containsKey(pieza.getId())){
            throw new Exception("La pieza no se encuentra en el inventario");
        } else{
            piezas.remove(pieza.getId());
            piezasPorNombre.remove(pieza.getTitulo());
            if (pieza instanceof Audiovisual){
                piezasAudiovisuales.remove(pieza.getId());
            } else if (pieza instanceof ObraEnPapel){
                piezasEnPapel.remove(pieza.getId());
            } else if (pieza instanceof Escultura){
                piezasEsculturas.remove(pieza.getId());
            }
        }
    }

    public void actualizarPieza(Pieza pieza)throws Exception{
        if (!piezas.containsKey(pieza.getId())){
            throw new Exception("La pieza no se encuentra en el inventario");
        } else{
            piezas.put(pieza.getId(), pieza);
            piezasPorNombre.put(pieza.getTitulo(), pieza.getId());
            if (pieza instanceof Audiovisual){
                piezasAudiovisuales.add(pieza.getId());
            } else if (pieza instanceof ObraEnPapel){
                piezasEnPapel.add(pieza.getId());
            } else if (pieza instanceof Escultura){
                piezasEsculturas.add(pieza.getId());
            }
        }
    }

    public Pieza buscarPiezaPorId(int id){
        return piezas.get(id);
    }

    public Pieza buscarPiezaPorTitulo(String nombre){
        return piezas.get(piezasPorNombre.get(nombre));
    }

    public ArrayList<Pieza> buscarPiezasPorAutor(Autor autor){
        ArrayList<Pieza> piezasAutor = new ArrayList<>();
        for (Pieza pieza: piezas.values()){
            if (pieza.getAutores().contains(autor.getNombre())){
                piezasAutor.add(pieza);
            }
        }
        return piezasAutor;
    }
}
