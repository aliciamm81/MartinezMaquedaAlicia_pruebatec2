package com.bootcamp.pruebatec2.logica;

import com.bootcamp.pruebatec2.logica.Turno;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-11-26T10:29:54")
@StaticMetamodel(Ciudadano.class)
public class Ciudadano_ { 

    public static volatile SingularAttribute<Ciudadano, String> primerApellido;
    public static volatile SingularAttribute<Ciudadano, LocalDate> fechaNacimiento;
    public static volatile SingularAttribute<Ciudadano, String> direccion;
    public static volatile SingularAttribute<Ciudadano, String> segundoApellido;
    public static volatile ListAttribute<Ciudadano, Turno> turnos;
    public static volatile SingularAttribute<Ciudadano, Integer> id;
    public static volatile SingularAttribute<Ciudadano, Integer> telefono;
    public static volatile SingularAttribute<Ciudadano, String> nombre;
    public static volatile SingularAttribute<Ciudadano, String> email;
    public static volatile SingularAttribute<Ciudadano, String> dni;

}