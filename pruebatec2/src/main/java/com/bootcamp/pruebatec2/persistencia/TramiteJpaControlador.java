/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.persistencia;

import com.bootcamp.pruebatec2.logica.Tramite;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 *
 * @author Alicia
 */
public class TramiteJpaControlador {

    private EntityManagerFactory emf = null;

    public TramiteJpaControlador() {
        this.emf = Persistence.createEntityManagerFactory("gestorTurnosDBJspPU");
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    /**
     * Inserta un nuevo trámite en la base de datos con la información
     * proporcionada.
     *
     * @param nuevoTramite
     */
    public void agregar(Tramite nuevoTramite) {
        EntityManager em = null;

        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            em.persist(nuevoTramite);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }

        }
    }

    public Tramite obtenerUltimo() {
        EntityManager em = this.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Tramite.class));
        Query q = em.createQuery(cq);
        List<Tramite> listaTramites = q.getResultList();
        Tramite tramite = listaTramites.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Tramite::getId)))
                .findFirst()
                .orElseThrow();
        return tramite;
    }

}
