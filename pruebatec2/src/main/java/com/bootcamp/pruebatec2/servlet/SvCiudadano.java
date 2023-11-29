/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bootcamp.pruebatec2.servlet;

import com.bootcamp.pruebatec2.logica.Ciudadano;
import com.bootcamp.pruebatec2.logica.Controladora;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import javax.servlet.RequestDispatcher;
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
@WebServlet(name = "SvCiudadano", urlPatterns = {"/SvCiudadano"})
public class SvCiudadano extends HttpServlet {

    Controladora controladora = new Controladora();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet SvCiudadano</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet SvCiudadano at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String nombre = request.getParameter("validarNombre");
            String primerApellido = request.getParameter("validarPrimerApellido");
            String segundoApellido = request.getParameter("validarSegundoApellido");
            LocalDate fechaNacimiento = controladora.formatearFecha(request.getParameter("validarFechaNacimiento"));
            String email = request.getParameter("validarEmail");
            String dni = request.getParameter("validarDni");
            controladora.validarCamposRellenos(request.getParameter("validarTelefono"));
            Integer telefono = Integer.valueOf(request.getParameter("validarTelefono"));

            String direccion = request.getParameter("validarDireccion");
            Ciudadano nuevoCiudadano = new Ciudadano(nombre, primerApellido, segundoApellido, fechaNacimiento, email, dni, telefono, direccion);
            nuevoCiudadano = controladora.obtenerCiudadanoExistente(nuevoCiudadano);

            HttpSession misession = request.getSession(true);
            misession.setAttribute("ciudadano", nuevoCiudadano);
            RequestDispatcher despachador = request.getRequestDispatcher("/SvTramite");
            despachador.forward(request, response);

        } catch (Exception e) {
            String mensaje = "Todos los campos tienen que estar rellenos y con valores correctos";
            if (e instanceof NumberFormatException) {
                mensaje = "Los datos en teléfono tienen que ser numéricos";
            }
            request.setAttribute("error", mensaje);
            request.getRequestDispatcher("index.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
