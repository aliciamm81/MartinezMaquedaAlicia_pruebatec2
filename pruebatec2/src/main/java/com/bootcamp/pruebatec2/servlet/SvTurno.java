/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bootcamp.pruebatec2.servlet;

import com.bootcamp.pruebatec2.logica.Ciudadano;
import com.bootcamp.pruebatec2.logica.Controladora;
import com.bootcamp.pruebatec2.logica.Tramite;
import com.bootcamp.pruebatec2.logica.Turno;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Alicia
 */
@WebServlet(name = "SvTurno", urlPatterns = {"/SvTurno"})
public class SvTurno extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Maneja las solicitudes GET para recuperar turnos basados en filtros de
     * estado y fecha. Obtiene la fecha y estado proporcionados por el cliente y
     * busca turnos que coincidan. Si el estado es 'completo', devuelve todos
     * los turnos; de lo contrario, busca por estado y fecha. Establece los
     * resultados en el objeto 'request' y reenvía a 'table.jsp' para su
     * visualización.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Turno> listaTurnos = new ArrayList<Turno>();
        LocalDate filtroFecha = controladora.formatterFecha(request.getParameter("filtroFecha"));
        String filtro = request.getParameter("estado");

        if (filtro.equals("completo")) {
            listaTurnos = controladora.findTurnos();
        } else {
            listaTurnos = controladora.findTurnosByFechaAndEstado(filtroFecha, filtro);
        }

        request.setAttribute("respuesta", listaTurnos);
        request.getRequestDispatcher("table.jsp").forward(request, response);

    }

    /**
     *
     * Este método procesa los datos enviados por el formulario para crear un
     * nuevo turno. Obtiene la información del ciudadano y del trámite de la
     * sesión actual y crea un nuevo turno con la fecha proporcionada por el
     * formulario, estableciendo el estado como "En espera". Finalmente,
     * redirige de vuelta a la página principal con un mensaje de confirmación.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession misession = request.getSession();
        LocalDate fechaTurno = controladora.formatterFecha(request.getParameter("fechaTurno"));
        Turno turno = new Turno();
        turno.setCiudadano((Ciudadano) misession.getAttribute("ciudadano"));
        turno.setFecha(fechaTurno);
        turno.setTramite((Tramite) misession.getAttribute("tramite"));
        turno.setEstado("En espera");
        controladora.createTurno(turno);
        request.setAttribute("registroCorrecto", "El turno se ha registrado correctamente");
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
