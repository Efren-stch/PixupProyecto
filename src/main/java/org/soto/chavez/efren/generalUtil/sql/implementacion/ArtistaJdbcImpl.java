package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Artista;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ArtistaJdbcImpl extends CatalogoJdbcImpl<Artista> {
    private static ArtistaJdbcImpl instance;

    private ArtistaJdbcImpl() {
    }

    public static ArtistaJdbcImpl getInstance() {
        if (instance == null) {
            instance = new ArtistaJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Artista> findAll() {
        ArrayList<Artista> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_ARTISTA";

        try {
            if (!openConnection()) {
                System.out.println("No se pudo abrir la conexión.");
                return null;
            }

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Artista a = new Artista();
                a.setId(rs.getInt("id"));
                a.setNombre(rs.getString("nombre"));
                list.add(a);
            }

            rs.close();
            statement.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public boolean guardar(Artista a) {
        String sql = "INSERT INTO TBL_ARTISTA (nombre) VALUES (?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, a.getNombre());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    a.setId(rs.getInt(1));
                }
                rs.close();
                ps.close();
                return true;
            }

            ps.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return false;
    }

    @Override
    public boolean actualizar(Artista a) {
        String sql = "UPDATE TBL_ARTISTA SET nombre = ? WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, a.getNombre());
            ps.setInt(2, a.getId());

            int affectedRows = ps.executeUpdate();
            ps.close();

            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return false;
    }

    @Override
    public boolean eliminar(int id) {
        String sql = "DELETE FROM TBL_ARTISTA WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setInt(1, id);

            int affectedRows = ps.executeUpdate();
            ps.close();

            return affectedRows > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            closeConnection();
        }

        return false;
    }

    @Override
    public ClaseCatalogo findById(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Artista artista = null;
        String sql = "Select * from TBL_ARTISTA WHERE id = %d";


        try {
            if ( !openConnection() ) {
                return null;
            }
            sql = String.format(sql, id);
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            if ( resultSet == null ) {
                return null;
            }
            while (resultSet.next()) {
                artista = new Artista();
                artista.setId(resultSet.getInt("id"));
                artista.setNombre(resultSet.getString("nombre"));
            }
            resultSet.close();
            closeConnection();
            return artista;
        } catch (SQLException e) {
            return null;
        }
    }
}
