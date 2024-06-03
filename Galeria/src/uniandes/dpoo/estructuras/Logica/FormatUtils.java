package uniandes.dpoo.estructuras.Logica;

import uniandes.dpoo.estructuras.model.Audiovisual;
import uniandes.dpoo.estructuras.model.Autor;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Pieza;

public class FormatUtils {
    public String mostradorPieza(Pieza pieza){
        String cadena = "";

        cadena += "ID: " + pieza.getId() + "\n";
        cadena += "Titulo: " + pieza.getTitulo() + "\n";
        cadena += "Autores: " + pieza.getAutoresString() + "\n";
        cadena += "Año: " + pieza.getAnio() + "\n";
        cadena += "Lugar de creación: " + pieza.getLugarCreacion() + "\n";
        cadena += "Valor: " + pieza.getValor() + "\n";
        if (pieza instanceof ObraEnPapel) {
            ObraEnPapel obra = (ObraEnPapel) pieza;
            
            cadena += "Tipo: " + obra.getTipo() + "\n";
            cadena += "Material: " + obra.getMaterial() + "\n";
            cadena += "Formato: " + obra.getFormato() + "\n";
        } else if (pieza instanceof Escultura){
            Escultura estatua = (Escultura) pieza;
            cadena += "Material: " + estatua.getMaterial() + "\n";
            cadena += "Altura: " + estatua.getAltura() + "\n";
            cadena += "Ancho: " + estatua.getAnchura() + "\n";
            cadena += "Profundidad: " + estatua.getProfundidad() + "\n";

            boolean electricidad = estatua.getElectricidad();
            if (electricidad) {
                cadena += "Requiere electricidad: Sí\n";
            } else {
                cadena += "Requiere electricidad: No\n";
            }
        } else if (pieza instanceof Audiovisual){
            Audiovisual audio = (Audiovisual) pieza;
            cadena += "Duración: " + audio.getDuracion() + "\n";
            cadena += "Formato: " + audio.getFormato() + "\n";
        }

        return cadena;
    }

    public String mostradorCompra(Compra compra) {
        String cadena = "";
        cadena += "ID: " + compra.getId() + "\n";
        cadena += "Comprador: " + compra.getComprador() + "\n";
        cadena += "Piezas: " + compra.getPiezas() + "\n";
        cadena += "Valor total: " + compra.getValor() + "\n";
        return cadena;
    }

    public String mostradorAutor(Autor autor) {
        String cadena = "";
        cadena += "Nombre: " + autor.getNombre() + "\n";
        cadena += "Tipo: " + autor.getTipo() + "\n";
        return cadena;
    }

    public String mostradorComprador(Comprador comprador) {
        String cadena = "";
        cadena += "ID: " + comprador.getId() + "\n";
        cadena += "Nombre: " + comprador.getNombre() + "\n";
        cadena += "Correo: " + comprador.getEmail() + "\n";
        cadena += "Teléfono: " + comprador.getTelefono() + "\n";

        return cadena;
    }
}
