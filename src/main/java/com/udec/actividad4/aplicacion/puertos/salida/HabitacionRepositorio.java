/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Habitacion;

import java.util.List;
import java.util.Optional;

public interface HabitacionRepositorio {

   void guardar(Habitacion habitacion);
   
    Optional<Habitacion> buscarPorId(int id);
    
    List<Habitacion> listarPorHotel(int hotelId);
    
    void actualizar(Habitacion habitacion);
    
void eliminar(int id);

List<String> obtenerTarifasPorHotelYTipo();

List<String> obtenerPreciosHabitacionesConSuplementos();


}