/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteRepositorio {

    void guardar(Cliente cliente);

    Optional<Cliente> buscarPorDni(String dni);

    List<Cliente> listarTodos();

    void eliminar(String dni);

    List<Cliente> buscarPorNombre(String nombre);
}