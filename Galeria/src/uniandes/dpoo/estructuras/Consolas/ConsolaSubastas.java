package uniandes.dpoo.estructuras.Consolas;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.Logica.LogicaSubastas;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.RangoFechas;
import uniandes.dpoo.estructuras.model.Subasta;

public class ConsolaSubastas {
    private Controlador controlador;
    private LogicaSubastas logica = new LogicaSubastas();
    Scanner scanner = new Scanner(System.in);
    Subasta subastaActual;

    public Controlador ejecutarConsolaSubastas(Controlador controlador){
        this.controlador = controlador;

        Subasta subasta = new Subasta();
        
        boolean agregandoProductos = true;
        while (agregandoProductos){
            System.out.println("Ingrese el id de la pieza a ingresar, ingrese 0 de lo contrario");
            int idPieza = scanner.nextInt();
            if (idPieza == 0){
                agregandoProductos = false;
            } else {
                try {
                    Pieza pieza = controlador.buscarPiezaPorId(idPieza);
                    if (subasta.contienePieza(pieza)){
                        System.out.println("La pieza ya está en la subasta");
                    } else{
                        if (pieza.getEstado().equals(Pieza.VENDIDA)){
                            System.out.println("La pieza ya ha sido vendida, no se puede agregar a la subasta");
                            continue;
                        } else if (pieza.isDisponible() == false){
                            System.out.println("La pieza no está disponible, no se puede agregar a la subasta");
                            continue;
                        }

                        subasta.agregarPieza(pieza);
                        System.out.println("Ingrese el precio inicial de la pieza");
                        int precioInicial = scanner.nextInt();
                        subasta.agregarValorInicial(idPieza, precioInicial);
                        System.out.println("Ingrese el precio minimo de la pieza");
                        int precioMinimo = scanner.nextInt();
                        subasta.agregarValorMinimo(idPieza, precioMinimo);
                        logica.agregarPieza(pieza, precioInicial, precioMinimo);
                        System.out.println("Pieza agregada a la subasta");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input invalido, por favor ingrese un numero entero");
                }
                catch (Exception e) {
                    System.out.println("La pieza no existe");
                }
            }
        }

        System.out.println("Ingrese a los compradores de la subasta, ingrese 0 de lo contrario");
        boolean agregandoCompradores = true;
        while (agregandoCompradores){
            System.out.println("Ingrese el id del comprador a ingresar");
            int idComprador = scanner.nextInt();
            if (idComprador == 0){
                agregandoCompradores = false;
            } else {
                try {
                    controlador.buscarCompradorPorId(idComprador);
                    subasta.agregarComprador(controlador.buscarCompradorPorId(idComprador));
                    System.out.println("Comprador agregado a la subasta");
                } catch (Exception e) {
                    System.out.println("El comprador no existe o id invalido");
                }
            }
        }

        try {
            controlador.agregarSubasta(subasta);
            this.subastaActual = subasta;
            iniciarSubasta();
        } catch (Exception e) {
            System.out.println("Error al agregar la subasta, intente de nuevo");
        }

        return this.controlador;
    }

    
    private void iniciarSubasta(){
        System.out.println("Iniciando subasta");
        System.out.println("Las piezas en subasta son: ");
        System.out.println(logica.getMostradorPiezas());

        for (Pieza pieza: logica.getPiezasAgregadas()){
            subastarPieza(pieza);
        }
    }

    private void imprimirIdCompradores(){
        System.out.println("Los compradores en la subasta son: ");
        for (Integer id: subastaActual.getCompradores()){
            System.out.println(id);
        }
    }

    private void subastarPieza(Pieza pieza){
        Comprador ganador = null;
        int valorActual = subastaActual.getValorInicial(pieza.getId());

        System.out.println("Inicia la subasta de: " + pieza.getTitulo());
        System.out.println("Precio inicial: " + subastaActual.getValorInicial(pieza.getId()));



        boolean subastando = true;
        while (subastando){
            imprimirIdCompradores();
            System.out.println("Ingrese el id del comprador que desea pujar, ingrese 0 de lo contrario");
            int idComprador = scanner.nextInt();
            if (idComprador == 0){
                subastando = false;
            } else {
                try {
                    Comprador comprador = controlador.buscarCompradorPorId(idComprador);
                    if (subastaActual.getCompradores().contains(idComprador)){
                        System.out.println("Ingrese el valor de la puja");
                        int valorPuja = scanner.nextInt();
                        if (valorPuja > valorActual){
                            ganador = comprador;
                            valorActual = valorPuja;
                        } else {
                            System.out.println("El valor de la puja debe ser mayor al valor minimo");
                        }
                    } else {
                        System.out.println("El comprador no está en la subasta");
                    }
                } catch (Exception e) {
                    System.out.println("El comprador no existe o id invalido");
                }
            }
        }

        System.out.println("Subasta finalizada");
        System.out.println("El ganador de la subasta es: " + ganador.getNombre());

        formalizarCompra(ganador, pieza, valorActual);
    }

    private void formalizarCompra(Comprador ganador, Pieza pieza, int valor){
        ArrayList<String> mediosDePago = controlador.getTiposMediosPago();
        System.out.println("Medios de pago disponibles: ");
        for (String medio: mediosDePago){
            System.out.println(medio);
        }

        System.out.println("Ingrese el medio de pago del ganador");
        String medioPago = scanner.next();
        
        boolean pagando = true;
        while (pagando){
            if (mediosDePago.contains(medioPago)){
                try {
                    Compra compra = new Compra(pieza, controlador.buscarMedioPago(medioPago), valor, ganador);
                    controlador.agregarCompra(compra);
                    pieza.agregarCompra(compra);
                    pieza.agregarPropietario(ganador, new RangoFechas(LocalDate.now(), null));
                    System.out.println("Compra realizada exitosamente");
                } catch (Exception e) {
                    System.out.println("Error al realizar la compra");
                }
            } else {
                System.out.println("Medio de pago no disponible");
            }
        }
    }
}
