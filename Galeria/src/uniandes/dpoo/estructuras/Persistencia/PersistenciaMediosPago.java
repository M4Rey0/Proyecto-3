package uniandes.dpoo.estructuras.Persistencia;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import uniandes.dpoo.estructuras.Inventarios.InventarioMediosPago;
import uniandes.dpoo.estructuras.model.MedioDePago;
import uniandes.dpoo.estructuras.Exceptions.MedioPagoException;
import uniandes.dpoo.estructuras.Inventarios.FactoryMediosPago;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PersistenciaMediosPago {
    private static final String FILE_PATH = "data/MediosPago/";
    private static final String archivoBase = FILE_PATH+"MediosDePago.txt";
    public static final String Assert = null;
    private PersistenciaUtils persistenciaUtils = new PersistenciaUtils();

    public void guardarMedioDePago(MedioDePago medioDePago) throws Exception {
        String path = FILE_PATH + medioDePago.getTipo() + ".txt";
        String contenido = medioDePago.toString();
        String clase = "MedioDePago";
        persistenciaUtils.guardarElemento(path, contenido, clase);
    }

    public void guardarArchivoBaseMediosPago(String medios) throws Exception {
        String clase = "MedioDePago";
        persistenciaUtils.guardarElemento(archivoBase, medios, clase);
    }

    public MedioDePago cargarMedioDePago(String tipo, String texto) throws MedioPagoException{
        String[] partes = texto.split(",");
        try {
            return FactoryMediosPago.crearMedioPago(tipo, partes);
        } catch (Exception e) {
            throw new MedioPagoException(e.getMessage());
        } 
    }

    public void eliminarMedioDePago(MedioDePago medioDePago) throws Exception {
        String path = FILE_PATH + medioDePago.getTipo() + ".txt";
        persistenciaUtils.eliminarElemento(path, "MedioDePago", medioDePago.getTipo());
    }
    
    public InventarioMediosPago cargarMediosDePago(InventarioMediosPago inventarioMediosPago) throws RuntimeException, MedioPagoException{
            try {
                File medios = new File(archivoBase);
                if (!medios.exists()) {
                    Files.createFile(Paths.get(archivoBase));
                }
                String texto = Files.readString(Paths.get(archivoBase));
                String[] mediosSeparados = texto.split(",");
                for (String medio : mediosSeparados) {
                    File medioFile = new File(FILE_PATH + medio + ".txt");
                    if (!medioFile.exists()) {
                        Files.createFile(Paths.get(FILE_PATH + medio + ".txt"));
                    }
                    String contenido = Files.readString(Paths.get(FILE_PATH + medio + ".txt"));
                    MedioDePago medioDePago = cargarMedioDePago(medio, contenido);
                    inventarioMediosPago.agregarMedioPago(medioDePago);
                }

                return inventarioMediosPago;
            } catch (IOException e) {
                System.out.println("Error al cargar los medios de pago: " + e.getMessage());
                throw new RuntimeException("Error al cargar los medios de pago");
            }
        }


}
