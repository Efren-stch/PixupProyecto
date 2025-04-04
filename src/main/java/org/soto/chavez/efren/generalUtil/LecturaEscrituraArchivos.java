/*
package org.soto.chavez.efren.generalUtil;
import org.soto.chavez.efren.lugares.Lugar;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LecturaEscrituraArchivos<T extends Lugar>{

}
    public static File file;
    public static ObjectInputStream objectInputStream = null;
    public static FileInputStream fileInputStream = null;
    public static ObjectOutputStream objectOutputStream = null;
    public static FileOutputStream fileOutputStream = null;


    private Class<T> tipo;
    private List<T> lista;

    public ArchivoLugar(Class<T> tipo){
        this
    }
}

    public static void leerArchivo(String ruta, ArrayList<? extends Lugar> list){
        try
        {
            file = getFile(ruta);
            fileInputStream = new FileInputStream( file );
            objectInputStream = new ObjectInputStream( fileInputStream );


            objectInputStream.close( );
            fileInputStream.close( );
            System.out.println( "Archivo leido con exito");
        }
        catch (FileNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (ClassNotFoundException e)
        {
            throw new RuntimeException(e);
        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
}
*/