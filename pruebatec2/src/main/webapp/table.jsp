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

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">

        <title>SB Admin 2 - Dashboard</title>

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
                    <a class="nav-link" href="index.jsp">
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
                <div id="content ">
                    <!-- Begin Page Content -->
                    <div class="container-fluid mt-4">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Listado</h1>
                        </div>
                        <!-- Content Row -->
                        <div class="row">
                            <!-- Content Column -->
                            <div class="col-lg-15 mb-4">

                                <!-- Illustrations -->
                                <div class="card shadow mb-5">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Listar turnos</h6>
                                    </div>
                                    <div class="card-body">
                                        <div class="container-fluid">

                                            <!-- Page Heading -->
                                            <form action ="SvTurno" method="get" class="row g-4  needs-validation mt-1 align-items-start" novalidate>

                                                <div class="col-md-3 mb-4 ">
                                                    <label for="filtroFecha" class="form-label">Fecha turno</label>
                                                    <input type="date" class="form-control" id="filtroFecha" name="filtroFecha" required>

                                                </div>
                                                <div class="col-md-5 mt-5 ml-5">
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" value="Atendido" name="estado" id="estado">
                                                        <label class="form-check-label" for="estado">
                                                            Atendido
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" value="En espera" name="estado" id="estado">
                                                        <label class="form-check-label" for="estado">
                                                            En espera
                                                        </label>
                                                    </div>
                                                    <div class="form-check form-check-inline">
                                                        <input class="form-check-input" type="radio" value="completo" name="estado" id="estado" checked>
                                                        <label class="form-check-label" for="estado">
                                                            Listado completo
                                                        </label>
                                                    </div>
                                                </div>

                                                <div class="col-md-3 mt-5 text-end">
                                                    <button class="btn btn-primary" type="submit">Mostrar turnos</button>
                                                </div>

                                            </form>  
                                            <% if (request.getAttribute("respuesta") != null) {%>
                                            <!-- DataTales Example -->
                                            <div class="card shadow mb-4 ">
                                                <div class="card-header py-3">
                                                    <h6 class="m-0 font-weight-bold text-primary">Lista de turnos</h6>
                                                </div>
                                                <div class="card-body">
                                                    <div class="table-responsive">
                                                        <table class="table table-bordered" id="dataTable" width="100%" >
                                                            <thead>
                                                                <tr>
                                                                    <th>Numero</th>
                                                                    <th>Fecha</th>
                                                                    <th>Estado</th>
                                                                    <th>Ciudadano</th>
                                                                    <th>Tramite</th>
                                                                    <th>Modificar</th>

                                                                </tr>
                                                            </thead>

                                                            <tbody>
                                                                <% for (Turno turno : (List<Turno>) request.getAttribute("respuesta")) {%>

                                                                <tr>
                                                                    <td><%=turno.getId()%></td>
                                                                    <td><%=turno.getFecha()%></td>
                                                                    <td><%=turno.getEstado()%></td>
                                                                    <td><%=turno.getCiudadano()%></td>
                                                                    <td><%=turno.getTramite()%></td>
                                                                    <td>    <form action="SvTurnoEstado" method="post">
                                                                            <input type="hidden" class="form-control" id="idCiudadano" value="<%=turno.getId()%>" name="idCiudadano" required>
                                                                            <button type="submit" name="estado" value="En espera" class="btn btn-link">
                                                                                <i class="fas fa-solid fa-clock fa-sm"></i> Espera
                                                                            </button>
                                                                            <button type="submit" name="estado" value="Atendido" class="btn btn-link">
                                                                                <i class="fas fa-solid fa-check fa-xs"></i> Atendido
                                                                            </button>
                                                                        </form>
                                                                    </td>
                                                                </tr>
                                                                <% }%>

                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                            <% }%>

                                        </div>
                                    </div>
                                </div>

                                <!-- Approach -->
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Modo de uso</h6>
                                    </div>
                                    <div class="card-body">
                                        <p>Para buscar un turno filtrado, introduce la fecha y una opción del estado que se quiere consultar, después hacer click en el botón de mostrar turnos</p>
                                        <p class="mb-0">Para mostrar el listado completo hay que seleccionar la opción y hacer click en mostrar turnos sin necesidad de indicar una fecha</p>
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

        <!-- Scroll to Top Button-->
        <a class="scroll-to-top rounded" href="#page-top">
            <i class="fas fa-angle-up"></i>
        </a>

        <!-- Logout Modal-->
        <div class="modal fade" id="logoutModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
             aria-hidden="true">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Ready to Leave?</h5>
                        <button class="close" type="button" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>
                    <div class="modal-body">Select "Logout" below if you are ready to end your current session.</div>
                    <div class="modal-footer">
                        <button class="btn btn-secondary" type="button" data-dismiss="modal">Cancel</button>
                        <a class="btn btn-primary" href="login.html">Logout</a>
                    </div>
                </div>
            </div>
        </div>

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