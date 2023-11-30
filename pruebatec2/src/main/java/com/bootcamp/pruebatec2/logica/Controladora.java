/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.logica;

import com.bootcamp.pruebatec2.persistencia.ControladorPersistencia;
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

    /**
     * Llama a la controladora de persistencia y le manda como parámetro el
     * tramite que se quiere crear en la base de datos
     *
     * @param nuevoTramite
     * @throws DatabaseException
     */
    public void createTramite(Tramite nuevoTramite) throws DatabaseException {
        controladora.createTramite(nuevoTramite);
    }

    /**
     * Llama a la controladora de persistencia y le manda como parámetro el
     * turno que se quiere crear en la base de datos
     *
     * @param nuevoTurno
     * @throws DatabaseException
     */
    public void createTurno(Turno nuevoTurno) throws DatabaseException {
        controladora.createTurno(nuevoTurno);
    }

    /**
     *
     * Llama a la controladora de persistencia y le manda como parámetro el
     * ciudadano que se quiere crear en la base de datos* @param nuevoCiudadano
     *
     * @throws DatabaseException
     */
    public void createCiudadano(Ciudadano nuevoCiudadano) throws DatabaseException {
        controladora.createCiudadano(nuevoCiudadano);
    }

    /**
     * Llama a la controladora de persistencia y le manda como parámetro el
     * turno que se quiere modificar en la base de datos
     *
     * @param turno
     * @throws DatabaseException
     */
    public void updateTurno(Turno turno) throws DatabaseException {
        controladora.updateTurno(turno);
    }

    /**
     * Llama a la controladora de persistencia y le solicita la lista de todos
     * los turnos que existen en la base de datos
     *
     * @return
     */
    public List<Turno> findTurnos() {
        return controladora.findTurnos();
    }

    /**
     * Devuelve el ciudadano que coincida con el id pasado como parámetro
     *
     * @param idCiudadano
     * @return
     */
    public Ciudadano readCiudadano(Integer idCiudadano) {
        return controladora.readCiudadano(idCiudadano);
    }

    /**
     * Devuelve el ciudadano que coincida con el dni pasado como parámetro
     *
     * @param idCiudadano
     * @return
     */
    public Ciudadano findCiudadanoByDni(String dniCiudadano) {
        return controladora.findCiudadanoByDni(dniCiudadano);
    }

    /**
     * Devuelve una lista de turnos que coincidan con el estado y la fecha
     * pasadas como parámetro
     *
     * @param fecha
     * @param estado
     * @return
     */
    public List<Turno> findTurnosByFechaAndEstado(LocalDate fecha, String estado) {
        return controladora.findTurnoByEstadoAndFecha(fecha, estado);
    }

    /**
     * Devuelve el último trámite que se ha creado en la base de datos.
     *
     * @return
     */
    public Tramite findUltimoTramite() {
        List<Tramite> listaTramites = controladora.findTramites();
        Tramite tramite = listaTramites.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Tramite::getId)))
                .findFirst()
                .orElse(null);
        return tramite;

    }

    /**
     * Devuelve el último ciudadano que se ha creado en la base de datos.
     *
     * @return
     */
    public Ciudadano findUltimoCiudadano() {
        List<Ciudadano> listaCiudadanos = controladora.findCiudadanos();
        Ciudadano ciudadano = listaCiudadanos.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Ciudadano::getId)))
                .findFirst()
                .orElse(null);
        return ciudadano;

    }

    /**
     * Crea una lista con todos los turnos que hay en la base de datos y busca
     * el elemento que coincida con el id y el estado pasado como parámetro, le
     * setea el estado al elementro encontrado y llama al método que lo
     * actualiza en la base de datos
     *
     * @param id
     * @param estado
     */
    public void updateTurnoEstado(Integer id, String estado) {

        List<Turno> listaTurnos = findTurnos();
        Turno turno = listaTurnos.stream()
                .filter(p -> p.getId() == id)
                .findFirst()
                .orElse(null);
        turno.setEstado(estado);
        updateTurno(turno);
    }

    /**
     * Método que dada una fecha pasada como string la formatea según el orden
     * indicado y la devuelve como un tipo LocalDate
     *
     * @param fechaString
     * @return
     */
    public LocalDate formatterFecha(String fechaString) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fecha = null;
        if (!fechaString.isEmpty()) {
            fecha = LocalDate.parse(fechaString, formatter);
        }
        return fecha;
    }

    /**
     * Método que valida se el ciudadano pasádo como parámetro existe en la base
     * de datos
     *
     * @param nuevoCiudadano
     * @return
     */
    public Ciudadano validateExisteCiudadano(Ciudadano nuevoCiudadano) {

        Ciudadano ciudadano = findCiudadanoByDni(nuevoCiudadano.getDni());
        if (ciudadano == null) {
            controladora.createCiudadano(nuevoCiudadano);
            return findUltimoCiudadano();
        } else {
            return ciudadano;
        }
    }

}
