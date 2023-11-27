<%-- 
    Document   : index
    Created on : 24 nov 2023, 19:09:22
    Author     : Alicia
--%>

<%@page import="org.eclipse.persistence.exceptions.DatabaseException"%>
<%@page import="com.bootcamp.pruebatec2.logica.Turno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">   
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
    <body>
        <div class="container mt-3" style="width: 80%">
            <h1>Gestor de Turnos</h1>

            <form action ="SvCiudadano" method="post" accept-charset="UTF-8" class="row g-3 needs-validation mt-3" novalidate>
                <div class="col-md-3">
                    <label for="validationServer01" class="form-label">Nombre</label>
                    <input type="text" class="form-control" id="validationServer01"  name="validarNombre" required>

                    <div class="invalid-feedback">
                        <%=request.getAttribute("errorCiudadano")%>
                    </div>
                </div>
                <div class="col-md-4">
                    <label for="validarPrimerApellido" class="form-label">Primer apellido</label>
                    <input type="text" class="form-control" id="validarPrimerApellido"  name="validarPrimerApellido" required>

                    <div class="invalid-feedback">
                        <%=request.getAttribute("errorCiudadano")%>
                    </div>
                </div>
                <div class="col-md-4">
                    <label for="validarSegundoApellido" class="form-label">Segundo apellido</label>
                    <input type="text" class="form-control" id="validarSegundoApellido"  name="validarSegundoApellido" required>

                </div>
                <div class="col-md-3">
                    <label for="validarFechaNacimiento" class="form-label">Fecha Nacimiento</label>
                    <input type="date" class="form-control" id="validarFechaNacimiento" name="validarFechaNacimiento" required>
                    <div class="invalid-feedback">
                        Please provide a valid zip.
                    </div>
                </div>
                <div class="col-md-3">
                    <label for="validarEmail" class="form-label">Email</label>
                    <div class="input-group has-validation">
                        <span class="input-group-text" id="inputGroupPrepend">@</span>
                        <input type="text" class="form-control" id="validarEmail" aria-describedby="inputGroupPrepend" name="validarEmail" required>
                        <div class="invalid-feedback">
                            Please choose a username.
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <label for="validarDni" class="form-label">Dni</label>
                    <input type="text" class="form-control" id="validarDni" name="validarDni" required>
                    <div class="invalid-feedback">
                        Please provide a valid city.
                    </div>
                </div>
                <div class="col-md-3">
                    <label for="validarTelefono" class="form-label">Telefono</label>
                    <input type="text" class="form-control" id="validarTelefono" name="validarTelefono" required>
                    <div class="invalid-feedback">
                        Please provide a valid city.
                    </div>
                </div>

                <div class="col-md-6">
                    <label for="validarDireccion" class="form-label">Dirección</label>
                    <input type="text" class="form-control" id="validarDireccion" name="validarDireccion" required>
                    <div class="invalid-feedback">
                        Please provide a valid city.
                    </div>
                </div>

                <div class="col-md-3">
                    <label for="nombreTramite" class="form-label">Tipo de trámite</label>
                    <select class="form-select" id="nombreTramite" required name="nombreTramite">
                        <option selected disabled value="">Selecciona...</option>
                        <option>Renovación de Documentos</option>
                        <option>Solicitudes de Permisos y Licencias</option>
                        <option>Trámites de Salud</option>
                        <option>Trámites Educativos</option>
                        <option>Trámites Fiscales y Financieros</option>
                        <option>Trámites de Vivienda y Urbanismo</option>
                        <option>Trámites Legales</option>
                        <option>Servicios de Transporte</option>
                    </select>
                    <div class="invalid-feedback">
                        Please select a valid state.
                    </div>

                </div>

                <div class="col-md-8">
                    <label for="descripcionTramite" class="form-label">Descripción del trámite</label>
                    <textarea class="form-control" id="descripcionTramite" name="descripcionTramite" required></textarea>
                    <div class="invalid-feedback">
                        Please provide a valid city.
                    </div>
                </div>
                <div class="col-md-3">
                    <label for="fechaTurno" class="form-label">Fecha del turno</label>
                    <input type="date" class="form-control" id="fechaTurno" name="fechaTurno" required>
                    <div class="invalid-feedback">
                        Please provide a valid zip.
                    </div>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="pendiente" name="estado" id="estado"  >
                    <label class="form-check-label" for="estado">
                        Pendiente
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="tramitado" name="estado" id="estado"  >
                    <label class="form-check-label" for="estado">
                        Tramitado
                    </label>
                </div>
                <div class="col-12">
                    <button class="btn btn-primary" type="submit">Registrar turno</button>
                    <% if (request.getAttribute("errorCiudadano") != null) {%>
                    <div class="alert alert-danger d-flex align-items-center" role="alert">
                        <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                        <div >
                            Ha ocurrido un error al agregar el ciudadano: <%= request.getAttribute("errorCiudadano")%>                    
                        </div>
                    </div>
                    <%}%>                        
                </div>
            </form> 

            <form action ="SvTurno" method="get" accept-charset="UTF-8" class="row g-3 needs-validation mt-3" novalidate>
                <div class="col-md-3">
                    <label for="filtroFecha" class="form-label">Fecha turno</label>
                    <input type="date" class="form-control" id="filtroFecha" name="filtroFecha" required>
                    <div class="invalid-feedback">
                        Please provide a valid zip.
                    </div>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="pendiente" name="estado" id="estado"  >
                    <label class="form-check-label" for="estado">
                        Pendiente
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="tramitado" name="estado" id="estado"  >
                    <label class="form-check-label" for="estado">
                        Tramitado
                    </label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" value="completo" name="completo" id="completo"  >
                    <label class="form-check-label" for="estado">
                        Listado completo
                    </label>
                </div>

                <div class="col-12">
                    <button class="btn btn-primary" type="submit">Mostrar turnos</button>
                </div>
            </form>  
            <% if (request.getAttribute("respuesta") != null) { %>

            <table class="table">
                <thead>
                    <tr>
                        <th scope="col">Nº Turno</th>
                        <th scope="col">Fecha</th>
                        <th scope="col">Estado</th>
                        <th scope="col">Ciudadano</th>
                        <th scope="col">Tramite</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Turno turno : (List<Turno>) request.getAttribute("respuesta")) {%>
                    <tr>
                        <th scope="row"><%=turno.getId()%> </th>
                        <td><%=turno.getFecha()%></td>
                        <td><%=turno.getEstado()%></td>
                        <td><%=turno.getCiudadano()%></td>
                        <td>@<%=turno.getTramite()%></td>
                    </tr>
                    <% }%>
                </tbody>
            </table>
            <% }%>

        </div>
        <!-- Bootstrap -->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
    </body>
</html>
