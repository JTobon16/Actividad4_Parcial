

package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Hotel;
import java.util.List;
import java.util.Map;

public interface GestionHotelCasoUso {
    void registrarHotel(Hotel hotel);
    List<Hotel> listarHoteles();
    Hotel buscarHotelPorId(int id); 
    void eliminarHotel(int id);
    void actualizarHotel(Hotel hotel); 
    List<String> obtenerContactosYDirectores(); // Consulta 2
    Map<String, Map<String, Integer>> contarEmpleadosPorTipoPorHotel(); //Consulta 3
    List<String> obtenerResumenEmpleados();// Consulta 4
    Map<String, Integer> contarHabitacionesDisponiblesPorTipo(int hotelId); // consulta 5



}
