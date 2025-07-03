package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Hotel;
import java.util.List;

public interface HotelServicio {
    void registrarHotel(Hotel hotel);
    Hotel buscarHotelPorId(int id);
    List<Hotel> listarHoteles();
    void eliminarHotel(int id);
    void actualizarHotel(Hotel hotel);
}
