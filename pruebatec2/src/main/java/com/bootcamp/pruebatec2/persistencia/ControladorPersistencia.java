/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.persistencia;

import com.bootcamp.pruebatec2.logica.Ciudadano;
import com.bootcamp.pruebatec2.logica.Tramite;
import com.bootcamp.pruebatec2.logica.Turno;
import java.time.LocalDate;
import java.util.List;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Alicia
 */
public class ControladorPersistencia {

    CiudadanoJpaControlador ciudadanoJpa = new CiudadanoJpaControlador();
    TurnoJpaControlador turnoJpa = new TurnoJpaControlador();
    TramiteJpaControlador tramiteJpa = new TramiteJpaControlador();

    public void agregarTramite(Tramite nuevoTramite) throws DatabaseException {
        tramiteJpa.agregar(nuevoTramite);
    }

    public void agregarTurno(Turno nuevoTurno) throws DatabaseException {
        turnoJpa.agregar(nuevoTurno);
    }

    public void agregarCiudadano(Ciudadano nuevoCiudadano) throws DatabaseException {
        ciudadanoJpa.agregar(nuevoCiudadano);
    }

    public List<Turno> obtenerTurno() {
        return turnoJpa.obtener();
    }

    public Tramite obtenerUltimoTramite() {
        return tramiteJpa.obtenerUltimo();
    }

    public Ciudadano obtenerUltimoCiudadano() {
        return ciudadanoJpa.obtenerUltimo();
    }

    public Ciudadano obtenerCiudadanoPorId(Integer idCiudadano) {
        return ciudadanoJpa.obtenerPorId(idCiudadano);
    }

    public Ciudadano obtenerCiudadanoPorDni(String dniCiudadano) {
        return ciudadanoJpa.obtenerPorDni(dniCiudadano);
    }

    public List<Turno> obtenerTurnoPorFecha(LocalDate fecha) {
        return turnoJpa.obtenerPorFecha(fecha);
    }

    public List<Turno> obtenerTurnoPorEstadoYFecha(LocalDate fecha, String estado) {
        return turnoJpa.obtenerPorEstadoYFecha(fecha, estado);
    }

}
