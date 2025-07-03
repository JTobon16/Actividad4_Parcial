package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Suplemento;

import java.util.List;

public interface GestionSuplementoCasoUso {

    void registrarSuplemento(Suplemento suplemento);

    Suplemento obtenerSuplementoPorId(int id);

    List<Suplemento> listarSuplementosPorTipo(String tipo);

    List<Suplemento> listarTodos();

    void eliminarSuplemento(int id);
}
