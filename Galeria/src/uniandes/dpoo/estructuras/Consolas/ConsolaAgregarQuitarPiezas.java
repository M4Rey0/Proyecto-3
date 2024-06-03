package uniandes.dpoo.estructuras.Consolas;

import java.util.ArrayList;
import java.util.Scanner;

import uniandes.dpoo.estructuras.Exceptions.SobreValorException;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Audiovisual;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Escultura;
import uniandes.dpoo.estructuras.model.ObraEnPapel;
import uniandes.dpoo.estructuras.model.Pieza;

public class ConsolaAgregarQuitarPiezas {

    Controlador controlador;
    Scanner scanner = new Scanner(System.in);

    public Controlador ejecutar(Controlador controlador) {
        int opcion = 0;
        this.controlador = controlador;

        System.out.println("Desea agregar, quitar o vender una pieza?");
        System.out.println("1. Agregar");
        System.out.println("2. Quitar");
        System.out.println("3. Vender");

        System.out.println("Ingrese una opción");
        opcion = scanner.nextInt();

        switch (opcion) {
            case 1:
                crearPieza();
                break;
            case 2:
                eliminarPieza();
                break;
            case 3:
                venderPiezas();
                break;
        }

        scanner.close();
        return this.controlador;
    }

    private ArrayList<String> getTipoPiezas() {
        ArrayList<String> tipos = new ArrayList<>();
        tipos.add("Audiovisual");
        tipos.add("Escultura");
        tipos.add("Obra en papel");
        return tipos;
    }

    private void crearPieza() {
        System.out.println("Ingrese el tipo de pieza que desea agregar");
        ArrayList<String> tipos = getTipoPiezas();
        for (int i = 0; i < tipos.size(); i++) {
            System.out.println((i + 1) + ". " + tipos.get(i));
        }

        try {
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1:
                    crearAudiovisual();
                    break;
                case 2:
                    crearEscultura();
                    break;
                case 3:
                    crearObraEnPapel();
                    break;
            }
        } catch (Exception e) {
            System.out.println("Error al crear la pieza: " + e.getMessage());
        }
    }

    private void crearEscultura() throws Exception {
        Pieza pieza = crearPiezaBasica();
        System.out.println("Ingrese el material de la escultura");
        String material = scanner.next();
        int altura;
        int anchura;
        int profundidad;
        Boolean electricidad;

        do {
            System.out.println("Ingrese la altura de la escultura en cm enteros");
            altura = scanner.nextInt();
            System.out.println("Ingrese la anchura de la escultura cm enteros");
            anchura = scanner.nextInt();
            System.out.println("Ingrese la profundidad de la escultura en cm enteros");
            profundidad = scanner.nextInt();
            System.out.println("Ingrese si la escultura requiere electricidad (1 true/2 false)");
            if (scanner.nextInt() == 1) {
                electricidad = true;
            } else {
                electricidad = false;
            }
        } while (scanner.hasNextInt());

        Escultura escultura = new Escultura(pieza, altura, anchura, profundidad, material, electricidad);
        this.controlador.agregarPieza(escultura);
    }

    private void crearObraEnPapel() throws Exception {
        Pieza pieza = crearPiezaBasica();

        boolean continuar = true;
        String material = "";
        while (continuar) {
            System.out.println("Ingrese el material de la obra en papel");
            material = scanner.next();
            if (material.equals("")) {
                System.out.println("Debe ingresar un material");
            } else {
                continuar = false;
            }
        }

        continuar = true;

        System.out.println("Ingrese el tipo de la obra en papel");
        String tipo = "";
        while (continuar) {
            tipo = scanner.next();
            if (tipo.equals("")) {
                System.out.println("Debe ingresar un tipo");
            } else {
                continuar = false;
            }
        }

        System.out.println("Ingrese el formato de la obra en papel");
        String formato = scanner.next();

        ObraEnPapel obraEnPapel = new ObraEnPapel(pieza, tipo, formato, material);
        this.controlador.agregarPieza(obraEnPapel);
    }

    private void crearAudiovisual() throws Exception {
        Pieza pieza = crearPiezaBasica();
        System.out.println("Ingrese el formato del audiovisual");
        String formato = scanner.next();
        System.out.println("Ingrese la duración del audiovisual en segundos");
        int duracion = scanner.nextInt();

        Audiovisual audiovisual = new Audiovisual(pieza, duracion, formato);
        this.controlador.agregarPieza(audiovisual);
    }

    private Pieza crearPiezaBasica() throws Exception {

        Pieza pieza = new Audiovisual();

        System.out.println("Ingrese el id de la pieza");
        int id = scanner.nextInt();
        pieza.setId(id);
        System.out.println("Ingrese el titulo de la pieza");
        String titulo = scanner.next();
        pieza.setTitulo(titulo);
        System.out.println("Ingrese el año de creación de la pieza");
        int anio = scanner.nextInt();
        pieza.setAnio(anio);
        System.out.println("Ingrese el lugar de creación de la pieza");
        String lugarCreacion = scanner.next();
        pieza.setLugarCreacion(lugarCreacion);
        System.out.println("Ingrese el valor de la pieza");
        int valor = scanner.nextInt();
        pieza.setValor(valor);
        System.out.println("Ingrese si la pieza está disponible (true/false)");
        boolean disponible = scanner.nextBoolean();
        pieza.setDisponible(disponible);
        System.out.println("Ingrese el estado de la pieza");
        String estado = scanner.next();
        pieza.setEstado(estado);

        return pieza;
    }

    private void eliminarPieza() {
        System.out.println("Ingrese el id de la pieza que desea eliminar");
        int id = scanner.nextInt();
        try {
            this.controlador.quitarPieza(this.controlador.buscarPiezaPorId(id));
        } catch (Exception e) {
            System.out.println("La pieza no existe en el controlador");
        }
    }

    private void venderPiezas() {
        Compra compra = new Compra();
        System.out.println("Ingrese el id del usuario al que desea vender");

        int idUsuario = scanner.nextInt();
        Comprador comprador = null;
        while (comprador == null) {
            comprador = this.controlador.buscarCompradorPorId(idUsuario);
            if (comprador == null) {
                System.out.println("El comprador no existe en la base de datos");
            }
        }

        compra.setComprador(idUsuario);

        boolean agregar = true;

        while (agregar) {
            System.out.println("Desea agregar una pieza a la compra? (1 true/2 false)");
            int opcion = scanner.nextInt();
            if (opcion == 1) {
                System.out.println("Ingrese el id de la pieza que desea agregar a la compra");
                int idPieza = scanner.nextInt();
                Pieza pieza = this.controlador.buscarPiezaPorId(idPieza);
                if (pieza != null) {
                    try {
                        compra.agregarPieza(pieza, comprador);
                    } catch (SobreValorException e) {
                        System.out.println(
                                "La pieza sobrepasa el valor maximo del cliente, desea ampliarlo? (1 true/2 false)");
                        if (scanner.nextInt() == 1) {
                            System.out.println("Ingrese el nuevo valor maximo: ");
                            int nuevoValor = scanner.nextInt();
                            comprador.setLimiteCompra(nuevoValor);
                            System.out.println("Valor actualizado, pero recuerde que la pieza no se ha agregado");
                        }
                    } catch (Exception e) {
                        System.out.println("No puedes agregar la misma pieza");
                    }
                } else {
                    System.out.println("La pieza no existe en la base de datos");
                }
            } else {
                agregar = false;
            }
            compra.setMediante(Compra.VENTA_DIRECTA);

            try {
                controlador.agregarCompra(compra);
            } catch (Exception e) {
                System.out.println("Error al agregar la compra: ");
            }

        }
    }
}
