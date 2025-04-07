package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.model.catalogos.agregarDisco.Disquera;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DisqueraJdbcImpl extends CatalogoJdbcImpl<Disquera> {
    private static DisqueraJdbcImpl instance;

    private DisqueraJdbcImpl() {}

    public static DisqueraJdbcImpl getInstance() {
        if (instance == null) {
            instance = new DisqueraJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Disquera> findAll() {
        ArrayList<Disquera> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_DISQUERA";

        try {
            if (!openConnection()) return null;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Disquera d = new Disquera();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                list.add(d);
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
    public boolean guardar(Disquera d) {
        String sql = "INSERT INTO TBL_DISQUERA (nombre) VALUES (?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, d.getNombre());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    d.setId(rs.getInt(1));
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
    public boolean actualizar(Disquera d) {
        String sql = "UPDATE TBL_DISQUERA SET nombre = ? WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getId());

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
        String sql = "DELETE FROM TBL_DISQUERA WHERE id = ?";

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
}
