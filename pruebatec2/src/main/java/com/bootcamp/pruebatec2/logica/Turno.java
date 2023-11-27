package com.bootcamp.pruebatec2.logica;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.Valid;

@Entity
@Valid
public class Turno implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate fecha;

    @ManyToOne
    @JoinColumn(name = "ID_TRAMITE")
    private Tramite tramite;

    @ManyToOne
    @JoinColumn(name = "ID_CIUDADANO")
    private Ciudadano ciudadano;

    @Basic(optional = false)
    @Column(nullable = false)
    private String estado;

    public Turno() {
    }

    public Turno(LocalDate fecha, Ciudadano ciudadano, String estado) {

        this.fecha = fecha;
        this.ciudadano = ciudadano;
        this.estado = estado;
        this.tramite = new Tramite();

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public Tramite getTramite() {
        return tramite;
    }

    public void setTramite(Tramite tramite) {
        this.tramite = tramite;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Turno{" + "id=" + id + ", fecha=" + fecha + ", tramite=" + tramite + ", ciudadano=" + ciudadano + ", estado=" + estado + '}';
    }

}
