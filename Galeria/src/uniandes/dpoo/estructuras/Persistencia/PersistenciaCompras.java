package uniandes.dpoo.estructuras.Persistencia;

import java.io.File;
import java.nio.file.Files;
import java.time.LocalDate;
import java.util.ArrayList;

import uniandes.dpoo.estructuras.Inventarios.InventarioCompras;
import uniandes.dpoo.estructuras.model.Compra;

public class PersistenciaCompras {
    private static final String FILE_PATH = "data/Compras/";

    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarCompra(Compra compra) throws Exception {
        String path = FILE_PATH + compra.getId() + ".txt";
        String contenido = compra.toString();
        String clase = "Compra";
        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public void eliminarCompra(Compra compra) throws Exception {
        String path = FILE_PATH + compra.getId() + ".txt";
        persistenciaUtils.eliminarElemento(path, "Compra", Integer.toString(compra.getId()));
    }

    public Compra cargarCompra(String texto) {
        String[] partes = texto.split(",");
        Compra compra = new Compra();
        compra.setId(Integer.parseInt(partes[0]));
        String[] piezas = partes[1].split("-");
        ArrayList<Integer> piezasCompradas = new ArrayList<>();
        for (String pieza : piezas) {
            if (!pieza.equals("")) {
                piezasCompradas.add(Integer.parseInt(pieza));
            }
        }
        compra.setPiezas(piezasCompradas);
        compra.setMedioDePago(partes[2]);
        compra.setValor(Integer.parseInt(partes[3]));
        compra.setMediante(partes[4]);
        compra.setComprador(Integer.parseInt(partes[5]));
        compra.setFechaCompra(LocalDate.parse(partes[6]));

        return compra;
    }

    public InventarioCompras cargarCompras(InventarioCompras inventarioCompras) throws RuntimeException {
        try {
            File directory = new File(FILE_PATH);
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        String contenido = Files.readString(file.toPath());
                        Compra compra = cargarCompra(contenido);
                        inventarioCompras.agregarCompra(compra);
                    }
                }
            }
            return inventarioCompras;

        } catch (Exception e) {
            System.out.println("Error al cargar las compras: " + e.getMessage());
            throw new RuntimeException("Error al cargar las compras");
        }
    }
}
