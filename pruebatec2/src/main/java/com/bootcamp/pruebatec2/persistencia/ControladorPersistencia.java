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
 * Gestiona operaciones en la base de datos para las entidades Tramite, Turno y
 * Ciudadano. Contiene métodos para crear, leer, actualizar y buscar registros
 * en la base de datos.
 *
 * @author Alicia
 */
public class ControladorPersistencia {

    CiudadanoJpaController ciudadanoJpa = new CiudadanoJpaController();
    TurnoJpaController turnoJpa = new TurnoJpaController();
    TramiteJpaController tramiteJpa = new TramiteJpaController();

    public void createTramite(Tramite nuevoTramite) throws DatabaseException {
        tramiteJpa.createTramite(nuevoTramite);
    }

    public void createTurno(Turno nuevoTurno) throws DatabaseException {
        turnoJpa.createTurno(nuevoTurno);
    }

    public void createCiudadano(Ciudadano nuevoCiudadano) throws DatabaseException {
        ciudadanoJpa.createCiudadano(nuevoCiudadano);
    }

    public void updateTurno(Turno turno) throws DatabaseException {
        turnoJpa.updateTurno(turno);
    }

    public List<Turno> findTurnos() {
        return turnoJpa.findTurnos();
    }

    public List<Tramite> findTramites() {
        return tramiteJpa.findTramites();
    }

    public List<Ciudadano> findCiudadanos() {
        return ciudadanoJpa.findCiudadanos();
    }

    public Ciudadano readCiudadano(Integer idCiudadano) {
        return ciudadanoJpa.readCiudadano(idCiudadano);
    }

    public Ciudadano findCiudadanoByDni(String dniCiudadano) {
        return ciudadanoJpa.findCiudadanoByDni(dniCiudadano);
    }

    public List<Turno> findTurnosByFecha(LocalDate fecha) {
        return turnoJpa.findTurnosByFecha(fecha);
    }

    public List<Turno> findTurnosByFechaAndEstado(LocalDate fecha, String estado) {
        return turnoJpa.findTurnosByFechaAndEstado(fecha, estado);
    }

}
