<%-- 
    Document   : home.jsp
    Created on : 27 nov 2023, 15:07:47
    Author     : Alicia
--%>

<%@page import="org.eclipse.persistence.exceptions.DatabaseException"%>
<%@page import="com.bootcamp.pruebatec2.logica.Turno"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
        <title>Gestor de turnos</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

        <!-- Custom fonts for this template-->
        <link href="vendor/fontawesome-free/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-C6RzsynM9kWDrMNeT87bh95OGNyZPhcTNXj1NW7RuBCsyN/o0jlpcV8Qyq46cDfL" crossorigin="anonymous"></script>
        <!-- Custom styles for this template-->
        <link href="css/sb-admin-2.min.css" rel="stylesheet">

    </head>

    <body id="page-top">
        <!-- Page Wrapper -->
        <div id="wrapper">
            <!-- Sidebar -->
            <ul class="navbar-nav bg-gradient-primary sidebar sidebar-dark accordion" id="accordionSidebar">
                <!-- Sidebar - Brand -->
                <a class="sidebar-brand d-flex align-items-center justify-content-center" href="index.jsp">
                    <div class="sidebar-brand-icon rotate-n-15">
                        <i class="fas fa-ticket-alt"></i>
                    </div>
                    <div class="sidebar-brand-text mx-3">Gestor de turnos</div>
                </a>
                <!-- Divider -->
                <hr class="sidebar-divider my-0">
                <!-- Nav Item - Dashboard -->
                <li class="nav-item active">
                    <a class="nav-link" href="index.">
                        <i class="fas fa-fw fa-tachometer-alt"></i>
                        <span>Dashboard</span></a>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider">
                <!-- Heading -->
                <div class="sidebar-heading">
                    MENU
                </div>
                <!-- Nav Item - Charts -->
                <li class="nav-item">
                    <a class="nav-link" href="index.jsp">
                        <i class="fas fa-fw fa-chart-area"></i>
                        <span>Crear turno</span></a>
                </li>
                <!-- Nav Item - Tables -->
                <li class="nav-item">
                    <a class="nav-link" href="table.jsp">
                        <i class="fas fa-fw fa-table"></i>
                        <span>Tabla de turnos</span></a>
                </li>
                <!-- Divider -->
                <hr class="sidebar-divider d-none d-md-block">

                <!-- Sidebar Toggler (Sidebar) -->
                <div class="text-center d-none d-md-inline">
                    <button class="rounded-circle border-0" id="sidebarToggle"></button>
                </div>
                <!-- Sidebar Message -->
            </ul>
            <!-- End of Sidebar -->
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">                 
                    <!-- Begin Page Content -->
                    <div class="container-fluid mt-4">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Turno</h1>
                        </div>
                        <!-- Content Row -->
                        <div class="row">
                            <!-- Content Column -->
                            <div class="col-lg-11 mb-5">
                                <!-- Illustrations -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Crear Turno</h6>
                                    </div>
                                    <div class="card-body">
                                        <form action ="SvCiudadano" method="post" class="row g-3 needs-validation mt-3" novalidate>
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
                                            <div class="col-md-3">
                                                <label for="fechaTurno" class="form-label">Fecha del turno</label>
                                                <input type="date" class="form-control" id="fechaTurno" name="fechaTurno" required>
                                                <div class="invalid-feedback">
                                                    Please provide a valid zip.
                                                </div>
                                            </div>
                                            <div class="col-md-8">
                                                <label for="descripcionTramite" class="form-label">Descripción del trámite</label>
                                                <textarea class="form-control" id="descripcionTramite" name="descripcionTramite" required></textarea>
                                                <div class="invalid-feedback">
                                                    Please provide a valid city.
                                                </div>
                                            </div>

                                            <div class="col-12">
                                                <button class="btn btn-primary" type="submit">Registrar turno</button>
                                                <% if (request.getAttribute("error") != null) {%>
                                                <div class="alert alert-danger d-flex align-items-center" role="alert">
                                                    <svg class="bi flex-shrink-0 me-2" width="24" height="24" role="img" aria-label="Danger:"><use xlink:href="#exclamation-triangle-fill"/></svg>
                                                    <div >
                                                        Ha ocurrido un error: <%= request.getAttribute("error")%>                    
                                                    </div>
                                                </div>
                                                <%}%>                        
                                            </div>
                                        </form> 
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- /.container-fluid -->

                </div>
                <!-- End of Main Content -->

                <!-- Footer -->
                <footer class="sticky-footer bg-white">
                    <div class="container my-auto">
                        <div class="copyright text-center my-auto">
                            <span>Copyright &copy; Your Website 2021</span>
                        </div>
                    </div>
                </footer>
                <!-- End of Footer -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->


        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="js/sb-admin-2.min.js"></script>

        <!-- Page level plugins -->
        <script src="vendor/chart.js/Chart.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="js/demo/chart-area-demo.js"></script>
        <script src="js/demo/chart-pie-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js" integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+" crossorigin="anonymous"></script>

    </body>

</html>