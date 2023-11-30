package com.bootcamp.pruebatec2.servlet;

import com.bootcamp.pruebatec2.logica.Ciudadano;
import com.bootcamp.pruebatec2.logica.Controladora;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.eclipse.persistence.exceptions.DatabaseException;

@WebServlet(name = "SvCiudadano", urlPatterns = {"/SvCiudadano"})
public class SvCiudadano extends HttpServlet {

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
     * Procesa la solicitud POST para validar y registrar un nuevo ciudadano. Se
     * obtienen los datos del formulario y se crea un nuevo objeto Ciudadano. Se
     * verifica si ya existe un ciudadano con los mismos datos, en cuyo caso se
     * redirecciona a la página de tramites. En caso de errores, como campos
     * vacíos o datos incorrectos, se manejan las excepciones correspondientes y
     * se redirecciona a la página principal con un mensaje de error específico.
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            Ciudadano nuevoCiudadano = new Ciudadano();
            nuevoCiudadano.setNombre(request.getParameter("validarNombre"));
            nuevoCiudadano.setPrimerApellido(request.getParameter("validarPrimerApellido"));
            nuevoCiudadano.setSegundoApellido(request.getParameter("validarSegundoApellido"));
            nuevoCiudadano.setFechaNacimiento(controladora.formatterFecha(request.getParameter("validarFechaNacimiento")));
            nuevoCiudadano.setEmail(request.getParameter("validarEmail"));
            nuevoCiudadano.setDni(request.getParameter("validarDni"));
            nuevoCiudadano.setTelefono(Integer.valueOf(request.getParameter("validarTelefono")));
            nuevoCiudadano.setDireccion(request.getParameter("validarDireccion"));

            nuevoCiudadano = controladora.validateExisteCiudadano(nuevoCiudadano);

            HttpSession misession = request.getSession(true);
            misession.setAttribute("ciudadano", nuevoCiudadano);
            RequestDispatcher despachador = request.getRequestDispatcher("/SvTramite");
            despachador.forward(request, response);

        } catch (Exception e) {
            String mensaje = "Todos los campos tienen que estar rellenos y con valores correctos";
            if (e instanceof NumberFormatException) {
                mensaje = "Los datos en teléfono tienen que ser numéricos";
            } else if (e instanceof DatabaseException) {
                mensaje = "Error en la base de datos";
            }
            request.setAttribute("error", mensaje);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
