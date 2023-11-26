package com.bootcamp.pruebatec2.logica;

import com.bootcamp.pruebatec2.logica.Turno;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.10.v20211216-rNA", date="2023-11-26T10:29:54")
@StaticMetamodel(Tramite.class)
public class Tramite_ { 

    public static volatile SingularAttribute<Tramite, String> descripcion;
    public static volatile SingularAttribute<Tramite, Integer> id;
    public static volatile SingularAttribute<Tramite, String> nombre;
    public static volatile ListAttribute<Tramite, Turno> listaTurnos;

}