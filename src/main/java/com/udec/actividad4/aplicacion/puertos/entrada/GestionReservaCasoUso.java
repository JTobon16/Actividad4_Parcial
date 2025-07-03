

package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Reserva;

import java.util.List;

public interface GestionReservaCasoUso {

    void registrarReserva(Reserva reserva);

    Reserva obtenerReservaPorId(int id);

    List<Reserva> listarReservas();

    void cancelarReserva(int id);
}
