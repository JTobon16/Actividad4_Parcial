

package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Estancia;

import java.util.List;

public interface GestionEstanciaCasoUso {

    void registrarEstancia(Estancia estancia);

    Estancia obtenerEstanciaPorId(int id);

    List<Estancia> listarEstanciasPorHotel(int hotelId);

    List<Estancia> listarEstanciasPorCliente(String clienteDni);

    void finalizarEstancia(int id);
}
