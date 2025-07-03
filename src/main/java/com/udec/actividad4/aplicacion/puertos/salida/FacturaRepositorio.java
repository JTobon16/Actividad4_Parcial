/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.Factura;

import java.util.List;
import java.util.Optional;

public interface FacturaRepositorio {

    void guardar(Factura factura);

    Optional<Factura> buscarPorId(int id);

    List<Factura> listarPorCliente(String clienteDni);

    List<Factura> listarTodas();

    void eliminar(int id);
}