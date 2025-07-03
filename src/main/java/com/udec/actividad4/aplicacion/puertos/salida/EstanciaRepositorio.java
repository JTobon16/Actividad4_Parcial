/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

/**
 *
 * @author altoc
 */

import com.udec.actividad4.dominio.modelo.Estancia;

import java.util.List;
import java.util.Optional;

public interface EstanciaRepositorio {

    void guardar(Estancia estancia);

    Optional<Estancia> buscarPorId(int id);

    List<Estancia> listarPorHotel(int hotelId);

    List<Estancia> listarPorCliente(String clienteDni);

    void eliminar(int id);
}
