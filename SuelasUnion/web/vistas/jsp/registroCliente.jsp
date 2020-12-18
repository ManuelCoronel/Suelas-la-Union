<%-- 
    Document   : registroCliente
    Created on : 14-dic-2020, 15:25:23
    Author     : ..
--%>

<%@page import="java.util.List"%>
<%@page import="DTO.TipoCliente"%>
<%@page import="DTO.TipoCliente"%>
<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <!-- Required meta tags-->
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="au theme template">
        <meta name="author" content="Hau Nguyen">
        <meta name="keywords" content="au theme template">

        <!-- Title Page-->
        <title>Login</title>

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
            <div class="page-content--bge12">
                <div class="container">
                    <div class="login-wrap">
                        <div class="login-content">
                            <label style="display: block; text-align: center;"><h3>Registro</h3></label> <br>
                            <div class="login-logo">
                                <a href="#">
                                    <img src="../images/icon/logo.png" alt="SuperSuelas La union" width="200px" height="100px">
                                </a>
                            </div>
                            <div class="login-form">
                                <form action="../../loginCliente"  method="GET">  

                                    <div class="grupo">
                                        <p>Cédula</p>
                                        <input class="form-control" type="text" name="txtCedula" required><span class="barra"></span>                                       
                                    </div><br>

                                    <div class="grupo">
                                        <p>Télefono</p>
                                        <input class="form-control" type="text" name="txtTelefono" required><span class="barra"></span>
                                    </div><br>

                                    <div class="grupo">
                                        <p>Dirección</p>
                                        <input class="form-control" type="text" name="txtDireccion" required><span class="barra"></span>
                                    </div><br>

                                    <div class="grupo">
                                        <p>Nombre</p> 
                                        <input class="form-control" type="text" name="txtNombre" required><span class="barra"></span>
                                    </div><br>

                                    <div class="grupo">
                                        <p>Correo</p>
                                        <input class="form-control" type="text" name="txtCorreo" required><span class="barra"></span>
                                    </div><br>

                                    <div class="grupo">
                                        <p>Fecha Nacimiento</p>
                                        <input class="form-control" type="date" name="txtFechaNacimiento" required><span class="barra"></span>
                                    </div><br>

                                    <p>Tipo cliente</p>
                                    <select name="opciones" class="form-control">
                                        <%
                                            List<TipoCliente> list = (List<TipoCliente>) request.getSession().getAttribute("tiposDeClientes");
                                            for (TipoCliente dto : list) {
                                        %>
                                        <option value="<%= dto.getIdTipoCliente()%>"><%= dto.getTipo()%></option>
                                        <% }%>
                                    </select><br>
  
                                    <input  type="submit"  class="au-btn au-btn--block au-btn--green m-b-20"  name="accion" value="Guardar">
                                    <a class="au-btn au-btn--block au-btn--blue m-b-20 text-center"  href="loginCliente.jsp">Atras</a>

                                </form>

                                <%
                                    Object sesion = request.getSession().getAttribute("INVALIDO");
                                    if (sesion != null) {
                                        out.print("<script>"
                                                + "alert('Usuario o Contrasena Incorrectas')"
                                                + "</script>");
                                    }

                                %>

                            </div>
                        </div>
                    </div>
                </div>
            </div>

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
<!-- end document-->
