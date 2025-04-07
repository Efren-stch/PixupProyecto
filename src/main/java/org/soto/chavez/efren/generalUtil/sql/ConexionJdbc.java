package org.soto.chavez.efren.generalUtil.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class ConexionJdbc<T> {

    public static String user = "root";
    public static String password = "m0m3l0";
    public static String db = "pixup";
    public static String server = "localhost";
    protected Connection connection;

    public ConexionJdbc()
    {
    }

    public boolean testDriver()
    {
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
            return true;
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    private boolean loadConnection(String user, String password, String db, String server)
    {
        String url = null;
        if (user == null || password == null || db == null || server == null)
        {
            return false;
        }
        if ("".equals(user) || "".equals(password) || "".equals(db) || "".equals(server))
        {
            return false;
        }
        url = String.format("jdbc:mysql://%s/%s?user=%s&password=%s", server, db, user, password);
        try
        {
            if (!testDriver( ) )
            {
                return false;
            }
            connection = DriverManager.getConnection(url);
            return connection != null;
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean openConnection() {
        try {
            if (connection == null || connection.isClosed()) { // Verificar si la conexión es null o está cerrada
                if (!loadConnection(user, password, db, server)) {
                    return false;
                }
            }
            return true; // Si la conexión está abierta, retorna true
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void closeConnection( )
    {
        try
        {
            if (connection == null)
            {
                return;
            }
            if (connection.isClosed())
            {
                return;
            }
            connection.close();
        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }
    }
}
