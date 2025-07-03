package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Cliente;

import java.util.List;

public interface GestionClienteCasoUso {

    void registrarCliente(Cliente cliente);

    Cliente obtenerClientePorDni(String dni);

    List<Cliente> listarClientes();

    void eliminarCliente(String dni);
}
