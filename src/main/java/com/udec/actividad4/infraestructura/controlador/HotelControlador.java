/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionHotelCasoUso;
import com.udec.actividad4.aplicacion.servicios.GestionHabitacionServicio;
import com.udec.actividad4.dominio.modelo.Habitacion;
import com.udec.actividad4.dominio.modelo.Hotel;
import java.time.LocalDate;

import java.util.List;
import java.util.Map;

public class HotelControlador {

    private final GestionHotelCasoUso gestionHotelCasoUso;
     private final GestionHabitacionServicio gestionHabitacionServicio;

    public HotelControlador(GestionHotelCasoUso gestionHotelCasoUso, GestionHabitacionServicio gestionHabitacionServicio) {
    this.gestionHotelCasoUso = gestionHotelCasoUso;
    this.gestionHabitacionServicio = gestionHabitacionServicio;
}

    // aqui hicimos el metodo que nos mostrara los hoteles consulta 1
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
       //Consulta 2
            public void mostrarContactosYDirectores() {
            List<String> contactos = gestionHotelCasoUso.obtenerContactosYDirectores();
                System.out.println("\n Contactos y Directores de los Hoteles:");
                         contactos.forEach(System.out::println);
}
      //Consulta 3     
            public void mostrarCantidadEmpleadosPorTipoYHotel() {
             Map<String, Map<String, Integer>> datos = gestionHotelCasoUso.contarEmpleadosPorTipoPorHotel();
             System.out.println("\nCantidad de empleados por tipo en cada hotel:");
                            for (String hotel : datos.keySet()) {
                   System.out.println("Hotel: " + hotel);
                   Map<String, Integer> tipos = datos.get(hotel);
                              for (String tipo : tipos.keySet()) {
                    System.out.println("  Tipo: " + tipo + ", Total: " + tipos.get(tipo));
                    }
                 System.out.println("-----------");
                   }
}
      //Consulta 4     
            public void mostrarResumenEmpleados() {
                List<String> resumen = gestionHotelCasoUso.obtenerResumenEmpleados();

            System.out.println("\nResumen de empleados:");
             resumen.forEach(System.out::println);
}
  
       //consulta 5         
           public void mostrarHabitacionesDisponiblesPorTipo(int hotelId) {
        Map<String, Integer> disponibles = gestionHotelCasoUso.contarHabitacionesDisponiblesPorTipo(hotelId);

        if (disponibles.isEmpty()) {
        System.out.println("No hay habitaciones disponibles para el hotel con ID: " + hotelId);
            } else {
        System.out.println("Habitaciones disponibles por tipo para el hotel ID " + hotelId + ":");
        disponibles.forEach((tipo, cantidad) -> 
            System.out.println("Tipo: " + tipo + " - Disponibles: " + cantidad));
    }
}

           
           public void mostrarOcupacionPorPeriodo(int hotelId, LocalDate inicio, LocalDate fin) {
    List<Habitacion> ocupadas = gestionHotelCasoUso.obtenerHabitacionesOcupadasEnPeriodo(hotelId, inicio, fin);

    System.out.println("Habitaciones ocupadas en el hotel ID " + hotelId + " del " + inicio + " al " + fin + ":");
    if (ocupadas.isEmpty()) {
        System.out.println("No hay habitaciones ocupadas en ese periodo.");
    } else {
        for (Habitacion h : ocupadas) {
            System.out.println("Habitacion ID: " + h.getId() + ", Tipo: " + h.getTipo());
        }
    }
}

        //consulta 16   
        public void mostrarTarifasHabitaciones() {
    System.out.println("\n--- Tarifas de habitaciones por hotel y tipo ---");
    List<String> tarifas = gestionHabitacionServicio.consultarTarifasHabitaciones();
    tarifas.forEach(System.out::println);
}




}

