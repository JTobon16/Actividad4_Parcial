/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Empleado;

import java.util.List;
import java.util.Optional;

public interface EmpleadoRepositorio {

    void guardar(Empleado empleado);

    Optional<Empleado> buscarPorId(int id);

    List<Empleado> listarPorHotel(int hotelId);

    List<Empleado> listarTodos();

    void eliminar(int id);
}