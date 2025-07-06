package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionReservaCasoUso;
import com.udec.actividad4.aplicacion.puertos.entrada.ReservaServicio;
import com.udec.actividad4.aplicacion.puertos.salida.ReservaRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Reserva;
import com.udec.actividad4.infraestructura.dtos.ReservaDetalleDTO;

import java.util.List;

public class GestionReservaServicio implements GestionReservaCasoUso{

    private final ReservaRepositorio reservaRepositorio;

    public GestionReservaServicio(ReservaRepositorio reservaRepositorio) {
            this.reservaRepositorio = reservaRepositorio;
    }

    @Override
    public void registrarReserva(Reserva reserva) {
        reservaRepositorio.guardar(reserva);
    }

    @Override
    public Reserva obtenerReservaPorId(int id) {
        return reservaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Reserva no encontrada con ID: " + id));
    }

    @Override
    public List<Reserva> listarReservas() {
        return reservaRepositorio.listar();
    }

    @Override
    public void cancelarReserva(int id) {
        Reserva reserva = reservaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Reserva no encontrada con ID: " + id));
        reservaRepositorio.eliminar(id);
    }

    @Override
    public void actualizarReserva(Reserva reserva) {
        reservaRepositorio.actualizar(reserva);
    }
    
    // consulta 7
    public  List<ReservaDetalleDTO> obtenerReservasActivasConDetalle() {
    return reservaRepositorio.obtenerReservasActivasConDetalle();
}

}
