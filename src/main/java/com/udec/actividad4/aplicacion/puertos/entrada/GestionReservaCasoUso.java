

package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Reserva;
import com.udec.actividad4.infraestructura.dtos.ReservaDetalleDTO;

import java.util.List;

public interface GestionReservaCasoUso {

    void registrarReserva(Reserva reserva);

    Reserva obtenerReservaPorId(int id);

    List<Reserva> listarReservas();

    void cancelarReserva(int id);
    
    void actualizarReserva(Reserva reserva);
    
    public List<ReservaDetalleDTO> obtenerReservasActivasConDetalle();// consulta 7
    
    List<Reserva> obtenerHistorialReservasCliente(String dniCliente); // consulta 8
    
    List<String> obtenerReservasConDetalleCliente();
    
    List<String> obtenerClientesConReservasPagasYEstanciasPendientes();




}
