/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aeropuerto;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author yuda
 */
public class Aeropuerto {

    /**
     * @param args the command line arguments
     *
     */
    // Conector JDBC y patrón Singleton
    private static final ConnexionBD bbdd = new ConnexionBD();

    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        int opcion = 0;
        // TODO code application logic here
        do {
            System.out.println("Elija una opcion: ");
            System.out.println("0.Mostrar y pedir información de la base de datos en general");
            System.out.println("1.Mostrar la información de la tabla pasajeros");
            System.out.println("2.Ver la información de los pasajeros de un vuelo, pasando el código de vuelo como parámetro");
            System.out.println("3.Insertar un vuelo cuyos valores se pasan como parámetros");
            System.out.println("4.Borrar el vuelo que se metió anteriormente en el que se pasa por parámetro su número de vuelo");
            System.out.println("5.Modificar los vuelos de fumadores a no fumadores");
            System.out.println("6.salir");
            try {
                opcion = input.nextInt();
                if (opcion != 0 && opcion != 1 && opcion != 2 && opcion != 3 && opcion != 4 && opcion != 5 && opcion != 6) {
                    System.out.println("Debe escribir un numero entre  0-6 ");
                } else {
                    opcionesMenu(opcion, input);
                }
            } catch (InputMismatchException e) {
                System.out.println("Debe escribir solo números");
                input.next();
            }

        } while (opcion != 6);

    }

    public static void opcionesMenu(int opcion, Scanner input) {
        switch (opcion) {
            case 0:
                System.out.println("Has seleccionado la opción 0");
                bbdd.mostrarVuelos();
                break;
            case 1:
                System.out.println("Has seleccionado la opción 1");
                bbdd.mostrarPasajeros();
                break;
            case 2:
                System.out.println("Has seleccionado la opción 2");

                String codigo = setcodigoVuelo(input);
                bbdd.mostrarPasajerosVuelo(codigo);

                break;
            case 3:
                System.out.println("Has seleccionado la opción 3");
                String codigoVuelo = setcodigoVuelo(input);
                Date fechaHora = validarFechaHora(input);
                System.out.println("Introduzca el origen del vuelo: ");
                String destino = setDatos(input);
                System.out.println("Introduzca destino del vuelo: ");
                String procedencia = setDatos(input);
                System.out.println("Introduzaca numero de plazas para fumador");
                int pF = setNumeros(input);
                System.out.println("Introduzaca numero de plazas para NO fumador");
                int pNof = setNumeros(input);
                System.out.println("Introduzaca numero de plazas tipo TURISTA");
                int pT = setNumeros(input);
                System.out.println("Introduzaca numero de plazas tipo PRIMERA");
                int pP = setNumeros(input);
                bbdd.insertarVuelo(codigoVuelo, fechaHora, destino, procedencia, pF, pNof, pT, pP);
                break;
            case 4:
                System.out.println("Has seleccionado la opción 4");
                break;
            case 5:
                System.out.println("Has seleccionado la opción 5");
                break;
            case 6:
                System.out.println("Gracias por utilizar el programa que tenga un buen dia");
                break;
        }
    }

    public static String setcodigoVuelo(Scanner input) {

        String codigoVuelo;
        boolean formatoCorrecto = true;
        String regexformato = "^[A-Z]{2}-[A-Z0-9]{2}-[0-9]{3,4}$";
        do {
            System.out.println("Introduzca un codigo de vuelo  con el siguiente formato => 2*LETRAS+2*(LETRAS-NUEMROS)+ (3-4)*(NUMEROS): ");
            codigoVuelo = input.nextLine();
            Pattern pattern = Pattern.compile(regexformato);
            Matcher matcher = pattern.matcher(codigoVuelo);
            formatoCorrecto = matcher.matches();
            if (!formatoCorrecto) {
                System.out.println("Formato incorrecto");
            }

        } while (!formatoCorrecto);
        return codigoVuelo;
    }

    /*Este metodo valida que la cadena de fecha y hora introducida tenga valores correctos
    *recibe el String fechaHora que ya tiene un formato correcto
     */
    public static boolean entrarFecha(String fechaHora) {
        if (fechaHora == null) {
            return false;
        }
        int hora = Integer.valueOf(fechaHora.substring(11, 13));
        System.out.println(hora);
        int min = Integer.valueOf(fechaHora.substring(14, 16));
        System.out.println(min);
        int dia = Integer.valueOf(fechaHora.substring(8, 10));
        System.out.println(dia);
        int mes = Integer.valueOf(fechaHora.substring(5, 7));
        System.out.println(mes);
        int anio = Integer.valueOf(fechaHora.substring(0, 4));
        System.out.println(anio);
        boolean validoFecha = true;
        boolean anioBisciesto = false;

        if ((anio % 4 == 0 && anio % 400 == 0 && anio % 100 == 0) || (anio % 4 == 0 && anio % 100 != 0)) {
            anioBisciesto = true;
        }
        if (hora < 0 || hora > 23) {
            validoFecha = false;
        }
        if (min < 0 || min > 59) {
            validoFecha = false;
        }

        if (dia < 1 || dia > 31) {
            validoFecha = false;
        }
        if (mes == 2 && dia > 28 && !anioBisciesto) {
            validoFecha = false;
        }
        if ((mes == 2 || mes == 4 || mes == 6 || mes == 9 || mes == 11) && (dia == 31)) {
            validoFecha = false;
        }

        if (mes < 1 || mes > 12) {
            validoFecha = false;
        }
        /*Para saber si un año es bisiesto se puede aplicar una simple formula, 
        la cual dice que un año es bisiesto si es divisible por cuatro
        , excepto los principios de año (los divisibles por 100),
        que para ser bisiestos deben de ser divisibles también por 400.*/

        if (mes == 2 && dia == 29 && !anioBisciesto) {
            validoFecha = false;
        }

        return validoFecha;
    }

    public static Date validarFechaHora(Scanner input) {
        String fechaHora = "";
        Date thisDate = new Date();
        boolean formatoCorrecto = true;
        //utilizamos la calse DateFormat para establecer un formato para la hora y fecha
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        do {
            formatoCorrecto = true;
            System.out.println("Escriba la fecha  y la hora de salida del vuelo en formato yyyy-mm-dd hh:mm:ss : ");
            fechaHora = input.nextLine();

            try {
                //intentamos parsear el string que introduce el usuario y si falla es que esta mal, asi que repetimos
                thisDate = dateFormat.parse(fechaHora);
                //LocalDateTime dateTime = LocalDateTime.parse(fechaHora, formatter);
            } catch (ParseException ex) {
                formatoCorrecto = false;
                fechaHora = null;
                System.out.println("Formato de fecha/hora incorrecto");
            }
            formatoCorrecto = entrarFecha(fechaHora);
        } while (!formatoCorrecto);

        return thisDate;
    }

    public static String setDatos(Scanner input) {
        String dato = "";
        boolean correcto = true;
        String formato = "[A-Z ]{2,254}";
        do {
            System.out.println("Introduza una cadena:");
            dato = input.nextLine().toUpperCase();
            Pattern pattern = Pattern.compile(formato);
            Matcher matcher = pattern.matcher(dato);
            correcto = matcher.matches();

        } while (!correcto);

        return dato;
    }

    public static int setNumeros(Scanner input) {

        int dato = 0;
        boolean correcto;;
        do {
            correcto = true;
            System.out.println("Introduzca un número:");
            try {
                dato = input.nextInt();
            } catch (InputMismatchException ei) {
                input.next();
                correcto = false;
            }
        } while (!correcto);

        return dato;
    }
}
