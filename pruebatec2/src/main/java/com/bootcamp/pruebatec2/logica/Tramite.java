package com.bootcamp.pruebatec2.logica;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Tramite implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nombre;

    private String descripcion;

    @OneToMany(mappedBy = "tramite")
    private List<Turno> listaTurnos;

    public Tramite() {
    }

    public Tramite(String nombre, String descripcion, List<Turno> listaTurnos) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.listaTurnos = listaTurnos;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Turno> getListaTurnos() {
        return listaTurnos;
    }

    public void setListaTurnos(LinkedList<Turno> listaTurnos) {
        this.listaTurnos = listaTurnos;
    }

    @Override
    public String toString() {
        return "Tipo: " + nombre + " descripcion: " + descripcion;
    }

}
