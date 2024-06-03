package uniandes.dpoo.estructuras.Consolas;

import java.util.Scanner;
import java.time.LocalDate;
import java.util.InputMismatchException;

import uniandes.dpoo.estructuras.Exceptions.SobreValorException;
import uniandes.dpoo.estructuras.Logica.Controlador;
import uniandes.dpoo.estructuras.model.Pieza;
import uniandes.dpoo.estructuras.model.RangoFechas;
import uniandes.dpoo.estructuras.model.Compra;
import uniandes.dpoo.estructuras.model.Comprador;

public class ConsolaVenderPiezas {
    Controlador controlador;
    Scanner scanner = new Scanner(System.in);

    public Controlador ejecutar(Controlador controlador) {
        this.controlador = controlador;
        System.out.println("Crear nueva orden de compra");
        System.out.println("Ingrese el id del comprador");

        int idComprador = -1;
        Comprador comprador = null;
        while (comprador == null) {
            try {
                idComprador = scanner.nextInt();
                comprador = controlador.buscarCompradorPorId(idComprador);
            } catch (Exception e) {
                System.out.println("No se encontró el comprador");
                System.out.println("Ingrese el id del comprador");
                idComprador = scanner.nextInt();
            }
        }

        boolean agregandoProductos = true;
        Compra compra = new Compra();
        compra.setMediante(Compra.VENTA_DIRECTA);

        while (agregandoProductos) {
            System.out.println("Ingrese el id de la pieza a ingresar, ingrese 0 de lo contrario");
            int idPieza = scanner.nextInt();
            if (idPieza == 0) {
                agregandoProductos = false;
            } else {
                try {
                    Pieza pieza = controlador.buscarPiezaPorId(idPieza);
                    if (compra.contienePieza(pieza)) {
                        System.out.println("La pieza ya está en la compra");
                    } else {
                        if (pieza.getEstado().equals(Pieza.VENDIDA)) {
                            System.out.println("La pieza ya ha sido vendida, no se puede agregar a la compra");
                            continue;
                        } else if (pieza.isDisponible() == false) {
                            System.out.println("La pieza no está disponible, no se puede agregar a la compra");
                            continue;
                        } else{
                            try{
                                compra.agregarPieza(pieza, comprador);
                                pieza.agregarPropietario(comprador, new RangoFechas(LocalDate.now(), null));
                                pieza.setDisponible(false);
                                pieza.setEstado(Pieza.VENDIDA);
                                controlador.agregarPieza(pieza);
                                comprador.agregarPieza(idPieza);
                                controlador.agregarComprador(comprador);
                                System.out.println("Pieza agregada a la compra");
                            } catch (SobreValorException e){
                                System.out.println("El valor de la pieza es mayor al valor maximo del comprador");
                            }
                        }

                    }
                } catch (InputMismatchException e) {
                    System.out.println("Input invalido, por favor ingrese un numero entero");
                } catch (Exception e) {
                    System.out.println("No se encontró la pieza");
                }
            }
        }

        try {
            controlador.agregarCompra(compra);
        } catch (Exception e) {
            System.out.println("No se pudo agregar la compra");
        }
        return this.controlador;
    }
}
