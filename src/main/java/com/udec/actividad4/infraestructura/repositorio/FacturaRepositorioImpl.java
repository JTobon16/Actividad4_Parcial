package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.FacturaRepositorio;
import com.udec.actividad4.dominio.modelo.Factura;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class FacturaRepositorioImpl implements FacturaRepositorio {

    private final Connection conexion;

    public FacturaRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Factura factura) {
        String sql = "INSERT INTO factura (id, reservaId, fechaEmision, diasEstancia, totalHabitaciones, " +
                     "totalSuplementos, totalActividades, senalDescontada, totalFinal) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, factura.getId());
            stmt.setInt(2, factura.getReservaId());
            stmt.setDate(3, Date.valueOf(factura.getFechaEmision()));
            stmt.setInt(4, factura.getDiasEstancia());
            stmt.setDouble(5, factura.getTotalHabitaciones());
            stmt.setDouble(6, factura.getTotalSuplementos());
            stmt.setDouble(7, factura.getTotalActividades());
            stmt.setDouble(8, factura.getSenalDescontada());
            stmt.setDouble(9, factura.getTotalFinal());

            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar la factura", e);
        }
    }

    @Override
    public Optional<Factura> buscarPorId(int id) {
        String sql = "SELECT * FROM factura WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Factura factura = new Factura();
                    factura.setId(rs.getInt("id"));
                    factura.setReservaId(rs.getInt("reservaId"));
                    factura.setFechaEmision(rs.getDate("fechaEmision").toLocalDate());
                    factura.setDiasEstancia(rs.getInt("diasEstancia"));
                    factura.setTotalHabitaciones(rs.getDouble("totalHabitaciones"));
                    factura.setTotalSuplementos(rs.getDouble("totalSuplementos"));
                    factura.setTotalActividades(rs.getDouble("totalActividades"));
                    factura.setSenalDescontada(rs.getDouble("senalDescontada"));
                    factura.setTotalFinal(rs.getDouble("totalFinal"));

                    return Optional.of(factura);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar la factura con ID: " + id, e);
        }

        return Optional.empty();
    }

    @Override
    public List<Factura> listarTodos() {
        List<Factura> facturas = new ArrayList<>();
        String sql = "SELECT * FROM factura";

        try (PreparedStatement stmt = conexion.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Factura factura = new Factura();
                factura.setId(rs.getInt("id"));
                factura.setReservaId(rs.getInt("reservaId"));
                factura.setFechaEmision(rs.getDate("fechaEmision").toLocalDate());
                factura.setDiasEstancia(rs.getInt("diasEstancia"));
                factura.setTotalHabitaciones(rs.getDouble("totalHabitaciones"));
                factura.setTotalSuplementos(rs.getDouble("totalSuplementos"));
                factura.setTotalActividades(rs.getDouble("totalActividades"));
                factura.setSenalDescontada(rs.getDouble("senalDescontada"));
                factura.setTotalFinal(rs.getDouble("totalFinal"));

                facturas.add(factura);
            }

        } catch (SQLException e) {
            throw new RuntimeException("Error al listar las facturas", e);
        }

        return facturas;
    }

    @Override
    public void eliminar(int id) {
        String sql = "DELETE FROM factura WHERE id = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar la factura con ID: " + id, e);
        }
    }
}
