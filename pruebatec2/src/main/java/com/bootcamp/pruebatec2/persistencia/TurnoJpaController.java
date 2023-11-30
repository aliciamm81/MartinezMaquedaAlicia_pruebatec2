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
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Alicia
 */
public class TurnoJpaController {

    private EntityManagerFactory emf = null;

    public TurnoJpaController() {
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
    public void createTurno(Turno nuevoTurno) throws DatabaseException {
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

    public void updateTurno(Turno turno) throws DatabaseException {
        EntityManager em = getEntityManager();
        em.getTransaction().begin();
        em.merge(turno);
        em.getTransaction().commit();
    }

    /**
     * Obtiene tods los registros que hay en la base de datos de la tabla Turno
     * y los almacen en una lista de tipo Turno
     *
     * @return
     */
    public List<Turno> findTurnos() {
        EntityManager em = this.getEntityManager();
        try {

            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Turno.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Recupera todos los turnos para una fecha específica
     *
     * @param List<Turno>
     * @return
     */
    public List<Turno> findTurnoByDate(LocalDate fecha) {
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
    public List<Turno> findTurnosByStateAndDate(LocalDate fecha, String estado) {
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
