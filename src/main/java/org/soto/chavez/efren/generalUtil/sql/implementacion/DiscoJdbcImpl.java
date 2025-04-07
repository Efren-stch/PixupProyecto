package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.model.catalogos.agregarDisco.Disco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DiscoJdbcImpl extends CatalogoJdbcImpl<Disco> {
    private static DiscoJdbcImpl instance;

    private DiscoJdbcImpl() {}

    public static DiscoJdbcImpl getInstance() {
        if (instance == null) {
            instance = new DiscoJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Disco> findAll() {
        ArrayList<Disco> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_DISCO";

        try {
            if (!openConnection()) return null;

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Disco d = new Disco();
                d.setId(rs.getInt("id"));
                d.setNombre(rs.getString("nombre"));
                d.setIdArtista(rs.getInt("idArtista"));
                d.setIdDisquera(rs.getInt("idDisquera"));
                d.setIdGeneroMusical(rs.getInt("idGeneroMusical"));
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
    public boolean guardar(Disco d) {
        String sql = "INSERT INTO TBL_DISCO (nombre, idArtista, idDisquera, idGeneroMusical) VALUES (?, ?, ?, ?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getIdArtista());
            ps.setInt(3, d.getIdDisquera());
            ps.setInt(4, d.getIdGeneroMusical());

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
    public boolean actualizar(Disco d) {
        String sql = "UPDATE TBL_DISCO SET nombre = ?, idArtista = ?, idDisquera = ?, idGeneroMusical = ? WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, d.getNombre());
            ps.setInt(2, d.getIdArtista());
            ps.setInt(3, d.getIdDisquera());
            ps.setInt(4, d.getIdGeneroMusical());
            ps.setInt(5, d.getId());

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
        String sql = "DELETE FROM TBL_DISCO WHERE id = ?";

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
