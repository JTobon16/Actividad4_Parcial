package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.EstanciaRepositorio;
import com.udec.actividad4.dominio.modelo.Estancia;

import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EstanciaRepositorioImpl implements EstanciaRepositorio {

    private final Connection conexion;

    public EstanciaRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Estancia estancia) {
        String sql = "INSERT INTO estancia (id, reserva_id, fecha_inicio, fecha_fin, habitaciones_ocupadas, finalizada) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, estancia.getId());
            stmt.setInt(2, estancia.getReservaId());
            stmt.setDate(3, Date.valueOf(estancia.getFechaInicio()));
            stmt.setDate(4, Date.valueOf(estancia.getFechaFin()));
            stmt.setString(5, convertirListaAString(estancia.getHabitacionesOcupadas()));
            stmt.setBoolean(6, estancia.isFinalizada());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la estancia", e);
        }
    }

    @Override
    public Optional<Estancia> buscarPorId(int id) {
        String sql = "SELECT * FROM estancia WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(construirEstancia(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la estancia con ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Estancia> listarTodas() {
        String sql = "SELECT * FROM estancia";
        List<Estancia> estancias = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                estancias.add(construirEstancia(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar estancias", e);
        }

        return estancias;
    }

    @Override
    public List<Estancia> listarPorHotel(int hotelId) {
        String sql = """
                     SELECT e.* 
                     FROM estancia e 
                     JOIN reserva r ON e.reserva_id = r.id 
                     WHERE r.hotel_id = ?
                     """;

        List<Estancia> estancias = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, hotelId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    estancias.add(construirEstancia(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar estancias por hotel", e);
        }

        return estancias;
    }

    @Override
    public List<Estancia> listarPorCliente(String clienteDni) {
        String sql = """
                     SELECT e.* 
                     FROM estancia e 
                     JOIN reserva r ON e.reserva_id = r.id 
                     WHERE r.cliente_dni = ?
                     """;

        List<Estancia> estancias = new ArrayList<>();

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, clienteDni);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    estancias.add(construirEstancia(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar estancias por cliente", e);
        }

        return estancias;
    }

    @Override
    public void actualizar(Estancia estancia) {
        String sql = "UPDATE estancia SET reserva_id = ?, fecha_inicio = ?, fecha_fin = ?, habitaciones_ocupadas = ?, finalizada = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, estancia.getReservaId());
            stmt.setDate(2, Date.valueOf(estancia.getFechaInicio()));
            stmt.setDate(3, Date.valueOf(estancia.getFechaFin()));
            stmt.setString(4, convertirListaAString(estancia.getHabitacionesOcupadas()));
            stmt.setBoolean(5, estancia.isFinalizada());
            stmt.setInt(6, estancia.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar estancia", e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM estancia WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar estancia", e);
        }
    }

    private Estancia construirEstancia(ResultSet rs) throws SQLException {
        Estancia estancia = new Estancia();
        estancia.setId(rs.getInt("id"));
        estancia.setReservaId(rs.getInt("reserva_id"));
        estancia.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
        estancia.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
        estancia.setHabitacionesOcupadas(convertirStringALista(rs.getString("habitaciones_ocupadas")));
        estancia.setFinalizada(rs.getBoolean("finalizada"));
        return estancia;
    }

    private String convertirListaAString(List<Integer> lista) {
        return lista != null ? lista.stream().map(String::valueOf).collect(Collectors.joining(",")) : "";
    }

    private List<Integer> convertirStringALista(String str) {
        if (str == null || str.isBlank()) return new ArrayList<>();
        return Stream.of(str.split(",")).map(Integer::parseInt).collect(Collectors.toList());
    }
    
     @Override
    public List<Estancia> obtenerEstanciasActivasPorHotel(int hotelId) {
    List<Estancia> estancias = new ArrayList<>();
    String sql = """
        SELECT e.*
        FROM estancia e
        JOIN reserva r ON e.reserva_id = r.id
        WHERE r.hotelId = ? AND e.finalizada = false
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, hotelId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Estancia estancia = new Estancia();
            estancia.setId(rs.getInt("id"));
            estancia.setReservaId(rs.getInt("reserva_id"));
            estancia.setFechaInicio(rs.getDate("fecha_inicio").toLocalDate());
            estancia.setFechaFin(rs.getDate("fecha_fin").toLocalDate());
            estancia.setFinalizada(rs.getBoolean("finalizada"));
            estancia.setHabitacionesOcupadas(convertirStringALista(rs.getString("habitaciones_ocupadas")));
            estancias.add(estancia);
        }
    } catch (SQLException e) {
        System.err.println("Error al consultar estancias activas por hotel: " + e.getMessage());
    }

    return estancias;
}


}
