package com.udec.actividad4.infraestructura.controlador;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionActividadCasoUso;
import com.udec.actividad4.dominio.enums.TipoActividad;
import com.udec.actividad4.dominio.modelo.Actividad;

import java.util.List;

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

}
