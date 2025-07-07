package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.HabitacionRepositorio;
import com.udec.actividad4.dominio.enums.TipoHabitacion;
import com.udec.actividad4.dominio.modelo.Habitacion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class HabitacionRepositorioImpl implements HabitacionRepositorio {

    private final Connection conexion;

    public HabitacionRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Habitacion habitacion) {
        String sql = "INSERT INTO habitacion (id, hotelId, tipo, precioBasePorNoche, tieneVistaMar, tieneJacuzzi, tieneBalcon) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, habitacion.getId());
            stmt.setInt(2, habitacion.getHotelId());
            stmt.setString(3, habitacion.getTipo().name());
            stmt.setDouble(4, habitacion.getPrecioBasePorNoche());
            stmt.setBoolean(5, habitacion.isTieneVistaMar());
            stmt.setBoolean(6, habitacion.isTieneJacuzzi());
            stmt.setBoolean(7, habitacion.isTieneBalcon());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la habitación", e);
        }
    }

    @Override
    public Optional<Habitacion> buscarPorId(int id) {
        String sql = "SELECT * FROM habitacion WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Habitacion habitacion = mapearDesdeResultSet(rs);
                    return Optional.of(habitacion);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la habitación con ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Habitacion> listarPorHotel(int hotelId) {
        List<Habitacion> habitaciones = new ArrayList<>();
        String sql = "SELECT * FROM habitacion WHERE hotelId = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, hotelId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    habitaciones.add(mapearDesdeResultSet(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar habitaciones por hotel", e);
        }

        return habitaciones;
    }

    @Override
    public void actualizar(Habitacion habitacion) {
        String sql = "UPDATE habitacion SET hotelId = ?, tipo = ?, precioBasePorNoche = ?, " +
                     "tieneVistaMar = ?, tieneJacuzzi = ?, tieneBalcon = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, habitacion.getHotelId());
            stmt.setString(2, habitacion.getTipo().name());
            stmt.setDouble(3, habitacion.getPrecioBasePorNoche());
            stmt.setBoolean(4, habitacion.isTieneVistaMar());
            stmt.setBoolean(5, habitacion.isTieneJacuzzi());
            stmt.setBoolean(6, habitacion.isTieneBalcon());
            stmt.setInt(7, habitacion.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la habitación", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM habitacion WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la habitación con ID: " + id, e);
        }
    }

    private Habitacion mapearDesdeResultSet(ResultSet rs) throws SQLException {
        Habitacion habitacion = new Habitacion();
        habitacion.setId(rs.getInt("id"));
        habitacion.setHotelId(rs.getInt("hotelId"));
        habitacion.setTipo(TipoHabitacion.valueOf(rs.getString("tipo")));
        habitacion.setPrecioBasePorNoche(rs.getDouble("precioBasePorNoche"));
        habitacion.setTieneVistaMar(rs.getBoolean("tieneVistaMar"));
        habitacion.setTieneJacuzzi(rs.getBoolean("tieneJacuzzi"));
        habitacion.setTieneBalcon(rs.getBoolean("tieneBalcon"));
        return habitacion;
    }
    
    //consulta 16
    @Override
    public List<String> obtenerTarifasPorHotelYTipo() {
    List<String> tarifas = new ArrayList<>();
    String sql = """
        SELECT hotelId, tipo, precioBasePorNoche
        FROM habitacion
        ORDER BY hotelId, tipo
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int hotelId = rs.getInt("hotelId");
            String tipo = rs.getString("tipo");
            double precio = rs.getDouble("precioBasePorNoche");
            tarifas.add("Hotel ID: " + hotelId + " | Tipo: " + tipo + " | Precio: $" + precio);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al consultar tarifas por hotel y tipo", e);
    }

    return tarifas;
}

    //consulta 24
    @Override
    public List<String> obtenerPreciosHabitacionesConSuplementos() {
    List<String> resultados = new ArrayList<>();

    String sql = """
        SELECT 
            h.id AS habitacionId,
            h.hotelId,
            h.tipo,
            h.precioBasePorNoche,
            h.tieneVistaMar,
            h.tieneJacuzzi,
            h.tieneBalcon,
            COALESCE(SUM(s.valorPorNoche), 0) AS suplementoTotal,
            (h.precioBasePorNoche + COALESCE(SUM(s.valorPorNoche), 0)) AS precioTotal
        FROM habitacion h
        LEFT JOIN suplemento s ON s.hotelId = h.hotelId
          AND (
               (h.tieneVistaMar = 1 AND s.descripcion LIKE '%vista%') OR
               (h.tieneJacuzzi = 1 AND s.descripcion LIKE '%jacuzzi%') OR
               (h.tieneBalcon = 1 AND s.descripcion LIKE '%balcon%')
          )
        GROUP BY h.id, h.hotelId, h.tipo, h.precioBasePorNoche, h.tieneVistaMar, h.tieneJacuzzi, h.tieneBalcon
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String info = String.format(
                "Habitacion ID: %d | Hotel ID: %d | Tipo: %s | Base: $%.2f | Vista Mar: %s | Jacuzzi: %s | Balcon: %s | Suplemento Total: $%.2f | Precio Total: $%.2f",
                rs.getInt("habitacionId"),
                rs.getInt("hotelId"),
                rs.getString("tipo"),
                rs.getDouble("precioBasePorNoche"),
                rs.getBoolean("tieneVistaMar") ? "Si" : "No",
                rs.getBoolean("tieneJacuzzi") ? "Si" : "No",
                rs.getBoolean("tieneBalcon") ? "Si" : "No",
                rs.getDouble("suplementoTotal"),
                rs.getDouble("precioTotal")
            );

            resultados.add(info);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al consultar precios de habitaciones con suplementos", e);
    }

    return resultados;
}

}
