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
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.eclipse.persistence.exceptions.DatabaseException;

/**
 *
 * @author Alicia
 */
@WebServlet(name = "SvTurno", urlPatterns = {"/SvTurno"})
public class SvTurno extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvTurno</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvTurno at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Turno> listaTurnos = new ArrayList<Turno>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate filtroFecha = null;
        String filtroFechaStr = request.getParameter("filtroFecha");
        if (!filtroFechaStr.isEmpty()) {
            filtroFecha = LocalDate.parse(filtroFechaStr, formatter);
        }
        String filtro = request.getParameter("estado");
        if (filtro.equals("completo")) {
            listaTurnos = controladora.obtenerTurno();
        } else {
            listaTurnos = controladora.obtenerTurnoPorEstadoYFecha(filtroFecha, filtro);

        }

        request.setAttribute("respuesta", listaTurnos);
        request.getRequestDispatcher("table.jsp").forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
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
        Ciudadano ciudadano = (Ciudadano) misession.getAttribute("ciudadano");
        Tramite tramite = (Tramite) misession.getAttribute("tramite");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate fechaTurno = null;
        String fechaTurnosStr = request.getParameter("fechaTurno");
        if (!fechaTurnosStr.isEmpty()) {
            fechaTurno = LocalDate.parse(fechaTurnosStr, formatter);
        }
        Turno turno = new Turno();
        turno.setCiudadano(ciudadano);
        turno.setFecha(fechaTurno);
        turno.setTramite(tramite);
        turno.setEstado("En espera");
        System.out.println("Turno: " + turno.toString());
        try {
            controladora.agregarTurno(turno);

        } catch (DatabaseException e) {
            System.out.println("Faltan datos de turno");

        }
        response.sendRedirect("index.jsp");

    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
