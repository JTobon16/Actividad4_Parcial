/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.aplicacion.puertos.salida;

/**
 *
 * @author altoc
 */
import com.udec.actividad4.dominio.modelo.Reserva;
import com.udec.actividad4.infraestructura.dtos.ReservaDetalleDTO;

import java.util.List;
import java.util.Optional;

public interface ReservaRepositorio {

    void guardar(Reserva reserva);

    Optional<Reserva> buscarPorId(int id);

    List<Reserva> listar();

    void eliminar(int id);
    
    void actualizar(Reserva reserva);

    public List<ReservaDetalleDTO> obtenerReservasActivasConDetalle();// consulta 7
    
    List<Reserva> obtenerHistorialReservasCliente(String dniCliente);// consulta 8

    public List<String> obtenerReservasConDetalleCliente();


}