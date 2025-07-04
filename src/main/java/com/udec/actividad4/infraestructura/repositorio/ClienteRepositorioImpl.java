package com.udec.actividad4.infraestructura.repositorio;

import com.udec.actividad4.aplicacion.puertos.salida.ClienteRepositorio;
import com.udec.actividad4.dominio.modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClienteRepositorioImpl implements ClienteRepositorio {

    private final Connection conexion;

    public ClienteRepositorioImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void guardar(Cliente cliente) {
        String sql = "INSERT INTO cliente (dni, nombreCompleto, direccion, telefono, movil) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getDni());
            stmt.setString(2, cliente.getNombreCompleto());
            stmt.setString(3, cliente.getDireccion());
            stmt.setString(4, cliente.getTelefonoFijo());
            stmt.setString(5, cliente.getTelefonoMovil());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace(); // En producción, usar logger
        }
    }

    @Override
    public Optional<Cliente> buscarPorDni(String dni) {
        String sql = "SELECT * FROM cliente WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, dni);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombreCompleto"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("movil")
                );
                return Optional.of(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public List<Cliente> listarTodos() {
        List<Cliente> clientes = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Statement stmt = conexion.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Cliente cliente = new Cliente(
                        rs.getString("dni"),
                        rs.getString("nombreCompleto"),
                        rs.getString("direccion"),
                        rs.getString("telefono"),
                        rs.getString("movil")
                );
                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return clientes;
    }

    @Override
    public void eliminar(Cliente cliente) {
        String sql = "DELETE FROM cliente WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getDni());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void actualizar(Cliente cliente) {
        String sql = "UPDATE cliente SET nombreCompleto = ?, direccion = ?, telefono = ?, movil = ? WHERE dni = ?";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, cliente.getNombreCompleto());
            stmt.setString(2, cliente.getDireccion());
            stmt.setString(3, cliente.getTelefonoFijo());
            stmt.setString(4, cliente.getTelefonoMovil());
            stmt.setString(5, cliente.getDni());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
