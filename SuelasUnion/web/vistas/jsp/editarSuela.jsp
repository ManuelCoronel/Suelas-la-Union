

<%@page import="DTO.Color"%>
<%@page import="DTO.Tipo"%>
<%@page import="DTO.Suela"%>
<%@page import="DTO.Proveedor"%>
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
        <title>Editar Proveedor</title>

        <!-- Favicons -->
        <link rel="icon" type="image/x-icon" href="../images/icon/logo.ico">

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
                                    <div class="col-lg-6">
                                        <%

                                            Suela suela = (Suela) request.getSession().getAttribute("editarSuela");

                                        %>
                                        <h1>Modificar suela</h1>
                                        <br>
                                        <form action="../../ControladorSuelas"  method="GET">
                                            Modelo:  <br>
                                            <input class="form-control" type="text" name="modelo" value="<%=suela.getModelo()%>" required><br>
                                            Talla: <br>
                                            <input class="form-control" type="text" name="talla" value="<%=suela.getTalla()%>" required><br>
                                            Tipo: <br>
                                            <select name="opcionesTipo" class="form-control">

                                                <%

                                                    List<Tipo> list = (List<Tipo>) request.getSession().getAttribute("tiposLista");

                                                    for (Tipo tipo : list) {
                                                %>
                                                <option  value="<%=tipo.getId()%>"><%=tipo.getTipo()%></option>
                                                <% }%></select>


                                            <br>
                                            Color: <br>
                                            <select name="opcionesColor" class="form-control">
                                                <%

                                                    List<Color> listColor = (List<Color>) request.getSession().getAttribute("colorLista");
                                                    for (Color color : listColor) {
                                                %>
                                                <option value="<%=color.getId()%>"><%=color.getColor()%></option>
                                                <% }%>
                                            </select><br>
                                            Precio: <br>
                                            <input class="form-control" type="text" name="precio" value="<%=suela.getProducto().getPrecio()%>" required><br>
                                            Cantidad Disponible: <br>
                                            <input class="form-control" type="text" name="cantidad" value="<%=suela.getProducto().getCantidadDisponible()%>" required><br>

                                            <br>
                                            <input  type="hidden" name="idSuela" value="<%=suela.getProducto().getId()%>">
                                            <input  type="submit" class="btn btn-success"  name="accion" value="Actualizar"> 
                                            <a href="../../ControladorSuelas?accion=buscar&tipo=<%=suela.getTipoSuela()%>">Atras</a>
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
