package com.bootcamp.pruebatec2.logica;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Entity
@Valid
public class Ciudadano implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank
    private String nombre;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank
    private String primerApellido;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank
    private String segundoApellido;

    @Basic(optional = false)
    @Column(nullable = false)
    private LocalDate fechaNacimiento;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank
    @Email
    private String email;

    @Basic(optional = false)
    @Column(unique = true, nullable = false)
    @NotBlank
    private String dni;

    @Basic(optional = false)
    @Column(nullable = false)
    private Integer telefono;

    @Basic(optional = false)
    @Column(nullable = false)
    @NotBlank
    private String direccion;

    @OneToMany(mappedBy = "ciudadano")
    private List<Turno> turnos;

    public Ciudadano() {
    }

    public Ciudadano(String nombre, String primerApellido, String segundoApellido, LocalDate fechaNacimiento, String email, String dni, Integer telefono, String direccion) {
        this.nombre = nombre;
        this.primerApellido = primerApellido;
        this.segundoApellido = segundoApellido;
        this.fechaNacimiento = fechaNacimiento;
        this.email = email;
        this.dni = dni;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPrimerApellido() {
        return primerApellido;
    }

    public void setPrimerApellido(String primerApellido) {
        this.primerApellido = primerApellido;
    }

    public String getSegundoApellido() {
        return segundoApellido;
    }

    public void setSegundoApellido(String segundoApellido) {
        this.segundoApellido = segundoApellido;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Integer getTelefono() {
        return telefono;
    }

    public void setTelefono(Integer telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(LinkedList<Turno> turnos) {
        this.turnos = turnos;
    }

    @Override
    public String toString() {
        return "Id: " + id + ", Nombre : " + nombre + " " + primerApellido + " " + segundoApellido + ", Email: " + email + ", DNI: " + dni + ", Telefono: " + telefono + ", Direccion: " + direccion;
    }

}
