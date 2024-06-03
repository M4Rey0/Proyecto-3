package uniandes.dpoo.estructuras.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Pieza {

    private static int nextId = 1;

    private int id;
    private String titulo;
    private int anio;
    private String lugarCreacion;
    private int valor;
    private boolean disponible;
    public static final String VENDIDA = "vendida";
    public static final String BODEGA = "bodega";
    public static final String EXHIBIDA = "exhibida";
    private String estado; //vendida, bodega, exhibida
    private List<Integer> compras = new ArrayList<>();
    private List<String> autores = new ArrayList<>();
    private HashMap<RangoFechas, Integer> propietarios = new HashMap<>();

    public Pieza(int id, String titulo, int anio, String lugarCreacion, int valor, boolean disponible, String estado, List<Autor> autores, List<Compra> compras) {
        this.id = id;
        this.titulo = titulo;
        this.anio = anio;
        this.lugarCreacion = lugarCreacion;
        this.valor = valor;
        this.disponible = disponible;
        this.estado = estado;

        nextId ++;
    }

    public Pieza(Pieza pieza){
        this.id = pieza.getId();
        this.titulo = pieza.getTitulo();
        this.anio = pieza.getAnio();
        this.lugarCreacion = pieza.getLugarCreacion();
        this.valor = pieza.getValor();
        this.disponible = pieza.isDisponible();
        this.estado = pieza.getEstado();
        this.autores = pieza.getAutores();
        this.propietarios = pieza.getPropietarios();
        this.compras = pieza.getCompras();
    }

    public Pieza(){
        this.id = nextId++;
    }

    @Override
    public String toString() {
        return
                id +
                "," + titulo +
                "," + anio +
                "," + lugarCreacion +
                "," + valor +
                "," + disponible +
                "," + estado +
                "," + getComprasString() +
                "," + getAutoresString() +
                "," + getPropietariosString();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Pieza){
            Pieza pieza = (Pieza) obj;
            return pieza.getId() == this.getId();
        }
        return false;
    }

    public String getAutoresString(){
        String autoresString = "";
        for (String autor : autores){
            autoresString += autor + "-";
        }
        return autoresString;
    }

    public String getPropietariosString(){
        String propietariosString = "";
        for (Map.Entry<RangoFechas, Integer> entry : propietarios.entrySet()){
            if (entry.getKey().getFechaFin() == null){
                propietariosString += entry.getValue() + ";" + entry.getKey().getFechaInicio().toString() + "_/";
                continue;
            }
            propietariosString += entry.getValue() + ";" + entry.getKey().getFechaInicio().toString() + "_" + entry.getKey().getFechaFin().toString() + "/";
        }
        return propietariosString;
    }

    public String getComprasString(){
        String comprasString = "";
        for (Integer compra : compras){
            comprasString += compra + "-";
        }
        return comprasString;
    }

    public void agregarAutor(Autor autor)throws Exception{
        if (autores.contains(autor.getNombre())){
            throw new Exception("El autor ya existe en la lista de autores.");
        }

        autores.add(autor.getNombre());
    }

    public void agregarPropietario(Comprador propietario, RangoFechas rangoFechas)throws Exception{
        if (propietarios.containsKey(rangoFechas)){
            throw new Exception("Ya existe un propietario para el rango de fechas especificado.");
        }

        propietarios.put(rangoFechas, propietario.getId());
    }

    public void agregarCompra(Compra compra)throws Exception{
        if (compras.contains(compra.getId())){
            throw new Exception("La compra ya existe en la lista de compras.");
        }
        compras.add(compra.getId());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getLugarCreacion() {
        return lugarCreacion;
    }

    public void setLugarCreacion(String lugarCreacion) {
        this.lugarCreacion = lugarCreacion;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<Integer> getCompras() {
        return compras;
    }

    public void setCompras(List<Integer> compras) {
        this.compras = compras;
    }

    public List<String> getAutores() {
        return autores;
    }

    public void setAutores(List<String> autores) {
        this.autores = autores;
    }

    public HashMap<RangoFechas, Integer> getPropietarios() {
        return propietarios;
    }

    public void setPropietarios(HashMap<RangoFechas, Integer> propietarios) {
        this.propietarios = propietarios;
    }
}
