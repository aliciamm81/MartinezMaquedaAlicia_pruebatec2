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
    public void agregarTramite(Tramite nuevoTramite) throws DatabaseException {
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
    public List<Tramite> obtenerTramites() {

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

///Con este método podría obtener el ultimo registro sin necesidad de hacer una lambda en
// la clase controladora así obtendría el valor directamente filtrado de la base de datos
//
//    public Tramite obtenerUltimoTramiteAgregado() {
//
//        EntityManager em = this.getEntityManager();
//        try {
//            CriteriaBuilder cb = em.getCriteriaBuilder();
//            CriteriaQuery<Tramite> cq = cb.createQuery(Tramite.class);
//            Root<Tramite> root = cq.from(Tramite.class);
//            cq.select(root).orderBy(cb.desc(root.get("id")));
//            Query q = em.createQuery(cq).setMaxResults(1);
//            return (Tramite) q.getSingleResult();
//        } finally {
//            if (em != null) {
//                em.close();
//            }
//        }
    }
}
