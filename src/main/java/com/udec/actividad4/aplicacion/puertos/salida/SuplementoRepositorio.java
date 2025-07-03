/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Suplemento;

import java.util.List;
import java.util.Optional;

public interface SuplementoRepositorio {

    void guardar(Suplemento suplemento);

    Optional<Suplemento> buscarPorId(int id);

    List<Suplemento> listarPorTipo(String tipo);

    List<Suplemento> listarTodos();

    void eliminar(int id);
}
