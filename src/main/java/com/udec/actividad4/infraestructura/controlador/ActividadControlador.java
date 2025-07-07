package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionActividadCasoUso;
import com.udec.actividad4.dominio.enums.TipoActividad;
import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;
import java.util.Map;

public class ActividadControlador {

    private final GestionActividadCasoUso gestionActividad;

    public ActividadControlador(GestionActividadCasoUso gestionActividad) {
        this.gestionActividad = gestionActividad;
    }

    public void mostrarActividadesPorHotel(int hotelId) {
        List<Actividad> actividades = gestionActividad.listarActividadesPorHotel(hotelId);
        System.out.println("\nActividades disponibles en el hotel ID " + hotelId + ":");

        for (Actividad act : actividades) {
            String tipo = (act.getTipoActividad() == TipoActividad.GRATUITA) ? "GRATUITA" : "DE PAGO";
            System.out.println("Nombre: " + act.getNombre() +
                               " | Dia: " + act.getDiaSemana() +
                               " | Hora: " + act.getHora() +
                               " | Tipo: " + tipo +
                               (tipo.equals("DE PAGO") ? " | Precio: $" + act.getPrecioPorPersona() : ""));
        }
    }
    
    // consulta 12
    public void mostrarActividadesContratadasPorCliente(String clienteDni) {
    List<Actividad> actividades = gestionActividad.obtenerActividadesContratadasPorCliente(clienteDni);
    System.out.println("\nActividades contratadas por el cliente " + clienteDni + ":");

    for (Actividad act : actividades) {
        System.out.println("Nombre: " + act.getNombre() +
                           " | Dia: " + act.getDiaSemana() +
                           " | Hora: " + act.getHora() +
                           " | Tipo: " + act.getTipoActividad() +
                           (act.getTipoActividad().name().equals("PAGA") ? " | Precio: $" + act.getPrecioPorPersona() : ""));
    }
}
    
    //consulta 13
    public void mostrarHistorialActividadesPagasPorHotel(int hotelId) {
    List<Actividad> actividades = gestionActividad.obtenerHistorialActividadesPagasPorHotel(hotelId);
    System.out.println("\nHistorial de actividades de pago en el hotel ID " + hotelId + ":");

    for (Actividad act : actividades) {
        System.out.println("Nombre: " + act.getNombre() +
                           " | Dia: " + act.getDiaSemana() +
                           " | Hora: " + act.getHora() +
                           " | Precio por persona: $" + act.getPrecioPorPersona());
    }
}

     //consulta 14
    public void mostrarIngresosPorActividadesPagas() {
    Map<Integer, Double> ingresos = gestionActividad.obtenerIngresosPorActividadesPagasPorHotel();
    System.out.println("\nReporte de ingresos por actividades de pago por hotel:");

    ingresos.forEach((hotelId, total) -> 
        System.out.println("Hotel ID: " + hotelId + " | Ingresos totales: $" + total));
}

    // consulta19
    public void mostrarHotelesConMasActividades() {
    Map<Integer, Integer> datos = gestionActividad.obtenerHotelesConMasActividades();

    System.out.println("\nHoteles con mayor numero de actividades de entretenimiento:");
    if (datos.isEmpty()) {
        System.out.println("No hay actividades registradas.");
    } else {
        datos.forEach((hotelId, cantidad) -> 
            System.out.println("Hotel ID: " + hotelId + " - Actividades: " + cantidad));
    }
}

    
     // consulta 19
    public void mostrarActividadesMasContratadasPorHotel() {
    System.out.println("\n--- Actividades mas contratadas por los clientes en cada hotel ---");
    List<Actividad> actividades = gestionActividad.listarActividadesMasContratadasPorHotel();

    for (Actividad act : actividades) {
        System.out.println("Hotel ID: " + act.getHotelId() +
                           " | Actividad: " + act.getNombre() +
                           " | Dia: " + act.getDiaSemana() +
                           " | Hora: " + act.getHora() +
                           " | Tipo: " + act.getTipoActividad() +
                           " | Precio: $" + act.getPrecioPorPersona());
    }
}

}
