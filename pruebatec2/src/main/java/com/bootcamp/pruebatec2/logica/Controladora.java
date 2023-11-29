/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.logica;

import com.bootcamp.pruebatec2.persistencia.ControladorPersistencia;
import excepciones.CiudadanoException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Alicia
 */
public class Controladora {

    ControladorPersistencia controladora = new ControladorPersistencia();

    public void agregarTramite(Tramite nuevoTramite) throws DatabaseException {
        controladora.agregarTramite(nuevoTramite);
    }

    public void agregarTurno(Turno nuevoTurno) throws DatabaseException {
        controladora.agregarTurno(nuevoTurno);
    }

    public void agregarCiudadano(Ciudadano nuevoCiudadano) throws DatabaseException {
        controladora.agregarCiudadano(nuevoCiudadano);
    }

    public void modificarTurno(Turno tuno) throws DatabaseException {
        controladora.modificarTurno(tuno);
    }

    public List<Turno> obtenerTurno() {
        return controladora.obtenerTurno();
    }

    public Tramite obtenerUltimoTramiteAgregado() {
        List<Tramite> listaTramites = controladora.obtenerTramites();
        Tramite tramite = listaTramites.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Tramite::getId)))
                .findFirst()
                .orElse(null);
        return tramite;

    }

    public Ciudadano obtenerUltimoCiudadanoAgregado() {
        List<Ciudadano> listaCiudadanos = controladora.obtenerCiudadanos();
        Ciudadano ciudadano = listaCiudadanos.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Ciudadano::getId)))
                .findFirst()
                .orElse(null);
        return ciudadano;

    }

    public Ciudadano obtenerCiudadanoPorId(Integer idCiudadano) {
        return controladora.obtenerCiudadanoPorId(idCiudadano);
    }

    public Ciudadano obtenerCiudadanoPorDni(String dniCiudadano) {
        return controladora.obtenerCiudadanoPorDni(dniCiudadano);
    }

    public List<Turno> obtenerTurnoPorFecha(LocalDate fecha) {
        return controladora.obtenerTurnoPorFecha(fecha);
    }

    public List<Turno> obtenerTurnoPorEstadoYFecha(LocalDate fecha, String estado) {
        return controladora.obtenerTurnoPorEstadoYFecha(fecha, estado);
    }

    public void modificarEstadoTurno(Integer id, String estado) {

        List<Turno> listaTurnos = obtenerTurno();
        Turno turno = listaTurnos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        turno.setEstado(estado);
        modificarTurno(turno);
    }

    /**
     *
     * @param fechaString
     * @return
     */
    public LocalDate formatearFecha(String fechaString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = null;
        if (!fechaString.isEmpty()) {
            fecha = LocalDate.parse(fechaString, formatter);
        }
        return fecha;
    }

    public void validarCamposRellenos(String valor) throws CiudadanoException {

        if (valor.isEmpty()) {
            throw new CiudadanoException("Todos los campos tienen que estar rellenos ");
        }
    }

    public Ciudadano obtenerCiudadanoExistente(Ciudadano nuevoCiudadano) {

        Ciudadano ciudadano = obtenerCiudadanoPorDni(nuevoCiudadano.getDni());
        if (ciudadano == null) {
            controladora.agregarCiudadano(nuevoCiudadano);
            return obtenerUltimoCiudadanoAgregado();
        } else {
            return ciudadano;
        }
    }

}
