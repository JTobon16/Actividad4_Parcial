/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;
import java.util.Optional;

public interface ActividadRepositorio {

    void guardar(Actividad actividad);

    Optional<Actividad> buscarPorId(int id);

    List<Actividad> listarPorHotel(int hotelId);

    List<Actividad> listarPorEmpleado(int empleadoId);

    void eliminar(int id);
}