/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.persistencia;

import com.bootcamp.pruebatec2.logica.Tramite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Alicia
 */
public class TramiteJpaController {

    private EntityManagerFactory emf = null;

    public TramiteJpaController() {
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
    public void createTramite(Tramite nuevoTramite) throws DatabaseException {
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

    /**
     * Método que devuelve una lista de todos los tramites registrados en la
     * base de datos
     *
     * @return List<Tramite>
     */
    public List<Tramite> findTramites() {

        EntityManager em = this.getEntityManager();
        try {

            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Tramite.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }

    }
}
