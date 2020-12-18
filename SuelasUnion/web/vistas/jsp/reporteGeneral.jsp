<%-- 
    Document   : reporteGeneral
    Created on : 10/12/2020, 05:02:43 PM
    Author     : ronal
--%>

<%@page import="DTO.ProductoPedidoVenta"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Reporte de ventas</title>

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
            <!-- END HEADER MOBILE-->
            <jsp:include page="headerMobile.html" flush="true"/>
            <!-- MENU SIDEBAR-->
            <jsp:include page="menuSiderbar.jsp" flush="true"/>
            <!-- END MENU SIDEBAR-->

            <!-- PAGE CONTAINER-->
            <div class="page-container">
                <!-- HEADER DESKTOP-->
                <jsp:include page="headerDesktop.jsp" flush="true"/>
                <!-- END HEADER DESKTOP-->

                <!-- MAIN CONTENT-->  
                <%
                    // List<ProductoPedidoVenta> pedidosLista = (List<ProductoPedidoVenta>)request.getSession().getAttribute("pedidoslista");
                    //for ( ProductoPedidoVenta p:pedidosLista){
                    //System.out.println(p.toString());
                    //}
%>
                <div class="main-content">
                    <div class="section_content section_content--p30">
                        <div class="container-fluid">
                            <div class="row">
                                <div class="col" >
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-sm">
                                                <h1>Reporte de Ventas por Departamento</h1>
                                            </div>
                                        </div><br>
                                        <div class="col">
                                            <div class="au-card au-card--bg-orange au-card-top-countries m-b-30">
                                                <div class="au-card-inner">
                                                    <div class="table-responsive">
                                                        <table class="table table-top-countries">
                                                            <tbody>
                                                                <tr>
                                                                    <td>Suelas PVC</td>
                                                                    <td class="text-right">$<%=request.getSession().getAttribute("total2")%></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Suelas Expanso</td>
                                                                    <td class="text-right">$<%=request.getSession().getAttribute("total3")%></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Tiras</td>
                                                                    <td class="text-right">$<%=request.getSession().getAttribute("total4")%></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Plantillas</td>
                                                                    <td class="text-right">$<%=request.getSession().getAttribute("total1")%></td>
                                                                </tr>
                                                                <tr>
                                                                    <td>Salpas</td>
                                                                    <td class="text-right">$<%=request.getSession().getAttribute("total5")%></td>
                                                                </tr>                                                                
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="col" >
                                    <div class="container">
                                        <div class="row">
                                            <div class="col-sm">
                                                <h1>Reporte de Ventas General</h1>
                                            </div>
                                        </div><br>
                                        <div class="col">
                                            <div class="au-card au-card--bg-orange au-card-top-countries m-b-30">
                                                <div class="au-card-inner">
                                                    <div class="table-responsive">
                                                        <table class="table table-top-countries">
                                                            <tbody>                                                                
                                                                <tr>
                                                                    <td>Total de ventas Generales</td>
                                                                    <td class="text-right">$<%=(Double) request.getSession().getAttribute("totalventas")%></td>
                                                                </tr>                                                                
                                                            </tbody>
                                                        </table>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
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
