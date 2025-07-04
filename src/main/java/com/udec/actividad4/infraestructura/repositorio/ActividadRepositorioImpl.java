package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.ActividadRepositorio;
import com.udec.actividad4.dominio.enums.TipoActividad;
import com.udec.actividad4.dominio.modelo.Actividad;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ActividadRepositorioImpl implements ActividadRepositorio {

    private final Connection conexion;

    public ActividadRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Actividad actividad) {
        String sql = "INSERT INTO actividad (id, hotelId, nombre, diaSemana, hora, descripcion, empleadoId, tipoActividad, precioPorPersona) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, actividad.getId());
            stmt.setInt(2, actividad.getHotelId());
            stmt.setString(3, actividad.getNombre());
            stmt.setString(4, actividad.getDiaSemana().name());
            stmt.setTime(5, Time.valueOf(actividad.getHora()));
            stmt.setString(6, actividad.getDescripcion());
            stmt.setInt(7, actividad.getEmpleadoId());
            stmt.setString(8, actividad.getTipoActividad().name());
            stmt.setDouble(9, actividad.getPrecioPorPersona());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la actividad", e);
        }
    }

    @Override
    public Optional<Actividad> buscarPorId(int id) {
        String sql = "SELECT * FROM actividad WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapearActividad(rs));
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la actividad con ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Actividad> listarTodos() {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT * FROM actividad";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                actividades.add(mapearActividad(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar todas las actividades", e);
        }

        return actividades;
    }

    @Override
    public void actualizar(Actividad actividad) {
        String sql = "UPDATE actividad SET hotelId = ?, nombre = ?, diaSemana = ?, hora = ?, descripcion = ?, empleadoId = ?, tipoActividad = ?, precioPorPersona = ? WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, actividad.getHotelId());
            stmt.setString(2, actividad.getNombre());
            stmt.setString(3, actividad.getDiaSemana().name());
            stmt.setTime(4, Time.valueOf(actividad.getHora()));
            stmt.setString(5, actividad.getDescripcion());
            stmt.setInt(6, actividad.getEmpleadoId());
            stmt.setString(7, actividad.getTipoActividad().name());
            stmt.setDouble(8, actividad.getPrecioPorPersona());
            stmt.setInt(9, actividad.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar la actividad con ID: " + actividad.getId(), e);
        }
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM actividad WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la actividad con ID: " + id, e);
        }
    }

    // Método de utilidad para mapear ResultSet a Actividad
    private Actividad mapearActividad(ResultSet rs) throws SQLException {
        Actividad actividad = new Actividad();
        actividad.setId(rs.getInt("id"));
        actividad.setHotelId(rs.getInt("hotelId"));
        actividad.setNombre(rs.getString("nombre"));
        actividad.setDiaSemana(DayOfWeek.valueOf(rs.getString("diaSemana")));
        actividad.setHora(rs.getTime("hora").toLocalTime());
        actividad.setDescripcion(rs.getString("descripcion"));
        actividad.setEmpleadoId(rs.getInt("empleadoId"));
        actividad.setTipoActividad(TipoActividad.valueOf(rs.getString("tipoActividad")));
        actividad.setPrecioPorPersona(rs.getDouble("precioPorPersona"));
        return actividad;
    }
}
