package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.SuplementoRepositorio;
import com.udec.actividad4.dominio.enums.TipoSuplemento;
import com.udec.actividad4.dominio.modelo.Suplemento;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SuplementoRepositorioImpl implements SuplementoRepositorio {

    private final Connection conexion;

    public SuplementoRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Suplemento suplemento) {
        String sql = "INSERT INTO suplemento (id, hotelId, descripcion, tipo, valorPorNoche, fechaInicio, fechaFin) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, suplemento.getId());
            stmt.setInt(2, suplemento.getHotelId());
            stmt.setString(3, suplemento.getDescripcion());
            stmt.setString(4, suplemento.getTipo().name());
            stmt.setDouble(5, suplemento.getValorPorNoche());
            stmt.setDate(6, Date.valueOf(suplemento.getFechaInicio()));
            stmt.setDate(7, Date.valueOf(suplemento.getFechaFin()));

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el suplemento", e);
        }
    }

    @Override
    public Optional<Suplemento> buscarPorId(int id) {
        String sql = "SELECT * FROM suplemento WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Suplemento suplemento = new Suplemento();
                    suplemento.setId(rs.getInt("id"));
                    suplemento.setHotelId(rs.getInt("hotelId"));
                    suplemento.setDescripcion(rs.getString("descripcion"));
                    suplemento.setTipo(TipoSuplemento.valueOf(rs.getString("tipo")));
                    suplemento.setValorPorNoche(rs.getDouble("valorPorNoche"));
                    suplemento.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                    suplemento.setFechaFin(rs.getDate("fechaFin").toLocalDate());

                    return Optional.of(suplemento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar suplemento por ID", e);
        }

        return Optional.empty();
    }

    @Override
    public List<Suplemento> listarPorTipo(String tipo) {
        List<Suplemento> suplementos = new ArrayList<>();
        String sql = "SELECT * FROM suplemento WHERE tipo = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, tipo);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Suplemento suplemento = new Suplemento();
                    suplemento.setId(rs.getInt("id"));
                    suplemento.setHotelId(rs.getInt("hotelId"));
                    suplemento.setDescripcion(rs.getString("descripcion"));
                    suplemento.setTipo(TipoSuplemento.valueOf(rs.getString("tipo")));
                    suplemento.setValorPorNoche(rs.getDouble("valorPorNoche"));
                    suplemento.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                    suplemento.setFechaFin(rs.getDate("fechaFin").toLocalDate());

                    suplementos.add(suplemento);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar suplementos por tipo", e);
        }

        return suplementos;
    }

    @Override
    public List<Suplemento> listarTodos() {
        List<Suplemento> suplementos = new ArrayList<>();
        String sql = "SELECT * FROM suplemento";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Suplemento suplemento = new Suplemento();
                suplemento.setId(rs.getInt("id"));
                suplemento.setHotelId(rs.getInt("hotelId"));
                suplemento.setDescripcion(rs.getString("descripcion"));
                suplemento.setTipo(TipoSuplemento.valueOf(rs.getString("tipo")));
                suplemento.setValorPorNoche(rs.getDouble("valorPorNoche"));
                suplemento.setFechaInicio(rs.getDate("fechaInicio").toLocalDate());
                suplemento.setFechaFin(rs.getDate("fechaFin").toLocalDate());

                suplementos.add(suplemento);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todos los suplementos", e);
        }

        return suplementos;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM suplemento WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el suplemento", e);
        }
    }

    @Override
    public void actualizar(Suplemento suplemento) {
        String sql = "UPDATE suplemento SET hotelId = ?, descripcion = ?, tipo = ?, valorPorNoche = ?, fechaInicio = ?, fechaFin = ? " +
                     "WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, suplemento.getHotelId());
            stmt.setString(2, suplemento.getDescripcion());
            stmt.setString(3, suplemento.getTipo().name());
            stmt.setDouble(4, suplemento.getValorPorNoche());
            stmt.setDate(5, Date.valueOf(suplemento.getFechaInicio()));
            stmt.setDate(6, Date.valueOf(suplemento.getFechaFin()));
            stmt.setInt(7, suplemento.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el suplemento", e);
        }
    }
    
    
   @Override
    public List<Suplemento> obtenerSuplementosTemporadaPorFecha(LocalDate fechaConsulta) {
    List<Suplemento> suplementos = new ArrayList<>();
    String sql = "SELECT * FROM suplemento WHERE tipo = 'TEMPORADA' AND ? BETWEEN fechaInicio AND fechaFin";

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setDate(1, Date.valueOf(fechaConsulta));
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Suplemento suplemento = new Suplemento(
                rs.getInt("id"),
                rs.getInt("hotelId"),
                rs.getString("descripcion"),
                TipoSuplemento.valueOf(rs.getString("tipo")),
                rs.getDouble("valorPorNoche"),
                rs.getDate("fechaInicio").toLocalDate(),
                rs.getDate("fechaFin").toLocalDate()
            );
            suplementos.add(suplemento);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener suplementos por temporada", e);
    }

    return suplementos;
}

    
    //Consulta 15
    @Override
    public List<Suplemento> obtenerSuplementosPorCaracteristicas() {
    List<Suplemento> suplementos = new ArrayList<>();
    String sql = "SELECT * FROM suplemento WHERE tipo = 'POR_CARACTERISTICA'";

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            Suplemento suplemento = new Suplemento(
                rs.getInt("id"),
                rs.getInt("hotelId"),
                rs.getString("descripcion"),
                TipoSuplemento.valueOf(rs.getString("tipo")),
                rs.getDouble("valorPorNoche"),
                rs.getDate("fechaInicio") != null ? rs.getDate("fechaInicio").toLocalDate() : null,
                rs.getDate("fechaFin") != null ? rs.getDate("fechaFin").toLocalDate() : null
            );
            suplementos.add(suplemento);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener suplementos por caracteristicas", e);
    }

    return suplementos;
}


}
