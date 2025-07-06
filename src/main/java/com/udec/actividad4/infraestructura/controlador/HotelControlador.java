/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionHotelCasoUso;
import com.udec.actividad4.dominio.modelo.Hotel;

import java.util.List;

public class HotelControlador {

    private final GestionHotelCasoUso gestionHotelCasoUso;

    public HotelControlador(GestionHotelCasoUso gestionHotelCasoUso) {
        this.gestionHotelCasoUso = gestionHotelCasoUso;
    }

    // aqui hicimos el metodo que nos mostrara los hoteles 
    public void mostrarHoteles() {
        List<Hotel> hoteles = gestionHotelCasoUso.listarHoteles();
        if (hoteles.isEmpty()) {
            System.out.println("No hay hoteles registrados.");
        } else {
            System.out.println("Listado de Hoteles:");
            for (Hotel hotel : hoteles) {
                System.out.println("Nombre: " + hotel.getNombre());
                System.out.println("Categoria: " + hotel.getCategoriaEstrellas() + " estrellas");
                System.out.println("Direccion: " + hotel.getDireccion());
                System.out.println("----------");
            }
        }
    }
            public void mostrarContactosYDirectores() {
            List<String> contactos = gestionHotelCasoUso.obtenerContactosYDirectores();
                System.out.println("\n Contactos y Directores de los Hoteles:");
                         contactos.forEach(System.out::println);
}
}

