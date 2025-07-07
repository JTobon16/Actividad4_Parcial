package com.udec.actividad4.presentacion.consola;

import com.udec.actividad4.aplicacion.puertos.entrada.GestionSuplementoCasoUso;
import com.udec.actividad4.aplicacion.puertos.salida.SuplementoRepositorio;
import com.udec.actividad4.aplicacion.servicios.GestionActividadServicio;
import com.udec.actividad4.aplicacion.servicios.GestionEstanciaServicio;
import com.udec.actividad4.aplicacion.servicios.GestionHotelServicio;
import com.udec.actividad4.aplicacion.servicios.GestionReservaServicio;
import com.udec.actividad4.aplicacion.servicios.GestionSuplementoServicio;
import com.udec.actividad4.infraestructura.config.ConexionBD;
import com.udec.actividad4.infraestructura.controlador.ActividadControlador;
import com.udec.actividad4.infraestructura.controlador.EstanciaControlador;
import com.udec.actividad4.infraestructura.controlador.HotelControlador;
import com.udec.actividad4.infraestructura.controlador.ReservaControlador;
import com.udec.actividad4.infraestructura.controlador.SuplementoControlador;
import com.udec.actividad4.infraestructura.repositorio.ActividadRepositorioImpl;
import com.udec.actividad4.infraestructura.repositorio.EstanciaRepositorioImpl;
import com.udec.actividad4.infraestructura.repositorio.HotelRepositorioImpl;
import com.udec.actividad4.infraestructura.repositorio.ReservaRepositorioImpl;
import com.udec.actividad4.infraestructura.repositorio.SuplementoRepositorioImpl;

import java.sql.Connection;
import java.time.LocalDate;

public class ConsolaApp {

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();

        
        
        
        if (conexion != null) {
            HotelRepositorioImpl hotelRepo = new HotelRepositorioImpl(conexion);
            GestionHotelServicio hotelServicio = new GestionHotelServicio(hotelRepo);
            HotelControlador controlador = new HotelControlador(hotelServicio);

            // aqui estamos generando las consultas
            controlador.mostrarHoteles();// consulta 1
            controlador.mostrarContactosYDirectores(); // consulta 2
            controlador.mostrarCantidadEmpleadosPorTipoYHotel(); // consulta 3
            controlador.mostrarResumenEmpleados();// consulta 4
            controlador.mostrarHabitacionesDisponiblesPorTipo(1); // consulta 5  
            controlador.mostrarOcupacionPorPeriodo(1, LocalDate.of(2025, 7, 1), LocalDate.of(2025, 7, 10)); // consulta 6
            
            
            ReservaRepositorioImpl reservaRepo = new ReservaRepositorioImpl(conexion);
            GestionReservaServicio reservaServicio = new GestionReservaServicio(reservaRepo);
            ReservaControlador reservaControlador = new ReservaControlador(reservaServicio);

    
            reservaControlador.mostrarReservasActivasConHabitaciones();//consulta 7
            reservaControlador.mostrarHistorialReservasCliente("CC1001001"); // consulta 8

            SuplementoRepositorio suplementoRepositorio = new SuplementoRepositorioImpl(conexion);
            GestionSuplementoCasoUso gestionSuplemento = new GestionSuplementoServicio(suplementoRepositorio);
            SuplementoControlador suplementoControlador = new SuplementoControlador(gestionSuplemento);

            
            suplementoControlador.mostrarSuplementosTemporada(LocalDate.of(2025, 6, 01));//consulta 9
            
            
            
            EstanciaRepositorioImpl estanciaRepo = new EstanciaRepositorioImpl(conexion);
            GestionEstanciaServicio gestionEstanciaServicio = new GestionEstanciaServicio(estanciaRepo);
            EstanciaControlador estanciaControlador = new EstanciaControlador(gestionEstanciaServicio);
            
            int hotelId = 5;
            estanciaControlador.mostrarEstanciasActivasPorHotel(hotelId);// consulta 10
            
            ActividadRepositorioImpl actividadRepo = new ActividadRepositorioImpl(conexion);
            GestionActividadServicio actividadServicio = new GestionActividadServicio(actividadRepo);
            ActividadControlador actividadControlador = new ActividadControlador(actividadServicio);
            
            actividadControlador.mostrarActividadesPorHotel(1); // Consulta 11
            actividadControlador.mostrarActividadesContratadasPorCliente("CC1001001"); // Consulta 12
            actividadControlador.mostrarHistorialActividadesPagasPorHotel(1); // Consulta 13
            actividadControlador.mostrarIngresosPorActividadesPagas(); // Consulta 14





        } else {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
        }
    }
}



