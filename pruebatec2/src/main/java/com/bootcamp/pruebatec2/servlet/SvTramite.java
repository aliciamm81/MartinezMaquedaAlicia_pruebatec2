package com.bootcamp.pruebatec2.servlet;

import com.bootcamp.pruebatec2.logica.Controladora;
import com.bootcamp.pruebatec2.logica.Tramite;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "SvTramite", urlPatterns = {"/SvTramite"})
public class SvTramite extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Este método procesa la solicitud HTTP POST enviada por el cliente. Crea
     * un nuevo objeto Tramite a partir de los parámetros proporcionados por la
     * solicitud Luego, lo almacena en la base de datos a través del
     * controlador. Después de almacenar el objeto Tramite, obtiene el último
     * Tramite creado. Establece este último Tramite como un atributo de la
     * sesión actual para ser utilizado más tarde. Finalmente, redirige la
     * solicitud hacia "/SvTurno"
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Tramite nuevoTramite = new Tramite();
        nuevoTramite.setNombre(request.getParameter("nombreTramite"));
        nuevoTramite.setDescripcion(request.getParameter("descripcionTramite"));

        controladora.createTramite(nuevoTramite);

        nuevoTramite = controladora.findUltimoTramite();
        HttpSession misession = request.getSession();
        misession.setAttribute("tramite", nuevoTramite);
        RequestDispatcher despachador = request.getRequestDispatcher("/SvTurno");
        despachador.forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
