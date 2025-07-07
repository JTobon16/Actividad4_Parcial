package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.HotelRepositorio;
import com.udec.actividad4.dominio.enums.TipoHabitacion;
import com.udec.actividad4.dominio.modelo.Habitacion;
import com.udec.actividad4.dominio.modelo.Hotel;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    // consulta 1
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
    
    
    // Consulta 2
    @Override
    public List<String> obtenerContactosYDirectores() {
    String sql = "SELECT nombre, telefono, nombreDirector FROM hotel";
    List<String> resultado = new ArrayList<>();

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String linea = String.format("Hotel: %s | Teléfono: %s | Director: %s",
                    rs.getString("nombre"),
                    rs.getString("telefono"),
                    rs.getString("nombreDirector"));
            resultado.add(linea);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener contactos y directores", e);
    }

    return resultado;
}
    
    //Consulta 3
    @Override
    public Map<String, Map<String, Integer>> contarEmpleadosPorTipoPorHotel() {
            Map<String, Map<String, Integer>> resultado = new HashMap<>();

    String sql = "SELECT h.nombre AS hotel, e.tipo AS tipo, COUNT(*) AS total " +
                 "FROM empleado e " +
                 "JOIN hotel h ON e.hotelId = h.id " +
                 "GROUP BY h.nombre, e.tipo";

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String nombreHotel = rs.getString("hotel");
            String tipo = rs.getString("tipo");
            int total = rs.getInt("total");

            resultado.putIfAbsent(nombreHotel, new HashMap<>());
            resultado.get(nombreHotel).put(tipo, total);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al contar empleados por tipo y hotel", e);
    }

    return resultado;
}

    //Consulta 4
    @Override
    public List<String> obtenerResumenEmpleados() {
    List<String> resumen = new ArrayList<>();
    String sql = "SELECT nombreCompleto, nivelEducativo, tipo FROM empleado";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String nombre = rs.getString("nombreCompleto");
                String titulo = rs.getString("nivelEducativo");
                String tipo = rs.getString("tipo"); // Enum como String
                resumen.add("Nombre: " + nombre + " | Título: " + titulo + " | Cargo: " + tipo);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener resumen de empleados", e);
    }

    return resumen;
}
    //consulta 5
    
    @Override

    public Map<String, Integer> contarHabitacionesDisponiblesPorTipo(int hotelId) {
    Map<String, Integer> resultado = new HashMap<>();

    String sql = "SELECT h.tipo, COUNT(*) AS cantidad " +
                 "FROM habitacion h " +
                 "WHERE h.hotelId = ? " +
                 "AND NOT EXISTS ( " +
                 "    SELECT 1 FROM reserva r " +
                 "    WHERE r.Id = h.id " +
                 "    AND r.hotelId = h.hotelId " +
                 "    AND CURDATE() BETWEEN r.fechaInicio AND r.fechaFin " +
                 ") " +
                 "GROUP BY h.tipo";

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, hotelId);
        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            String tipo = rs.getString("tipo");
            int cantidad = rs.getInt("cantidad");
            resultado.put(tipo, cantidad);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al contar habitaciones disponibles por tipo", e);
    }

    return resultado;
}

        //consulta 6
    @Override
    public List<Habitacion> obtenerHabitacionesOcupadasEnPeriodo(int hotelId, LocalDate inicio, LocalDate fin) {
    List<Habitacion> ocupadas = new ArrayList<>();

    String sql = "SELECT DISTINCT h.id, h.tipo, h.precioBasePorNoche, h.tieneVistaMar, h.tieneJacuzzi, h.tieneBalcon " +
                 "FROM habitacion h " +
                 "JOIN reserva r ON h.id = r.Id AND h.hotelId = r.hotelId " +
                 "WHERE h.hotelId = ? " +
                 "AND ( " +
                 "      (r.fechaInicio <= ? AND r.fechaFin >= ?) OR " +
                 "      (r.fechaInicio BETWEEN ? AND ?) OR " +
                 "      (r.fechaFin BETWEEN ? AND ?) " +
                 ")";

    try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
        stmt.setInt(1, hotelId);
        stmt.setDate(2, Date.valueOf(fin));
        stmt.setDate(3, Date.valueOf(inicio));
        stmt.setDate(4, Date.valueOf(inicio));
        stmt.setDate(5, Date.valueOf(fin));
        stmt.setDate(6, Date.valueOf(inicio));
        stmt.setDate(7, Date.valueOf(fin));

        ResultSet rs = stmt.executeQuery();

        while (rs.next()) {
            Habitacion h = new Habitacion();
            h.setId(rs.getInt("id"));
            h.setHotelId(hotelId);
            h.setTipo(TipoHabitacion.valueOf(rs.getString("tipo")));
            h.setPrecioBasePorNoche(rs.getDouble("precioBasePorNoche"));
            h.setTieneVistaMar(rs.getBoolean("tieneVistaMar"));
            h.setTieneJacuzzi(rs.getBoolean("tieneJacuzzi"));
            h.setTieneBalcón(rs.getBoolean("tieneBalcon"));
            ocupadas.add(h);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al consultar habitaciones ocupadas", e);
    }

    return ocupadas;
}

    // consulta 17
    @Override
    public List<String> obtenerDirectoresDeHoteles() {
    List<String> directores = new ArrayList<>();
    String sql = """
        SELECT id, nombre, nombreDirector
        FROM hotel
        WHERE nombreDirector IS NOT NULL AND nombreDirector <> ''
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String linea = "Hotel: " + rs.getString("nombre") +
                           " (ID: " + rs.getInt("id") + ") - Director: " + rs.getString("nombreDirector");
            directores.add(linea);
        }

    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener los directores de hoteles", e);
    }

    return directores;
}

    //consulta 22 
   @Override
    public List<String> listarHotelesConMayorOcupacionEn6Meses() {
    List<String> resultado = new ArrayList<>();
    String sql = """
        SELECT h.nombre AS nombreHotel, COUNT(*) AS totalOcupacion
        FROM estancia e
        JOIN reserva r ON e.reserva_id = r.id
        JOIN detallereservahabitacion drh ON r.id = drh.reservaId
        JOIN habitacion hab ON drh.habitacionId = hab.id
        JOIN hotel h ON hab.hotelId = h.id
        WHERE e.fecha_inicio >= DATE_SUB(CURDATE(), INTERVAL 6 MONTH)
        GROUP BY h.nombre
        ORDER BY totalOcupacion DESC
    """;

    try (PreparedStatement stmt = conexion.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            String hotel = rs.getString("nombreHotel");
            int ocupacion = rs.getInt("totalOcupacion");
            resultado.add("Hotel: " + hotel + " | Ocupaciones en 6 meses: " + ocupacion);
        }
    } catch (SQLException e) {
        throw new RuntimeException("Error al obtener hoteles con mayor ocupación", e);
    }

    return resultado;
}



}
