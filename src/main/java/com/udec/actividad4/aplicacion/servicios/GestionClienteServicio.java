package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.ClienteServicio;
import com.udec.actividad4.aplicacion.puertos.salida.ClienteRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Cliente;

import java.util.List;

public class GestionClienteServicio implements ClienteServicio {

    private final ClienteRepositorio clienteRepositorio;

    public GestionClienteServicio(ClienteRepositorio clienteRepositorio) {
        this.clienteRepositorio = clienteRepositorio;
    }

    @Override
    public void registrarCliente(Cliente cliente) {
        clienteRepositorio.guardar(cliente);
    }

    @Override
    public Cliente buscarClientePorDni(String dni) {
        return clienteRepositorio.buscarPorDni(dni)
                .orElseThrow(() -> new EntidadNoEncontradaException("Cliente no encontrado con DNI: " + dni));
    }

    @Override
    public List<Cliente> listarClientes() {
        return clienteRepositorio.listarTodos();
    }

    @Override
    public void eliminarCliente(String dni) {
        Cliente cliente = buscarClientePorDni(dni);
        clienteRepositorio.eliminar(cliente);
    }

    @Override
    public void actualizarCliente(Cliente cliente) {
        clienteRepositorio.actualizar(cliente);
    }
}
