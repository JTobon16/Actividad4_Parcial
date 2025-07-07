package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Suplemento;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

public interface GestionSuplementoCasoUso {

    void registrarSuplemento(Suplemento suplemento);

    Suplemento obtenerSuplementoPorId(int id);

    List<Suplemento> listarSuplementosPorTipo(String tipo);

    List<Suplemento> listarTodos();

    void eliminarSuplemento(int id);
    
    List<Suplemento> obtenerSuplementosTemporadaPorFecha(LocalDate fecha); //consulta 9
   
    void actualizarSuplemento(Suplemento suplemento);

    List<Suplemento> listarSuplementosPorCaracteristicas(); //consulta 15
    
    Map<Integer, List<String>> obtenerSuplementosTemporadaPorHotel(); // consulta 17


}
