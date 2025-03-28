package org.soto.chavez.efren.generalUtil;

public class Salidas {
    //Menus
    public static String menuPrincipal = (
        "Bienvenido al programa!\n" +
        "SELECCIONA UNA OPCION\n" +
        "1.- Consola\n" +
        "2.- Ventana\n" +
        "3.- Salir"
    );
    public static String menuSeccion2 = (
        "---- Menú ----\n" +
        "¿Qué operación desea realizar?:\n" +
        "1. Catálogo\n" +
        "2. Pendiente\n" +
        "3. Salir"
    );
    public static String menuUbicacion = (
        "1. Estado\n" +
        "2. Municipio\n" +
        "3. Colonia\n" +
        "4. Salir"
    );
    public static String menuABM = (
        "1. Alta\n" +
        "2. Baja\n" +
        "3. Ver\n" +
        "4. Actualizar\n" +
        "5. Salir"
    );
    public static String leerId = "¿Cuál es el id?";
    public static String leerNombre = "¿Cuál es el nombre? ";
    public static String leerCP = "¿Cuál es el codigo postal? ";

    public static String nuevoNombre = "¿Cuál es el nuevo nombre?";


    public static String seleccionarOpcion = "Selecciona una opción: ";

    public static String listaVacia = "No hay estados registrados";
    public static String elementoNoEncontrado = "No se encontró el elemento";
    public static void opcionInvalida() {
        System.out.println("La opción no es correcta");
    }
    public static void errorDato() {
        System.out.println("No es un dato valido");
    }

    public static void terminarPrograma(){
        System.out.println("¡Hasta luego!");
        System.out.println("No olvides cerrar la ventana si aún sigue abierta");
    }
}