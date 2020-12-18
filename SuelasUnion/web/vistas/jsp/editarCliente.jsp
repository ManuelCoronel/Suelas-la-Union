


<%@page import="DTO.TipoCliente"%>
<%@page import="DTO.Cliente"%>
<%@page import="DAO.*"%>

<%@page import="java.util.List"%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Editar Cliente</title>

        <!-- Favicons -->
        <link rel="icon" type="image/x-icon" href="../images/icon/logo.ico">

        <!-- Formulario-->
        <link rel="stylesheet" href="../css/estilos.css">
        <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700&display=swap" rel="stylesheet">

        <!-- Fontfaces CSS-->
        <link href="../css/font-face.css" rel="stylesheet" media="all">
        <link href="../vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">
        <link href="../vendor/font-awesome-5/css/fontawesome-all.min.css" rel="stylesheet" media="all">
        <link href="../vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">

        <!-- Bootstrap CSS-->
        <link href="../vendor/bootstrap-4.1/bootstrap.min.css" rel="stylesheet" media="all">

        <!-- ../vendor CSS-->
        <link href="../vendor/animsition/animsition.min.css" rel="stylesheet" media="all">
        <link href="../vendor/bootstrap-progressbar/bootstrap-progressbar-3.3.4.min.css" rel="stylesheet" media="all">
        <link href="../vendor/wow/animate.css" rel="stylesheet" media="all">
        <link href="../vendor/css-hamburgers/hamburgers.min.css" rel="stylesheet" media="all">
        <link href="../vendor/slick/slick.css" rel="stylesheet" media="all">
        <link href="../vendor/select2/select2.min.css" rel="stylesheet" media="all">
        <link href="../vendor/perfect-scrollbar/perfect-scrollbar.css" rel="stylesheet" media="all">

        <!-- Main CSS-->
        <link href="../css/theme.css" rel="stylesheet" media="all">


    </head>

    <body class="animsition">
        <div class="page-wrapper">
            <!-- HEADER MOBILE-->
            <jsp:include page="headerMobile.html" flush="true"/>
            <!-- END HEADER MOBILE-->

            <!-- MENU SIDEBAR-->
            <jsp:include page="menuSiderbar.jsp" flush="true"/>
            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <!-- HEADER DESKTOP-->
                <jsp:include page="headerDesktop.jsp" flush="true"/>
                <!-- END HEADER DESKTOP-->

                <!-- MAIN CONTENT-->
                <div class="main-content">
                    <div class="section__content section__content--p30">
                        <div class="container-fluid">                        
                            <div class="row">
                                <div class="container">
                                    <div class="col-lg-6" style="margin-left:290px;">
                                        <%
                                            DTO.Cliente c = (DTO.Cliente) request.getSession().getAttribute("editarCliente");
                                        %>

                                        <form action="../../ControladorCliente"  method="GET">
                                            <h1>Modificar Cliente</h1>

                                            <div class="grupo">
                                                <p>Cédula</p>
                                                <input class="form-control" type="text" name="txtCedula" value="<%= c.getCedula()%>" disabled><span class="barra"></span>  
                                            </div>

                                            <div class="grupo">
                                                <p>Teléfono</p>
                                                <input class="form-control" type="text" name="txtTelefono" value="<%= c.getTelefono()%>" required><span class="barra"></span>
                                            </div>

                                            <div class="grupo">
                                                <p>Dirección</p>
                                                <input class="form-control" type="text" name="txtDireccion" value="<%= c.getDireccion()%>" required><span class="barra"></span>
                                            </div>

                                            <div class="grupo">
                                                <p>Nombre</p>
                                                <input class="form-control" type="text" name="txtNombre" value="<%= c.getNombre()%>" required><span class="barra"></span>
                                            </div>

                                            <div class="grupo">
                                                <p>Correo</p>
                                                <input class="form-control" type="text" name="txtCorreo" value="<%= c.getCorreo()%>" required><span class="barra"></span>
                                            </div>

                                            <div class="grupo">
                                                <p>Fecha registro</p>
                                                <input class="form-control" type="date" name="txtFechaRegistro" value="<%= c.getFechaRegistro()%>" required><span class="barra"></span>
                                            </div>

                                            <div class="grupo">
                                                <p>Fecha nacimiento</p>
                                                <input class="form-control" type="date" name="txtFechaNacimiento" value="<%= c.getFechaNacimiento()%>" required><span class="barra"></span>
                                            </div>

                                            Tipo Cliente: <br>
                                            <select name="opciones" class="form-control">
                                                <%
                                                    List<TipoCliente> list = (List<TipoCliente>) request.getSession().getAttribute("tiposDeClientes");
                                                    for (TipoCliente dto : list) {
                                                %>
                                                <option value="<%= dto.getIdTipoCliente()%>"><%= dto.getTipo()%></option>
                                                <% }%>
                                            </select>
                                            <br>
                                            <input  type="hidden" name="txtCedula" value="<%= c.getCedula()%>">

                                            <input  type="submit" class="botones"  name="accion" value="Actualizar"> 
                                            <a  class="btn botones1" style="margin-left: 33px; width: 80%;"  href="../../ControladorCliente?accion=listar">Atras</a>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-- END MAIN CONTENT-->
            </div>
            <!-- END PAGE CONTAINER-->

        </div>

        <!-- Jquery JS-->
        <script src="../vendor/jquery-3.2.1.min.js"></script>
        <!-- Bootstrap JS-->
        <script src="../vendor/bootstrap-4.1/popper.min.js"></script>
        <script src="../vendor/bootstrap-4.1/bootstrap.min.js"></script>
        <!-- ../vendor JS       -->
        <script src="../vendor/slick/slick.min.js">
        </script>
        <script src="../vendor/wow/wow.min.js"></script>
        <script src="../vendor/animsition/animsition.min.js"></script>
        <script src="../vendor/bootstrap-progressbar/bootstrap-progressbar.min.js">
        </script>
        <script src="../vendor/counter-up/jquery.waypoints.min.js"></script>
        <script src="../vendor/counter-up/jquery.counterup.min.js">
        </script>
        <script src="../vendor/circle-progress/circle-progress.min.js"></script>
        <script src="../vendor/perfect-scrollbar/perfect-scrollbar.js"></script>
        <script src="../vendor/chartjs/Chart.bundle.min.js"></script>
        <script src="../vendor/select2/select2.min.js">
        </script>

        <!-- Main JS-->
        <script src="../js/main.js"></script>

    </body>

</html>
