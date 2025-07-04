package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.HotelRepositorio;
import com.udec.actividad4.dominio.modelo.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HotelRepositorioImpl implements HotelRepositorio {

    private final Connection conexion;

    public HotelRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Hotel hotel) {
        String sql = "INSERT INTO hotel (id, nombre, categoriaEstrellas, direccion, telefono, nombreDirector) " +
                     "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, hotel.getId());
            stmt.setString(2, hotel.getNombre());
            stmt.setInt(3, hotel.getCategoriaEstrellas());
            stmt.setString(4, hotel.getDireccion());
            stmt.setString(5, hotel.getTelefono());
            stmt.setString(6, hotel.getNombreDirector());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el hotel", e);
        }
    }

    @Override
    public Optional<Hotel> buscarPorId(int id) {
        String sql = "SELECT * FROM hotel WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Hotel hotel = mapearDesdeResultSet(rs);
                    return Optional.of(hotel);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el hotel con ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Hotel> listarTodos() {
        List<Hotel> hoteles = new ArrayList<>();
        String sql = "SELECT * FROM hotel";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                hoteles.add(mapearDesdeResultSet(rs));
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los hoteles", e);
        }

        return hoteles;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM hotel WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el hotel con ID: " + id, e);
        }
    }

    @Override
    public void actualizar(Hotel hotel) {
        String sql = "UPDATE hotel SET nombre = ?, categoriaEstrellas = ?, direccion = ?, telefono = ?, nombreDirector = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, hotel.getNombre());
            stmt.setInt(2, hotel.getCategoriaEstrellas());
            stmt.setString(3, hotel.getDireccion());
            stmt.setString(4, hotel.getTelefono());
            stmt.setString(5, hotel.getNombreDirector());
            stmt.setInt(6, hotel.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el hotel", e);
        }
    }

    @Override
    public List<Hotel> buscarPorNombreOCategoria(String nombre, int estrellas) {
        List<Hotel> hoteles = new ArrayList<>();
        String sql = "SELECT * FROM hotel WHERE LOWER(nombre) LIKE ? OR categoriaEstrellas = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, "%" + nombre.toLowerCase() + "%");
            stmt.setInt(2, estrellas);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    hoteles.add(mapearDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar hoteles por nombre o categoría", e);
        }

        return hoteles;
    }

    private Hotel mapearDesdeResultSet(ResultSet rs) throws SQLException {
        Hotel hotel = new Hotel();
        hotel.setId(rs.getInt("id"));
        hotel.setNombre(rs.getString("nombre"));
        hotel.setCategoriaEstrellas(rs.getInt("categoriaEstrellas"));
        hotel.setDireccion(rs.getString("direccion"));
        hotel.setTelefono(rs.getString("telefono"));
        hotel.setNombreDirector(rs.getString("nombreDirector"));
        return hotel;
    }
}
