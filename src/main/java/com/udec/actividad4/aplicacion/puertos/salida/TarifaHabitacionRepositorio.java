package com.udec.actividad4.aplicacion.puertos.salida;

import com.udec.actividad4.dominio.modelo.TarifaHabitacion;
import java.util.List;
import java.util.Optional;

public interface TarifaHabitacionRepositorio {
    void guardar(TarifaHabitacion tarifa);
    Optional<TarifaHabitacion> buscarPorId(int id);
    List<TarifaHabitacion> listarPorHotel(int hotelId);
    void actualizar(TarifaHabitacion tarifa);
    void eliminar(int id);
}
