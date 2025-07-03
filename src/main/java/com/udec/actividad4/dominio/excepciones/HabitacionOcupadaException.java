/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.udec.actividad4.dominio.excepciones;

/**
 *
 * @author altoc
 */
public class HabitacionOcupadaException extends RuntimeException {
    public HabitacionOcupadaException(String mensaje) {
        super(mensaje);
    }
}