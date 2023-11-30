/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.persistencia;

import com.bootcamp.pruebatec2.logica.Ciudadano;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Alicia
 */
public class CiudadanoJpaController {

    private EntityManagerFactory emf = null;

    public CiudadanoJpaController() {
        this.emf = Persistence.createEntityManagerFactory("gestorTurnosDBJspPU");
    }

    public EntityManager getEntityManager() {
        return this.emf.createEntityManager();
    }

    /**
     * Inserta un nuevo ciudadano en la base de datos
     *
     * @param nuevoCiudadano
     */
    public void createCiudadano(Ciudadano nuevoCiudadano) throws DatabaseException {
        EntityManager em = null;

        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            em.persist(nuevoCiudadano);
            em.getTransaction().commit();

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Recupera la información de un ciudadano por su ID
     *
     * @param idCiudadano
     * @return
     */
    public Ciudadano readCiudadano(Integer idCiudadano) {

        EntityManager em = this.getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudadano> root = cq.from(Ciudadano.class);
            cq.select(root).where(root.get("id").in(new Object[]{idCiudadano}));
            Query q = em.createQuery(cq);
            return (Ciudadano) q.getSingleResult();
        } finally {
            if (em != null) {
                em.close();
            }

        }
    }

    /**
     * Dado un dni de un ciudadano busca si existe en la base de datos, devuelve
     * el el ciudadano que coincida con ese dni, si no hay ninguna coincidencia
     * devuelve null
     *
     * @param dniCiudadano
     * @return
     */
    public Ciudadano findCiudadanoByDni(String dniCiudadano) {

        EntityManager em = this.getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudadano> root = cq.from(Ciudadano.class);
            cq.select(root).where(root.get("dni").in(new Object[]{dniCiudadano}));
            Query q = em.createQuery(cq);
            return (Ciudadano) q.getSingleResult();
        } catch (NoResultException e) {
            return null;

        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    /**
     * Método que devuelve una lista de todos los ciudadanos registrados en la
     * base de datos
     *
     * @return Ciudadano
     */
    public List<Ciudadano> findCiudadanos() {
        EntityManager em = this.getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ciudadano.class));
            Query q = em.createQuery(cq);
            return q.getResultList();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

}
