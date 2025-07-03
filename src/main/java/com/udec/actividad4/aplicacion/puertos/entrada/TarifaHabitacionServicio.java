package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.TarifaHabitacion;
import java.util.List;

public interface TarifaHabitacionServicio {
    void registrarTarifa(TarifaHabitacion tarifa);
    TarifaHabitacion buscarTarifaPorId(int id);
    List<TarifaHabitacion> listarTarifasPorHotel(int hotelId);
    void actualizarTarifa(TarifaHabitacion tarifa);
    void eliminarTarifa(int id);
}
