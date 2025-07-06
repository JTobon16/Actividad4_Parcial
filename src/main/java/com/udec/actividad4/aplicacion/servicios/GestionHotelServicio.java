package com.udec.actividad4.aplicacion.servicios;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionHotelCasoUso;

import com.udec.actividad4.aplicacion.puertos.salida.HotelRepositorio;
import com.udec.actividad4.dominio.excepciones.EntidadNoEncontradaException;
import com.udec.actividad4.dominio.modelo.Hotel;

import java.util.List;

public class GestionHotelServicio implements GestionHotelCasoUso {

    private final HotelRepositorio hotelRepositorio;

    public GestionHotelServicio(HotelRepositorio hotelRepositorio) {
        this.hotelRepositorio = hotelRepositorio;
    }

    @Override
    public void registrarHotel(Hotel hotel) {
        hotelRepositorio.guardar(hotel);
    }

    @Override
    public Hotel buscarHotelPorId(int id) {
        return hotelRepositorio.buscarPorId(id)
                .orElseThrow(() -> new EntidadNoEncontradaException("Hotel no encontrado con ID: " + id));
    }

    @Override
    public List<Hotel> listarHoteles() {
        return hotelRepositorio.listarTodos();
    }

    @Override
    public void eliminarHotel(int id) {
        Hotel hotel = buscarHotelPorId(id);
        hotelRepositorio.eliminar(id);
    }

    @Override
    public void actualizarHotel(Hotel hotel) {
        hotelRepositorio.actualizar(hotel);
    }
    
    //Consulta 2
    @Override
    public List<String> obtenerContactosYDirectores() {
    return hotelRepositorio.obtenerContactosYDirectores();
}

}
