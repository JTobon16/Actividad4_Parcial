/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;


import com.udec.actividad4.dominio.modelo.Hotel;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface HotelRepositorio {

    void guardar(Hotel hotel);

    Optional<Hotel> buscarPorId(int id);

    List<Hotel> listarTodos();

    void eliminar(int id);
    
    void actualizar(Hotel hotel);

    List<Hotel> buscarPorNombreOCategoria(String nombre, int estrellas);
    
    List<String> obtenerContactosYDirectores(); // Consulta 2
    
    


}