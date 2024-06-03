package uniandes.dpoo.estructuras.Inventarios;

import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.estructuras.model.Subasta;

public class InventarioSubastas {
    private Map<Integer, Subasta> subastas = new HashMap<>();

    public InventarioSubastas() {
    }

    public void agregarSubasta(Subasta subasta) {
        subastas.put(subasta.getId(), subasta);
    }

    public void quitarSubasta(int id) throws Exception {
        if (!subastas.containsKey(id)) {
            throw new Exception("La subasta no se encuentra en el inventario");
        } else {
            subastas.remove(id);
        }
    }

    public Subasta buscarSubastaPorId(int id) {
        return subastas.get(id);
    }
}
