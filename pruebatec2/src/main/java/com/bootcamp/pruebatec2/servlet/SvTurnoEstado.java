/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.bootcamp.pruebatec2.servlet;

import com.bootcamp.pruebatec2.logica.Controladora;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Alicia
 */
@WebServlet(name = "SvTurnoEstado", urlPatterns = {"/SvTurnoEstado"})
public class SvTurnoEstado extends HttpServlet {

    Controladora controladora = new Controladora();
    public static final String URL = "http://localhost:8080/pruebatec2/";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Este método procesa la solicitud POST para modificar el estado de un
     * turno asociado a un ciudadano. Obtiene el nuevo estado y el ID del
     * ciudadano del formulario y llama al método de la controladora para
     * actualizar el estado del turno correspondiente a ese ciudadano. Luego
     * redirige a la página de visualización de turnos con un filtro aplicado
     * para mostrar el listado completo.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String modificarEstado = request.getParameter("estado");
        Integer idCiudadano = Integer.valueOf(request.getParameter("idCiudadano"));
        controladora.updateTurnoEstado(idCiudadano, modificarEstado);
        response.sendRedirect(URL + "SvTurno?filtroFecha=&estado=completo");

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
