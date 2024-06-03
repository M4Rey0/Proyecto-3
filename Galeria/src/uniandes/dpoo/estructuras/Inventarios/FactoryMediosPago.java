package uniandes.dpoo.estructuras.Inventarios;

import java.lang.reflect.InvocationTargetException;

import uniandes.dpoo.estructuras.model.MedioDePago;

public class FactoryMediosPago {
    public static MedioDePago crearMedioPago(String tipo, String[] contenido) throws ClassNotFoundException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException{
        Class<?> clase = Class.forName("uniandes.dpoo.estructuras.model.mediosPago."+tipo);
        MedioDePago medioDePago = (MedioDePago) clase.getDeclaredConstructor(null).newInstance();
        medioDePago.cargar(contenido);
        return medioDePago;
    }
}
