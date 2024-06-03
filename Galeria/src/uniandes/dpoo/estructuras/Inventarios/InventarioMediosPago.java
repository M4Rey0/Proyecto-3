package uniandes.dpoo.estructuras.Inventarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import uniandes.dpoo.estructuras.model.MedioDePago;

public class InventarioMediosPago {
    private Map<String, MedioDePago> mediosPago = new HashMap<>();

    public void agregarMedioPago(MedioDePago medioPago) {
        mediosPago.put(medioPago.getTipo(), medioPago);
    }

    public void quitarMedioPago(MedioDePago medioPago) throws Exception {
        if (!mediosPago.containsKey(medioPago.getTipo())) {
            throw new Exception("El medio de pago no se encuentra en el inventario");
        } else {
            mediosPago.remove(medioPago.getTipo());
        }
    }

    public MedioDePago buscarMedioPago(String tipo) {
        return mediosPago.get(tipo);
    }

    public ArrayList<String> getTiposMediosPago() {
        return new ArrayList<>(mediosPago.keySet());
    }

    public List<MedioDePago> getMediosPago() {
        return new ArrayList<>(mediosPago.values());
    }

    public String[] getMediosPagoNombre() {
        String[] nombres = new String[mediosPago.size()];
        int i = 0;
        for (MedioDePago medioPago : mediosPago.values()) {
            nombres[i] = medioPago.getTipo();
            i++;
        }
        return nombres;
    }
}
