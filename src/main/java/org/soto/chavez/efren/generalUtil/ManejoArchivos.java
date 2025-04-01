package org.soto.chavez.efren.generalUtil;

import org.soto.chavez.efren.lugares.Lugar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos<T extends Lugar>{

    private final Class<T> tipo;
    private List<T> lista;

    private File file;

    public ManejoArchivos(Class<T> tipo) {
        this.tipo = tipo;
        this.lista = new ArrayList<>();
    }

    private File getFile() {
        return new File( tipo.getSimpleName().toLowerCase() + ".dat" );
    }

    public void guardarArchivo() {
        ObjectOutputStream objectOutputStream = null;
        FileOutputStream fileOutputStream = null;

        try{
            file = getFile( );
            fileOutputStream = new FileOutputStream( file );
            objectOutputStream = new ObjectOutputStream( fileOutputStream );

            objectOutputStream.writeObject(lista);
            System.out.println("Archivo guardado con éxito: " + getFile().getName());
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        try ( ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(obtenerNombreArchivo()))) {

            oos.writeObject(lista);
            System.out.println("Archivo guardado con éxito: " + obtenerNombreArchivo());

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al guardar el archivo.");
        }

         */
    }

    public void leerArchivo() {
        ObjectInputStream objectInputStream = null;
        FileInputStream fileInputStream = null;
        file = getFile( );
        if (!file.exists()) {
            System.out.println("No existe el archivo: " + getFile().getName());
            return;
        }

        try {
            fileInputStream = new FileInputStream( file );
            objectInputStream = new ObjectInputStream( fileInputStream );

            Object object = objectInputStream.readObject();
            if (object instanceof List<?>) {
                List<?> objetos = (List<?>) object;

                lista = new ArrayList<>();

                for (Object elemento : objetos) {
                    if (tipo.isInstance(elemento)) {
                        lista.add(tipo.cast(elemento));
                    }
                }
                System.out.println("Archivo leído con éxito: " + getFile().getName());
            }

            System.out.println( "Archivo leido con exito");
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        File archivo = new File(obtenerNombreArchivo());
        if (!archivo.exists()) {
            System.out.println("No existe el archivo: " + obtenerNombreArchivo());
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {

            Object obj = ois.readObject();
            if (obj instanceof List<?>) {
                List<?> objetos = (List<?>) obj;

                lista = new ArrayList<>();

                for (Object elemento : objetos) {
                    if (tipo.isInstance(elemento)) {
                        lista.add(tipo.cast(elemento));
                    }
                }
                System.out.println("Archivo leído con éxito: " + obtenerNombreArchivo());
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error al leer el archivo.");
        }

         */
    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
}
