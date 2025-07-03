package com.udec.actividad4.aplicacion.puertos.entrada;

import com.udec.actividad4.dominio.modelo.Estancia;
import java.util.List;

public interface EstanciaServicio {
    void registrarEstancia(Estancia estancia);
    Estancia buscarEstanciaPorId(int id);
    List<Estancia> listarEstancias();
    void finalizarEstancia(int id);
}
