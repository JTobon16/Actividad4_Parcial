package com.udec.actividad4.presentacion.consola;

import com.udec.actividad4.aplicacion.servicios.GestionHotelServicio;
import com.udec.actividad4.infraestructura.config.ConexionBD;
import com.udec.actividad4.infraestructura.controlador.HotelControlador;
import com.udec.actividad4.infraestructura.repositorio.HotelRepositorioImpl;

import java.sql.Connection;

public class ConsolaApp {

    public static void main(String[] args) {
        Connection conexion = ConexionBD.obtenerConexion();

        
        
        
        if (conexion != null) {
            HotelRepositorioImpl hotelRepo = new HotelRepositorioImpl(conexion);
            GestionHotelServicio hotelServicio = new GestionHotelServicio(hotelRepo);
            HotelControlador controlador = new HotelControlador(hotelServicio);

            // aqui estamos generando las consultas
            controlador.mostrarHoteles();
            controlador.mostrarContactosYDirectores(); // consulta 2
            controlador.mostrarCantidadEmpleadosPorTipoYHotel(); // consulta 3
        } else {
            System.err.println("No se pudo establecer la conexión a la base de datos.");
        }
    }
}



