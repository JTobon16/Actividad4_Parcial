package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.ReservaRepositorio;
import com.udec.actividad4.dominio.modelo.DetalleReservaHabitacion;
import com.udec.actividad4.dominio.modelo.Reserva;
import com.udec.actividad4.infraestructura.dtos.ReservaDetalleDTO;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ReservaRepositorioImpl implements ReservaRepositorio {

    private final Connection conexion;

    public ReservaRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Reserva reserva) {
        String sql = "INSERT INTO reserva (id, clienteDni, hotelId, fechaInicio, fechaFin, senalPagada, confirmada) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, reserva.getId());
            stmt.setString(2, reserva.getClienteDni());
            stmt.setInt(3, reserva.getHotelId());
            stmt.setDate(4, Date.valueOf(reserva.getFechaInicio()));
            stmt.setDate(5, Date.valueOf(reserva.getFechaFin()));
            stmt.setDouble(6, reserva.getSenalPagada());
            stmt.setBoolean(7, reserva.isConfirmada());

            stmt.executeUpdate();

            // Aquí podrías guardar habitacionesReservadas si tienes una tabla `detalle_reserva_habitacion`
            // y si se requiere manejo completo, lo vemos luego

        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la reserva", e);
        }
    }

    @Override
    public Optional<Reserva> buscarPorId(int id) {
        String sql = "SELECT * FROM reserva WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Reserva reserva = new Reserva();
                    reserva.setId(rs.getInt("id"));
                    reserva.setClienteDni(rs.getString("clienteDni"));
                    reserva.setHotelId(rs.getInt("hotelId"));
                    reserva.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                    reserva.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                    reserva.setSenalPagada(rs.getDouble("senalPagada"));
                    reserva.setConfirmada(rs.getBoolean("confirmada"));

                    // Las habitacionesReservadas se podrían llenar luego si se hace join con tabla detalle

                    return Optional.of(reserva);
                }
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la reserva con ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Reserva> listar() {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Reserva reserva = new Reserva();
                reserva.setId(rs.getInt("id"));
                reserva.setClienteDni(rs.getString("clienteDni"));
                reserva.setHotelId(rs.getInt("hotelId"));
                reserva.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                reserva.setFechaFin(rs.getDate("fechaFin").toLocalDate());
                reserva.setSenalPagada(rs.getDouble("senalPagada"));
                reserva.setConfirmada(rs.getBoolean("confirmada"));
                reservas.add(reserva);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar reservas", e);
        }

        return reservas;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM reserva WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la reserva con ID: " + id, e);
        }
    }

    @Override
    public void actualizar(Reserva reserva) {
        String sql = "UPDATE reserva SET clienteDni = ?, hotelId = ?, fechaInicio = ?, fechaFin = ?, " +
                     "senalPagada = ?, confirmada = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, reserva.getClienteDni());
            stmt.setInt(2, reserva.getHotelId());
            stmt.setDate(3, Date.valueOf(reserva.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(reserva.getFechaFin()));
            stmt.setDouble(5, reserva.getSenalPagada());
            stmt.setBoolean(6, reserva.isConfirmada());
            stmt.setInt(7, reserva.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la reserva", e);
        }
    }
    
    // consulta 7
    @Override
    public List<ReservaDetalleDTO> obtenerReservasActivasConDetalle() {
    List<ReservaDetalleDTO> resultados = new ArrayList<>();
    String sql = """
        SELECT c.nombreCompleto, r.id, r.fechaInicio, r.fechaFin, e.habitaciones_ocupadas
        FROM reserva r
        JOIN cliente c ON r.clienteDni = c.dni
        JOIN estancia e ON r.id = e.reserva_id
        WHERE r.confirmada = TRUE AND e.finalizada = 0
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            ReservaDetalleDTO dto = new ReservaDetalleDTO(
                rs.getString("nombreCompleto"),
                rs.getInt("id"),
                rs.getString("fechaInicio"),
                rs.getString("fechaFin"),
                rs.getString("habitaciones_ocupadas")
            );
            resultados.add(dto);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al consultar reservas activas", e);
    }
    return resultados;
}
    
    
  // consulta 8
    @Override
    public List<Reserva> obtenerHistorialReservasCliente(String clienteDni) {
        List<Reserva> reservas = new ArrayList<>();
        String sql = "SELECT * FROM reserva WHERE clienteDni = ? ORDER BY fechaInicio DESC";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, clienteDni);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Reserva reserva = new Reserva(
                        rs.getInt("id"),
                        rs.getString("clienteDni"),
                        rs.getInt("hotelId"),
                        rs.getDate("fechaInicio").toLocalDate(),
                        rs.getDate("fechaFin").toLocalDate(),
                        rs.getDouble("senalPagada"),
                        rs.getBoolean("confirmada"),
                        null // habitacionesReservadas se puede cargar aparte si se requiere
                );
                reservas.add(reserva);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener historial de reservas del cliente", e);
        }

        return reservas;
    }
    
   @Override
    public List<String> obtenerReservasConDetalleCliente() {
    List<String> resultados = new ArrayList<>();

    String sql = """
        SELECT r.id AS idReserva, r.fechaInicio, r.fechaFin,
               c.nombreCompleto, c.dni, c.telefono
        FROM reserva r
        JOIN cliente c ON r.clienteDni = c.dni
        ORDER BY r.fechaInicio
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String linea = String.format(
                "Reserva ID: %d | Inicio: %s | Fin: %s | Cliente: %s (%s) - Tel: %s",
                rs.getInt("idReserva"),
                rs.getDate("fechaInicio"),
                rs.getDate("fechaFin"),
                rs.getString("nombreCompleto"),
                rs.getString("dni"),
                rs.getString("telefono")
            );
            resultados.add(linea);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener reservas con detalle de cliente", e);
    }

    return resultados;
}



}
