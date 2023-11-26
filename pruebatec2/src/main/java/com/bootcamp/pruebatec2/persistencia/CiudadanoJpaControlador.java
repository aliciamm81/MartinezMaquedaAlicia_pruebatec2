/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bootcamp.pruebatec2.persistencia;

import com.bootcamp.pruebatec2.logica.Ciudadano;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Alicia
 */
public class CiudadanoJpaControlador {

    private EntityManagerFactory emf = null;

    public CiudadanoJpaControlador() {
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
    public boolean agregar(Ciudadano nuevoCiudadano) {
        EntityManager em = null;

        try {
            em = this.getEntityManager();
            em.getTransaction().begin();
            em.persist(nuevoCiudadano);
            em.getTransaction().commit();
            return false;
        } catch (Exception e) {
            return true;
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
    public Ciudadano obtenerPorId(Integer idCiudadano) {

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

    public Ciudadano obtenerPorDni(String dniCiudadano) {

        EntityManager em = this.getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ciudadano> root = cq.from(Ciudadano.class);
            cq.select(root).where(root.get("dni").in(new Object[]{dniCiudadano}));
            Query q = em.createQuery(cq);
            return (Ciudadano) q.getSingleResult();
        } finally {
            if (em != null) {
                em.close();
            }

        }
    }

    public Ciudadano obtenerUltimo() {
        EntityManager em = this.getEntityManager();
        CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
        cq.select(cq.from(Ciudadano.class));
        Query q = em.createQuery(cq);
        List<Ciudadano> listaCiudadanos = q.getResultList();
        Ciudadano ciudadano = listaCiudadanos.stream()
                .sorted(Collections.reverseOrder(Comparator.comparing(Ciudadano::getId)))
                .findFirst()
                .orElseThrow();
        return ciudadano;
    }

}
