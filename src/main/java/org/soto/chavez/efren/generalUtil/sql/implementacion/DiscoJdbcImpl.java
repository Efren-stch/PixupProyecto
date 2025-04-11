package org.soto.chavez.efren.generalUtil.sql.implementacion;


import org.soto.chavez.efren.model.catalogos.ClaseCatalogo;
import org.soto.chavez.efren.model.catalogos.agregarDisco.Disco;
import org.soto.chavez.efren.model.catalogos.agregarDisco.GeneroMusical;

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
                d.setPrecio(rs.getDouble("precio"));
                d.setExistencia(rs.getInt("existencia"));
                d.setDescuento(rs.getDouble("descuento"));
                d.setFechaLanzamiento(rs.getString("fechaLanzamiento"));
                d.setImagen(rs.getString("imagen"));
                d.setArtista(rs.getInt("idArtista"));
                d.setDisquera(rs.getInt("idDisquera"));
                d.setGeneroMusical(rs.getInt("idGeneroMusical"));
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
        String sql = "INSERT INTO TBL_DISCO (nombre, precio, existencia, descuento, fechaLanzamiento, imagen, idArtista, idDisquera, idGeneroMusical) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try {
            if (!openConnection()) return false;

            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, d.getNombre());
            ps.setDouble(2, d.getPrecio());

            ps.setInt(3, d.getExistencia());
            ps.setDouble(4, d.getDescuento());
            ps.setString(5, d.getFechaLanzamiento());
            ps.setString(6, d.getImagen());

            ps.setInt(7, d.getArtista().getId());
            ps.setInt(8, d.getDisquera().getId());
            ps.setInt(9, d.getGeneroMusical().getId());

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
            ps.setInt(2, d.getArtista().getId());
            ps.setInt(3, d.getDisquera().getId());
            ps.setInt(4, d.getGeneroMusical().getId());
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

    @Override
    public ClaseCatalogo findById(int id) {
        Statement statement = null;
        ResultSet resultSet = null;
        Disco disco = null;
        String sql = "Select * from TBL_DISCO WHERE id = %d";


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
                disco = new Disco();
                disco.setId(resultSet.getInt("id"));
                disco.setNombre(resultSet.getString("nombre"));
                disco.setPrecio(resultSet.getDouble("precio"));
                disco.setDescuento(resultSet.getDouble("descuento"));
                disco.setExistencia(resultSet.getInt("existencia"));
                disco.setFechaLanzamiento(resultSet.getString("fechaLanzamiento"));
                disco.setImagen(resultSet.getString("imagen"));
                disco.setDisquera(resultSet.getInt("idArtista"));
                disco.setArtista(resultSet.getInt("idDisquera"));
                disco.setGeneroMusical(resultSet.getInt("idGeneroMusical"));
            }
            resultSet.close();
            closeConnection();
            return disco;
        } catch (SQLException e) {
            return null;
        }
    }
}
