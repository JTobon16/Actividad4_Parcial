package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.TarifaHabitacion;

import java.util.List;

public interface GestionTarifaHabitacionCasoUso {

    void registrarTarifa(TarifaHabitacion tarifa);

    TarifaHabitacion obtenerTarifaPorId(int id);

    List<TarifaHabitacion> listarTarifasPorHotel(int hotelId);

    List<TarifaHabitacion> listarPorTipoHabitacion(String tipo);

    void eliminarTarifa(int id);
}
