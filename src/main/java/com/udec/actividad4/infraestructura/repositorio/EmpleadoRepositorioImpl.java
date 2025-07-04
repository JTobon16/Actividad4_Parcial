package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.EmpleadoRepositorio;
import com.udec.actividad4.dominio.enums.TipoEmpleado;
import com.udec.actividad4.dominio.modelo.Empleado;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmpleadoRepositorioImpl implements EmpleadoRepositorio {

    private final Connection conexion;

    public EmpleadoRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Empleado empleado) {
        String sql = "INSERT INTO empleado (id, nombreCompleto, direccion, dni, nivelEducativo, tipo, hotelId) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, empleado.getId());
            stmt.setString(2, empleado.getNombreCompleto());
            stmt.setString(3, empleado.getDireccion());
            stmt.setString(4, empleado.getDni());
            stmt.setString(5, empleado.getNivelEducativo());
            stmt.setString(6, empleado.getTipo().name());
            stmt.setInt(7, empleado.getHotelId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el empleado", e);
        }
    }

    @Override
    public Optional<Empleado> buscarPorDni(String dni) {
        String sql = "SELECT * FROM empleado WHERE dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Empleado empleado = construirEmpleadoDesdeResultSet(rs);
                    return Optional.of(empleado);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar empleado con DNI: " + dni, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Empleado> listarTodos() {
        List<Empleado> empleados = new ArrayList<>();
        String sql = "SELECT * FROM empleado";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                empleados.add(construirEmpleadoDesdeResultSet(rs));
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al listar empleados", e);
        }

        return empleados;
    }

    @Override
    public void actualizar(Empleado empleado) {
        String sql = "UPDATE empleado SET nombreCompleto = ?, direccion = ?, nivelEducativo = ?, tipo = ?, hotelId = ? WHERE dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, empleado.getNombreCompleto());
            stmt.setString(2, empleado.getDireccion());
            stmt.setString(3, empleado.getNivelEducativo());
            stmt.setString(4, empleado.getTipo().name());
            stmt.setInt(5, empleado.getHotelId());
            stmt.setString(6, empleado.getDni());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el empleado con DNI: " + empleado.getDni(), e);
        }
    }

    @Override
    public void eliminar(String dni) {
        String sql = "DELETE FROM empleado WHERE dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar empleado con DNI: " + dni, e);
        }
    }

    private Empleado construirEmpleadoDesdeResultSet(ResultSet rs) throws SQLException {
        Empleado empleado = new Empleado();
        empleado.setId(rs.getInt("id"));
        empleado.setNombreCompleto(rs.getString("nombreCompleto"));
        empleado.setDireccion(rs.getString("direccion"));
        empleado.setDni(rs.getString("dni"));
        empleado.setNivelEducativo(rs.getString("nivelEducativo"));
        empleado.setTipo(TipoEmpleado.valueOf(rs.getString("tipo")));
        empleado.setHotelId(rs.getInt("hotelId"));
        return empleado;
    }
}
