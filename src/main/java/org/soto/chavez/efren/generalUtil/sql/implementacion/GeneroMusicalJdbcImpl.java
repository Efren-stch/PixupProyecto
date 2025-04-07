package org.soto.chavez.efren.generalUtil.sql.implementacion;

import org.soto.chavez.efren.model.catalogos.agregarDisco.GeneroMusical;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class GeneroMusicalJdbcImpl extends CatalogoJdbcImpl<GeneroMusical> {
    private static GeneroMusicalJdbcImpl instance;

    private GeneroMusicalJdbcImpl() {}

    public static GeneroMusicalJdbcImpl getInstance() {
        if (instance == null) {
            instance = new GeneroMusicalJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<GeneroMusical> findAll() {
        ArrayList<GeneroMusical> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_GENERO_MUSICAL";

        try {
            if (!openConnection()) return null;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                GeneroMusical g = new GeneroMusical();
                g.setId(rs.getInt("id"));
                g.setNombre(rs.getString("nombre"));
                list.add(g);
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
    public boolean guardar(GeneroMusical g) {
        String sql = "INSERT INTO TBL_GENERO_MUSICAL (nombre) VALUES (?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, g.getNombre());

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    g.setId(rs.getInt(1));
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
    public boolean actualizar(GeneroMusical g) {
        String sql = "UPDATE TBL_GENERO_MUSICAL SET nombre = ? WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, g.getNombre());
            ps.setInt(2, g.getId());

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
        String sql = "DELETE FROM TBL_GENERO_MUSICAL WHERE id = ?";

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
