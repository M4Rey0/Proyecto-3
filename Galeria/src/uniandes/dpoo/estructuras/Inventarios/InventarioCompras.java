package uniandes.dpoo.estructuras.Inventarios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import uniandes.dpoo.estructuras.model.Compra;

public class InventarioCompras {
    private Map<Integer, Compra> compras = new HashMap<>();
    private Map<Integer, ArrayList<Integer>> comprasSegunComprador = new HashMap<>();

    public InventarioCompras() {
    }

    public void agregarCompra(Compra compra) {
        compras.put(compra.getId(), compra);
        if (!comprasSegunComprador.containsKey(compra.getComprador())) {
            ArrayList<Integer> lista = new ArrayList<>();
            lista.add(compra.getId());
            comprasSegunComprador.put(compra.getComprador(), lista);
        } else {
            ArrayList<Integer> compras = comprasSegunComprador.get(compra.getComprador());
            compras.add(compra.getId());
            comprasSegunComprador.put(compra.getComprador(), compras);
        }
    }

    public void quitarCompra(Compra compra) throws Exception {
        if (!compras.containsKey(compra.getId())) {
            throw new Exception("La compra no se encuentra en el inventario");
        } else {
            compras.remove(compra.getId());
        }
    }

    public Compra getCompraPorId(int id) {
        return compras.get(id);
    }

    public ArrayList<Compra> buscarComprasPorComprador(int id) {
        ArrayList<Compra> comprasComprador = new ArrayList<>();
        if (comprasSegunComprador.containsKey(id)) {
            ArrayList<Integer> idsCompras = comprasSegunComprador.get(id);
            for (Integer idCompra : idsCompras) {
                comprasComprador.add(compras.get(idCompra));
            }
        }
        return comprasComprador;
    }

    public Map<Integer, Integer> buscarVentasPorMes() {
        Map<Integer, Integer> ventasPorMes = new HashMap<>();

        for (int i = 1; i <= 12; i++) {
            ventasPorMes.put(i, 0);
        }

        for (Compra compra : compras.values()) {
            int mes = compra.getFechaCompra().getMonthValue();
            if (ventasPorMes.containsKey(mes)) {
                ventasPorMes.put(mes, ventasPorMes.get(mes) + compra.getValor());
            }
        }

        return ventasPorMes;
    }
}
