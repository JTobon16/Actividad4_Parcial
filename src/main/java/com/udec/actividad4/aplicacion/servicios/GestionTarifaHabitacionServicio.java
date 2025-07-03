package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.TarifaHabitacionServicio;
import com.udec.actividad4.aplicacion.puertos.salida.TarifaHabitacionRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.TarifaHabitacion;

import java.util.List;

public class GestionTarifaHabitacionServicio implements TarifaHabitacionServicio {

    private final TarifaHabitacionRepositorio tarifaRepositorio;

    public GestionTarifaHabitacionServicio(TarifaHabitacionRepositorio tarifaRepositorio) {
        this.tarifaRepositorio = tarifaRepositorio;
    }

    @Override
    public void registrarTarifa(TarifaHabitacion tarifa) {
        tarifaRepositorio.guardar(tarifa);
    }

    @Override
    public TarifaHabitacion buscarTarifaPorId(int id) {
        return tarifaRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Tarifa no encontrada con ID: " + id));
    }

    @Override
    public List<TarifaHabitacion> listarTarifasPorHotel(int hotelId) {
        return tarifaRepositorio.listarPorHotel(hotelId);
    }

    @Override
    public void actualizarTarifa(TarifaHabitacion tarifa) {
        tarifaRepositorio.actualizar(tarifa);
    }

    @Override
    public void eliminarTarifa(int id) {
        tarifaRepositorio.eliminar(id);
    }
}
