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

@WebServlet(name = "SvTurno", urlPatterns = {"/SvTurno"})
public class SvTurno extends HttpServlet {

    Controladora controladora = new Controladora();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Maneja una solicitud GET para filtrar la lista de turnos según los
     * parámetros de fecha y estado. Los resultados se envían a 'table.jsp' como
     * atributo 'respuesta' o 'error' para mostrar la lista de turnos o un
     * mensaje respectivamente.
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
        String filtroEstado = request.getParameter("estado");

        // NOTA: He preferido hacer tres métodos de consulta directa a la base de datos porque considero que
        // es más óptimo que hacer solo una consulta y un filtrado posterior con una lambda
        String mensajeRequest = null;
        if (filtroFecha == null && filtroEstado == null) {
            listaTurnos = controladora.findTurnos();
        } else if (filtroFecha != null && filtroEstado == null) {
            listaTurnos = controladora.findTurnosByFecha(filtroFecha);
        } else if (filtroFecha != null && filtroEstado != null) {
            listaTurnos = controladora.findTurnosByFechaAndEstado(filtroFecha, filtroEstado);
        } else {
            mensajeRequest = "Selecciona la fecha por favor";
        }

        if (mensajeRequest != null) {
            request.setAttribute("error", mensajeRequest);
        } else {
            request.setAttribute("respuesta", listaTurnos);
        }
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
