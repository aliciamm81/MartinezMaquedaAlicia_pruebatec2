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
        tramiteJpa.agregarTramite(nuevoTramite);
    }

    public void agregarTurno(Turno nuevoTurno) throws DatabaseException {
        turnoJpa.agregarTurno(nuevoTurno);
    }

    public void agregarCiudadano(Ciudadano nuevoCiudadano) throws DatabaseException {
        ciudadanoJpa.agregarCiudadano(nuevoCiudadano);
    }

    public void modificarTurno(Turno turno) throws DatabaseException {
        turnoJpa.modificarTurno(turno);
    }

    public List<Turno> obtenerTurno() {
        return turnoJpa.obtenerTurno();
    }

    public List<Tramite> obtenerTramites() {
        return tramiteJpa.obtenerTramites();
    }

    public List<Ciudadano> obtenerCiudadanos() {
        return ciudadanoJpa.obtenerCiudadanos();
    }

    public Ciudadano obtenerCiudadanoPorId(Integer idCiudadano) {
        return ciudadanoJpa.obtenerCiudadanoPorId(idCiudadano);
    }

    public Ciudadano obtenerCiudadanoPorDni(String dniCiudadano) {
        return ciudadanoJpa.obtenerCiudadanoPorDni(dniCiudadano);
    }

    public List<Turno> obtenerTurnoPorFecha(LocalDate fecha) {
        return turnoJpa.obtenerTurnoPorFecha(fecha);
    }

    public List<Turno> obtenerTurnoPorEstadoYFecha(LocalDate fecha, String estado) {
        return turnoJpa.obtenerTurnoPorEstadoYFecha(fecha, estado);
    }

}
