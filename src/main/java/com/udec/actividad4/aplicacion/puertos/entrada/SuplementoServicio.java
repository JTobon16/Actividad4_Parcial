package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Suplemento;
import java.util.List;

public interface SuplementoServicio {
    void registrarSuplemento(Suplemento suplemento);
    Suplemento buscarSuplementoPorId(int id);
    List<Suplemento> listarSuplementos();
    void actualizarSuplemento(Suplemento suplemento);
    void eliminarSuplemento(int id);
}
