package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.model.catalogos.registrarUsuario.Colonia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ColoniaJdbcImpl extends CatalogoJdbcImpl<Colonia> {
    private static ColoniaJdbcImpl instance;

    private ColoniaJdbcImpl() {}

    public static ColoniaJdbcImpl getInstance() {
        if (instance == null) {
            instance = new ColoniaJdbcImpl();
        }
        return instance;
    }

    @Override
    public ArrayList<Colonia> findAll() {
        ArrayList<Colonia> list = new ArrayList<>();
        String sql = "SELECT * FROM TBL_COLONIA";

        try {
            if (!openConnection()) {
                System.out.println("No se pudo abrir la conexión.");
                return null;
            }

            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                Colonia c = new Colonia();
                c.setId(rs.getInt("id"));
                c.setNombre(rs.getString("nombre"));
                c.setIdMunicipio(rs.getInt("idMunicipio")); // Relación con Municipio
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
    public boolean guardar(Colonia c) {
        String sql = "INSERT INTO TBL_COLONIA (nombre, idMunicipio) VALUES (?, ?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdMunicipio()); // Relación con Municipio

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
    public boolean actualizar(Colonia c) {
        String sql = "UPDATE TBL_COLONIA SET nombre = ?, idMunicipio = ? WHERE id = ?";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, c.getNombre());
            ps.setInt(2, c.getIdMunicipio()); // Relación con Municipio
            ps.setInt(3, c.getId());

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
        String sql = "DELETE FROM TBL_COLONIA WHERE id = ?";

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
