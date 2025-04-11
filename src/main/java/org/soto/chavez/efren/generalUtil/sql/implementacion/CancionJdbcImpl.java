package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Cancion;
import org.soto.chavez.efren.model.catalogos.agregarDisco.GeneroMusical;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CancionJdbcImpl extends CatalogoJdbcImpl<Cancion> {
    private static CancionJdbcImpl instance;

    private CancionJdbcImpl() {}

    public static CancionJdbcImpl getInstance() {
        if (instance == null) {
            instance = new CancionJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Cancion> findAll() {
        ArrayList<Cancion> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_CANCION";

        try {
            if (!openConnection()) return null;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Cancion c = new Cancion();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setDuracion(rs.getDouble("duracion"));
                c.setDisco(rs.getInt("idDisco"));
                list.add(c);
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
    public boolean guardar(Cancion c) {
        String sql = "INSERT INTO TBL_CANCION (nombre, duracion, idDisco) VALUES (?, ?, ?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getNombre());
            ps.setDouble(2, c.getDuracion());
            ps.setInt(3, c.getDisco().getId());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    c.setId(rs.getInt(1));
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
    public boolean actualizar(Cancion c) {
        String sql = "UPDATE TBL_CANCION SET nombre = ?, duracion = ?, idDisco = ? WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setDouble(2, c.getDuracion());
            ps.setInt(3, c.getDisco().getId());
            ps.setInt(4, c.getId());

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
        String sql = "DELETE FROM TBL_CANCION WHERE id = ?";

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
        Cancion cancion = null;
        String sql = "Select * from TBL_CANCION WHERE id = %d";


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
                cancion = new Cancion();
                cancion.setId(resultSet.getInt("id"));
                cancion.setNombre(resultSet.getString("nombre"));
                cancion.setDuracion(resultSet.getInt("duracion"));
                cancion.setDisco(resultSet.getInt("idDisco"));
            }
            resultSet.close();
            closeConnection();
            return cancion;
        } catch (SQLException e) {
            return null;
        }
    }
}
