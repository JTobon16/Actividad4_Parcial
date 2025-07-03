/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.TarifaHabitacion;

import java.util.List;
import java.util.Optional;

public interface TarifaHabitacionRepositorio {

    void guardar(TarifaHabitacion tarifa);

    Optional<TarifaHabitacion> buscarPorId(int id);

    List<TarifaHabitacion> listarPorHotel(int hotelId);

    List<TarifaHabitacion> listarPorTipoHabitacion(String tipo);

    void eliminar(int id);
}