package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.registrarUsuario.Estado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstadoJdbcImpl extends CatalogoJdbcImpl<Estado> {
    private static EstadoJdbcImpl instance;

    private EstadoJdbcImpl() {
    }

    public static EstadoJdbcImpl getInstance() {
        if (instance == null) {
            instance = new EstadoJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Estado> findAll() {
        ArrayList<Estado> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_ESTADO";

        try {
            if (!openConnection()) {
                System.out.println("No se pudo abrir la conexión.");
                return null;
            }

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if ( rs == null ) {
                return null;
            }

            while (rs.next()) {
                Estado e = new Estado();
                e.setId(rs.getInt("id"));
                e.setNombre(rs.getString("nombre"));
                list.add(e);
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
    public boolean guardar(Estado e) {
        String sql = "INSERT INTO TBL_ESTADO (nombre) VALUES (?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, e.getNombre());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    e.setId(rs.getInt(1));
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
    public boolean actualizar(Estado e) {
        String sql = "UPDATE TBL_ESTADO SET nombre = ? WHERE ID = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, e.getNombre());
            ps.setInt(2, e.getId());

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
        String sql = "DELETE FROM TBL_ESTADO WHERE id = ?";

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
        Estado estado = null;
        String sql = "Select * from TBL_ESTADO WHERE id = %d";


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
                estado = new Estado();
                estado.setId(resultSet.getInt("id"));
                estado.setNombre(resultSet.getString("nombre"));
            }
            resultSet.close();
            closeConnection();
            return estado;
        } catch (SQLException e) {
            return null;
        }
    }

}
