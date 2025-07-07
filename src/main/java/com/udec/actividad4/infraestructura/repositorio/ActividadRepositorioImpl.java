package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.ActividadRepositorio;
import com.udec.actividad4.dominio.enums.TipoActividad;
import com.udec.actividad4.dominio.modelo.Actividad;

import java.sql.*;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
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
    
    @Override
    public List<Actividad> obtenerActividadesPorHotel(int hotelId) {
        List<Actividad> actividades = new ArrayList<>();
        String sql = "SELECT * FROM actividad WHERE hotelId = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, hotelId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Actividad actividad = new Actividad(
                    rs.getInt("id"),
                    rs.getInt("hotelId"),
                    rs.getString("nombre"),
                    DayOfWeek.valueOf(rs.getString("diaSemana").toUpperCase()),
                    rs.getTime("hora").toLocalTime(),
                    rs.getString("descripcion"),
                    rs.getInt("empleadoId"),
                    TipoActividad.valueOf(rs.getString("tipoActividad")),
                    rs.getDouble("precioPorPersona")
                );
                actividades.add(actividad);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener actividades por hotel", e);
        }

        return actividades;
    }
    
    @Override
public List<Actividad> obtenerActividadesPorEmpleado(int empleadoId) {
    List<Actividad> actividades = new ArrayList<>();
    String sql = "SELECT * FROM actividad WHERE empleadoId = ?";

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, empleadoId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Actividad actividad = new Actividad(
                rs.getInt("id"),
                rs.getInt("hotelId"),
                rs.getString("nombre"),
                DayOfWeek.valueOf(rs.getString("diaSemana").toUpperCase()),
                rs.getTime("hora").toLocalTime(),
                rs.getString("descripcion"),
                rs.getInt("empleadoId"),
                TipoActividad.valueOf(rs.getString("tipoActividad").toUpperCase()),
                rs.getDouble("precioPorPersona")
            );
            actividades.add(actividad);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener actividades por empleado", e);
    }

    return actividades;
}

    //consulta 12
    @Override
    public List<Actividad> obtenerActividadesContratadasPorCliente(String clienteDni) {
    List<Actividad> actividades = new ArrayList<>();
    String sql = """
        SELECT a.*
        FROM actividad a
        JOIN detalleActividad dac ON a.id = dac.actividadId
        JOIN estancia e ON dac.estanciaId = e.id
        JOIN reserva r ON e.id = r.id
        WHERE r.clienteDni = ?
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setString(1, clienteDni);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            actividades.add(mapearActividad(rs));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener actividades contratadas por cliente", e);
    }

    return actividades;
}

    
    
    @Override
public List<Actividad> obtenerHistorialActividadesPagasPorHotel(int hotelId) {
    List<Actividad> actividades = new ArrayList<>();
    String sql = """
        SELECT a.*
        FROM actividad a
        JOIN detalleActividad dac ON a.id = dac.actividadId
        WHERE a.tipoActividad = 'PAGA'
          AND a.hotelId = ?
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, hotelId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            actividades.add(mapearActividad(rs));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener historial de actividades pagas por hotel", e);
    }

    return actividades;
}


    @Override
    public Map<Integer, Double> obtenerIngresosPorActividadesPagasPorHotel() {
    Map<Integer, Double> ingresosPorHotel = new HashMap<>();

    String sql = """
        SELECT a.hotelId, SUM(dac.cantidadPersonas * a.precioPorPersona) AS ingresosTotales
        FROM detalleActividad dac
        JOIN actividad a ON dac.actividadId = a.id
        WHERE a.tipoActividad = 'DE_PAGO'
        GROUP BY a.hotelId
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int hotelId = rs.getInt("hotelId");
            double ingresos = rs.getDouble("ingresosTotales");
            ingresosPorHotel.put(hotelId, ingresos);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener ingresos por actividades de pago por hotel", e);
    }

    return ingresosPorHotel;
}

    
    @Override
    public Map<Integer, Integer> obtenerHotelesConMasActividades() {
    Map<Integer, Integer> actividadesPorHotel = new LinkedHashMap<>();

    String sql = """
        SELECT hotelId, COUNT(*) AS cantidad
        FROM actividad
        GROUP BY hotelId
        ORDER BY cantidad DESC
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int hotelId = rs.getInt("hotelId");
            int cantidad = rs.getInt("cantidad");
            actividadesPorHotel.put(hotelId, cantidad);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener hoteles con más actividades", e);
    }

    return actividadesPorHotel;
}

    @Override
    public List<Actividad> obtenerActividadesMasContratadasPorHotel() {
    List<Actividad> actividades = new ArrayList<>();
    String sql = """
        SELECT a.*, COUNT(*) AS vecesContratada
        FROM actividad a
        JOIN detalleActividad da ON a.id = da.actividadId
        GROUP BY a.id, a.hotelId, a.nombre, a.diaSemana, a.hora, a.descripcion, a.empleadoId, a.tipoActividad, a.precioPorPersona
        ORDER BY a.hotelId ASC, vecesContratada DESC
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {
        while (rs.next()) {
            actividades.add(mapearActividad(rs));
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener actividades más contratadas por hotel", e);
    }

    return actividades;
}
    //consulta 25
    public List<String> listarServiciosAdicionalesPorEstanciaCliente() {
    List<String> servicios = new ArrayList<>();
    String sql = """
        SELECT c.dni, c.nombreCompleto, a.nombre AS actividad, a.descripcion, da.cantidadPersonas, e.fecha_inicio, e.fecha_fin
        FROM cliente c
        JOIN reserva r ON c.dni = r.clienteDni
        JOIN estancia e ON r.id = e.reserva_id
        JOIN detalleActividad da ON e.id = da.estanciaId
        JOIN actividad a ON da.actividadId = a.id
        ORDER BY c.dni, e.fecha_inicio
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String detalle = String.format(
                "Cliente: %s - %s | Actividad: %s (%s) | Personas: %d | Estancia: %s a %s",
                rs.getString("dni"),
                rs.getString("nombreCompleto"),
                rs.getString("actividad"),
                rs.getString("descripcion"),
                rs.getInt("cantidadPersonas"),
                rs.getDate("fecha_inicio").toLocalDate(),
                rs.getDate("fecha_fin").toLocalDate()
            );
            servicios.add(detalle);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al listar servicios adicionales contratados", e);
    }

    return servicios;
}

}
