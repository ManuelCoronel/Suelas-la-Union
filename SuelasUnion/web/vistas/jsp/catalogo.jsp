<%-- 
    Document   : catalogo
    Created on : 13-dic-2020, 15:56:40
    Author     : ..
--%>
<%@page import="DTO.Salpa"%>
<%@page import="DTO.Plantilla"%>
<%@page import="DTO.Tira"%>
<%@page import="DTO.Suela"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="DTO.Cliente"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Super Suelas La Union</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link rel="icon" type="image/x-icon" href="../images/icon/logo.ico">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,600,600i,700,700i,900" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="../Mamba/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="../Mamba/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
        <link href="../Mamba/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="../Mamba/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="../Mamba/assets/vendor/venobox/venobox.css" rel="stylesheet">
        <link href="../Mamba/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="../Mamba/assets/vendor/aos/aos.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="../Mamba/assets/css/style.css" rel="stylesheet">

        <!-- =======================================================
        * Template Name: Mamba - v2.5.0
        * Template URL: https://bootstrapmade.com/mamba-one-page-bootstrap-template-free/
        * Author: BootstrapMade.com
        * License: https://bootstrapmade.com/license/
        ======================================================== -->
    </head>

    <body>
        <!-- ======= Top Bar ======= -->
        <section id="topbar" class="d-none d-lg-block">
            <div class="container clearfix">
                <div class="contact-info float-left">
                    <i class="icofont-envelope"></i><a href="mailto:supersuelaslaunion@gmail.com">supersuelaslaunion@gmail.com</a>
                    <i class="icofont-phone"></i> +57 311 4586406
                </div>
                <div class="social-links float-right">
                    <a href="https://www.facebook.com/supersuelaslaunion/" class="facebook"><i class="icofont-facebook"></i></a>
                    <a href="#" class="instagram"><i class="icofont-instagram"></i></a>
                </div>
            </div>
        </section>

        <!-- ======= Header ======= -->
        <%Cliente user = (Cliente) request.getSession().getAttribute("personalActual");%>
        <header id="header">
            <div class="container">
                <% if (user != null) {%>
                <div class="logo float-left">
                    <h1 class="text-light"><a href="indexCliente.jsp"><span>Super Suelas La Union</span></a></h1>
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
                </div>
                <%} else {%>
                <div class="logo float-left">
                    <h1 class="text-light"><a href="../../index.jsp"><span>Super Suelas La Union</span></a></h1>
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
                </div>
                <%}%>
                <nav class="nav-menu float-right d-none d-lg-block">
                    <ul>
                        <% if (user != null) {%>
                        <li class="active"><a href="indexCliente.jsp">Inicio</a></li>
                        <li><a href="indexCliente.jsp#about">Sobre Nosotros</a></li>
                        <li><a href="indexCliente.jsp#portfolio">Portafolio</a></li>
                        <li><a href="../../loginCliente?accion=catalogo">Catálogo</a></li>
                        <li><a href="indexCliente.jsp#contact">Contáctanos</a></li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <%=user.getNombre().split(" ")[0] %> 
                            </a>
                            <div class="dropdown-menu" aria-labelledby="navbarDropdown"></br>
                                <h4 class="text-center font-weight-bold text-capitalize text-black">
                                    <%= user.getNombre()%>
                                </h4>
                                <h6 class="text-center font-weight text-capitalize text-black">
                                    <%= user.getTipoClienteIdTipoCliente().getTipo()%> 
                                </h6></br>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="#catalogo">Catálogo</a>
                                <a class="dropdown-item" href="../../ControladorCompras?accion=listar">Mis Compras</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="../../loginCliente?accion=salir">Salir</a>
                            </div>
                        </li>
                        <li>
                            <a href="carritoCompras.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                                </svg> (<label><%=(request.getSession().getAttribute("productosEnCarrito") != null) ? request.getSession().getAttribute("productosEnCarrito") : 0%></label>) Carrito</a>
                        </li>
                        <%} else {%>
                        <li class="active"><a href="../../index.jsp">Inicio</a></li>
                        <li><a href="../../index.jsp#about">Sobre Nosotros</a></li>
                        <li><a href="../../index.jsp#portfolio">Portafolio</a></li>
                        <li><a href="#catalogo">Catálogo</a></li>
                        <li><a href="../../index.jsp#contact">Contáctanos</a></li>
                        <li><a href="loginCliente.jsp">Login</a></li>
                        <li>
                            <a href="carritoCompras.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                                </svg> (<label><%=(request.getSession().getAttribute("productosEnCarrito") != null) ? request.getSession().getAttribute("productosEnCarrito") : 0%></label>) Carrito</a>
                        </li>
                        <%}%>
                        <!-- colocar ruta del login -->
                    </ul>
                </nav><!-- .nav-menu -->

            </div>
        </header><!-- End Header -->


        <!-- ======= Our Portfolio Section ======= -->
        <section id="catalogo" class="portfolio section-bg">
            <div class="container" data-aos="fade-up" data-aos-delay="100">
                <div class="section-title">
                    <h2>Nuestro Catálogo</h2>
                </div>
                <form action="catalogo.jsp"  method="POST">
                    <div >
                        <!-- filtros -->
                        <div class="row">
                            <div class="col offset-8 col-sm-2 mt-4">
                                <select name="opciones" class="form-control">
                                    <option  value="0">Departamento</option>
                                    <option  value="1">Plantillas</option>
                                    <option  value="2">Suela PVC</option>
                                    <option  value="3">Suela Expanso</option>
                                    <option  value="4">Tiras</option>
                                    <option  value="5">Salpas</option>
                                </select>
                            </div>
                            <div class="col col-sm-2">
                                <input class="btn btn-primary" type="submit" name="accion" value="Buscar"  style="margin-top: 25px;">
                            </div>
                        </div>
                        <!-- filtros -->
                    </div>
                </form>
                <div class="col-md-12 blog-main">
                    <%
                        String filtro = request.getParameter("opciones");
                        if (filtro == null) {
                            filtro = "0";
                        }
                        if (filtro.equalsIgnoreCase("0") || filtro.equalsIgnoreCase("2")) { %>     
                    <h3 class="pb-4 mb-4 font-italic border-bottom text-center">
                        Suelas PVC
                    </h3>
                </div>
                <div class="row row-cols-1 row-cols-md-3">
                    <%
                        List<DTO.Suela> list = (List<Suela>) request.getSession().getAttribute("suelas");
                        for (Suela dto : list) {
                            if (dto.getTipoSuela().equalsIgnoreCase("2")) {
                    %>
                    <div class="col mb-4">
                        <div class="card h-100">
                            <div class="row card-body">
                                <div class="col">
                                    <h2 class="card-title"><%= dto.getModelo()%></h2>
                                    <h4>Talla: <%= dto.getTalla()%></h4>
                                    <h4>Color: <%= dto.getColorId().getColor()%></h4>
                                </div>
                            </div>
                            <div class="portfolio-wrap">
                                <img src="../Mamba/assets/img/portfolio/portfolio-1.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <div class="portfolio-links">
                                        <a href="../Mamba/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="row card-body">
                                <div class="col">
                                    <h3 class="text-center ">$<%=dto.getProducto().getPrecio()%></h3>
                                    <form action="../../ControladorCarritoCompras" method="GET">
                                        <div>
                                            <h6>Cantidad: <input class="form-control" type="number" name="cantidad" value="1" min="1" max="<%=dto.getProducto().getCantidadDisponible()%>"></h6>
                                        </div>
                                        <input type="hidden" name="producto" value="<%=dto.getProductoId()%>">
                                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="+Agregar" name="accion">
                                    </form>                           
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}
                        }%>
                </div></br>
                <%}%>
                <%    if (filtro == null) {
                        filtro = "0";
                    }
                    if (filtro.equalsIgnoreCase("0") || filtro.equalsIgnoreCase("3")) {%>
                <div class="col-md-12 blog-main">
                    <h3 class="pb-4 mb-4 font-italic border-bottom text-center">
                        Suelas Expanso
                    </h3>
                </div>
                <div class="row row-cols-1 row-cols-md-3">
                    <%

                        List<DTO.Suela> list3 = (List<Suela>) request.getSession().getAttribute("suelas");
                        for (Suela dto : list3) {
                            if (dto.getTipoSuela().equalsIgnoreCase("3")) {
                    %>
                    <div class="col mb-4">
                        <div class="card h-100">
                            <div class="row card-body">
                                <div class="col">
                                    <h2 class="card-title"><%= dto.getModelo()%></h2>
                                    <h4>Talla: <%= dto.getTalla()%></h4>
                                    <h4>Color: <%= dto.getColorId().getColor()%></h4>
                                </div>
                            </div>
                            <div class="portfolio-wrap">
                                <img src="../Mamba/assets/img/portfolio/portfolio-1.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <div class="portfolio-links">
                                        <a href="../Mamba/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="row card-body">
                                <div class="col">
                                    <h3 class="text-center ">$<%=dto.getProducto().getPrecio()%></h3>
                                    <form action="../../ControladorCarritoCompras" method="GET">
                                        <div>
                                            <h6>Cantidad: <input class="form-control" type="number" name="cantidad" value="1" min="1" max="<%=dto.getProducto().getCantidadDisponible()%>"></h6>
                                        </div>
                                        <input type="hidden" name="producto" value="<%=dto.getProductoId()%>">
                                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="+Agregar" name="accion">
                                    </form>                           
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}
                            }
                        }%>
                    <%    if (filtro == null) {
                            filtro = "0";
                        }
                        if (filtro.equalsIgnoreCase("0") || filtro.equalsIgnoreCase("4")) {%>
                </div></br>
                <div class="col-md-12 blog-main">
                    <h3 class="pb-4 mb-4 font-italic border-bottom text-center">
                        Tiras
                    </h3>
                </div>
                <div class="row row-cols-1 row-cols-md-3">
                    <%
                        List<DTO.Tira> tiras = (List<Tira>) request.getSession().getAttribute("tiras");
                        for (Tira dto : tiras) {
                    %>
                    <div class="col mb-4">
                        <div class="card h-100">
                            <div class="row card-body">
                                <div class="col">
                                    <h2 class="card-title"><%= dto.getModelo()%></h2>
                                    <h4>Anchura(mm): <%= dto.getAnchura()%></h4>
                                    <h4>Color: <%= dto.getColorId().getColor()%></h4>
                                </div>
                            </div>
                            <div class="portfolio-wrap">
                                <img src="../Mamba/assets/img/portfolio/portfolio-1.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <div class="portfolio-links">
                                        <a href="../Mamba/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="row card-body">
                                <div class="col">
                                    <h3 class="text-center ">$<%= dto.getProducto().getPrecio()%></h3>
                                    <form action="../../ControladorCarritoCompras" method="GET">
                                        <div>
                                            <h6>Cantidad: <input class="form-control" type="number" name="cantidad" value="1" min="1" max="<%=dto.getProducto().getCantidadDisponible()%>"></h6>
                                        </div>
                                        <input type="hidden" name="producto" value="<%=dto.getProductoId()%>">
                                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="+Agregar" name="accion">
                                    </form> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}
                        }%>
                </div></br>
                <%
                    if (filtro.equalsIgnoreCase("0") || filtro.equalsIgnoreCase("1")) {%>
                <div class="col-md-12 blog-main">
                    <h3 class="pb-4 mb-4 font-italic border-bottom text-center">
                        Plantillas
                    </h3>
                </div>
                <div class="row row-cols-1 row-cols-md-3">
                    <%
                        List<DTO.Plantilla> plantillas = (List< Plantilla>) request.getSession().getAttribute("plantillas");
                        for (Plantilla dto : plantillas) {
                    %>
                    <div class="col mb-4">
                        <div class="card h-100">
                            <div class="row card-body">
                                <div class="col">
                                    <h2 class="card-title"><%= dto.getModelo()%></h2>
                                    <h4>Talla: <%= dto.getTalla()%></h4>
                                </div>
                            </div>
                            <div class="portfolio-wrap">
                                <img src="../Mamba/assets/img/portfolio/portfolio-1.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <div class="portfolio-links">
                                        <a href="../Mamba/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="row card-body">
                                <div class="col">
                                    <h3 class="text-center ">$<%= dto.getProducto().getPrecio()%></h3>
                                    <form action="../../ControladorCarritoCompras" method="GET">
                                        <div>
                                            <h6>Cantidad: <input class="form-control" type="number" name="cantidad" value="1" min="1" max="<%=dto.getProducto().getCantidadDisponible()%>"></h6>
                                        </div>
                                        <input type="hidden" name="producto" value="<%=dto.getProductoId()%>">
                                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="+Agregar" name="accion">
                                    </form> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}
                    %>
                </div></br><%}%>
                <%
                    if (filtro.equalsIgnoreCase("0") || filtro.equalsIgnoreCase("5")) {%>

                <div class="col-md-12 blog-main">
                    <h3 class="pb-4 mb-4 font-italic border-bottom text-center">
                        Salpas
                    </h3>
                </div>
                <div class="row row-cols-1 row-cols-md-3">
                    <%
                        List<DTO.Salpa> salpas = (List<Salpa>) request.getSession().getAttribute("salpas");
                        for (Salpa dto : salpas) {
                    %>
                    <div class="col mb-4">
                        <div class="card h-100">
                            <div class="row card-body">
                                <div class="col">
                                    <h2 class="card-title">Salpa</h2>
                                </div>
                            </div>
                            <div class="portfolio-wrap">
                                <img src="../Mamba/assets/img/portfolio/portfolio-1.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <div class="portfolio-links">
                                        <a href="../Mamba/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                            <div class="row card-body">
                                <div class="col">
                                    <h3 class="text-center ">$<%= dto.getProducto().getPrecio()%></h3>
                                    <form action="../../ControladorCarritoCompras" method="GET">
                                        <div>
                                            <h6>Cantidad: <input class="form-control" type="number" name="cantidad" value="1" min="1" max="<%=dto.getProducto().getCantidadDisponible()%>"></h6>
                                        </div>
                                        <input type="hidden" name="producto" value="<%=dto.getProductoId()%>">
                                        <input class="btn btn-primary btn-lg btn-block" type="submit" value="+Agregar" name="accion">
                                    </form> 
                                </div>
                            </div>
                        </div>
                    </div>
                    <%}
                        }%>
                </div>
        </section><!-- End Our Portfolio Section -->            
    </main><!-- End #main -->

    <!-- ======= Footer ======= -->
    <!-- ======= Footer ======= -->
    <footer id="footer">
        <div class="footer-top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-3 col-md-6 footer-info">
                        <h3>Super Suelas La Union</h3>
                        <p>
                            Cl. 10 #9-90, <br>
                            Norte de Santander Cúcuta, Colombia<br><br>
                            <strong>Telefono:</strong> +57 321 2030377<br>
                            <strong>Email:</strong> supersuelaslaunion@gmail.com<br>
                        </p>
                        <div class="social-links mt-3">
                            <a href="https://www.facebook.com/supersuelaslaunion" class="facebook"><i class="bx bxl-facebook"></i></a>
                            <a href="#" class="instagram"><i class="bx bxl-instagram"></i></a>
                        </div>
                    </div>
                    <div class="col-lg-2 col-md-6 footer-links">
                        <h4>Useful Links</h4>
                        <ul>
                            <li><i class="bx bx-chevron-right"></i> <a href="#">Inicio</a></li>
                            <li><i class="bx bx-chevron-right"></i> <a href="#">Sobre Nosotros</a></li>
                        </ul>
                    </div>
                    <div class="col-lg-1 col-md-1">
                    </div>
                    <div class="col-lg-6 col-md-6 footer-newsletter">
                        <h4>Newsletter</h4>
                        <p>Suscribete a nuestro Newsletter</p>
                        <form action="" method="post">
                            <input type="email" name="email"><input type="submit" value="Subscribe">
                        </form>

                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="copyright">
                &copy; Copyright <strong><span>Super Suelas La Union</span></strong>. All Rights Reserved
            </div>
            <div class="credits">
                <!-- All the links in the footer should remain intact. -->
                <!-- You can delete the links only if you purchased the pro version. -->
                <!-- Licensing information: https://bootstrapmade.com/license/ -->
                <!-- Purchase the pro version with working PHP/AJAX contact form: https://bootstrapmade.com/mamba-one-page-bootstrap-template-free/ -->
                Designed by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </div>
    </footer><!-- End Footer -->

    <a href="#" class="back-to-top"><i class="icofont-simple-up"></i></a>

    <!-- Vendor JS Files -->
    <script src="../Mamba/assets/vendor/jquery/jquery.min.js"></script>
    <script src="../Mamba/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
    <script src="../Mamba/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
    <script src="../Mamba/assets/vendor/php-email-form/validate.js"></script>
    <script src="../Mamba/assets/vendor/jquery-sticky/jquery.sticky.js"></script>
    <script src="../Mamba/assets/vendor/venobox/venobox.min.js"></script>
    <script src="../Mamba/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
    <script src="../Mamba/assets/vendor/counterup/counterup.min.js"></script>
    <script src="../Mamba/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
    <script src="../Mamba/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
    <script src="../Mamba/assets/vendor/aos/aos.js"></script>

    <!-- Template Main JS File -->
    <script src="../Mamba/assets/js/main.js"></script>
</body>
</html>
