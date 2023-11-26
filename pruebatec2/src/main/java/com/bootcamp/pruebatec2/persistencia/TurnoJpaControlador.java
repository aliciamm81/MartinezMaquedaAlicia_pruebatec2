/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.persistencia;

import com.bootcamp.pruebatec2.logica.Turno;
import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alicia
 */
public class TurnoJpaControlador {

    private EntityManagerFactory emf = null;

    public TurnoJpaControlador() {
        this.emf = Persistence.createEntityManagerFactory("gestorTurnosDBJspPU");
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    /**
     * Inserta un nuevo turno en la base de datos con la información
     * proporcionada
     *
     * @param nuevoTurno
     */
    public void agregar(Turno nuevoTurno) {
        EntityManager em = null;

        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            em.persist(nuevoTurno);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Turno> obtener() {
        EntityManager em = this.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Turno.class));
        Query q = em.createQuery(cq);
        return q.getResultList();
    }

    /**
     * Recupera todos los turnos para una fecha específica
     *
     * @param fecha
     * @return
     */
    public List<Turno> obtenerPorFecha(LocalDate fecha) {
        EntityManager em = this.getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Turno> root = cq.from(Turno.class);
            cq.select(root).where(root.get("fecha").in(new Object[]{fecha}));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Recupera los turnos para una fecha y estado específicos
     *
     * @param fecha
     * @param estado
     * @return
     */
    public List<Turno> obtenerPorEstadoYFecha(LocalDate fecha, String estado) {
        EntityManager em = this.getEntityManager();
        try {
            CriteriaBuilder cb = em.getCriteriaBuilder();
            CriteriaQuery<Turno> cq = cb.createQuery(Turno.class);
            Root<Turno> root = cq.from(Turno.class);

            Predicate predicateForFecha
                    = cb.equal(root.get("fecha"), fecha);
            Predicate predicateForEstado
                    = cb.equal(root.get("estado"), estado);
            Predicate finalPredicate
                    = cb.and(predicateForFecha, predicateForEstado);
            cq.where(finalPredicate);
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
}