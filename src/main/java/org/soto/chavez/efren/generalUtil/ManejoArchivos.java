package org.soto.chavez.efren.generalUtil;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ManejoArchivos<T>{

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

    }

    public List<T> getLista() {
        return lista;
    }

    public void setLista(List<T> lista) {
        this.lista = lista;
    }
}
