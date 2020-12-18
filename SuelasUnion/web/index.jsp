<%-- 
    Document   : index
    Created on : 13-dic-2020, 14:14:38
    Author     : ..
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="utf-8">
        <meta content="width=device-width, initial-scale=1.0" name="viewport">

        <title>Súper Suelas La Unión</title>
        <meta content="" name="description">
        <meta content="" name="keywords">

        <!-- Favicons -->
        <link rel="icon" type="image/x-icon" href="vistas/images/icon/logo.ico">

        <!-- Google Fonts -->
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,300i,400,400i,600,600i,700,700i|Raleway:300,300i,400,400i,600,600i,700,700i,900" rel="stylesheet">

        <!-- Vendor CSS Files -->
        <link href="vistas/Mamba/assets/vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
        <link href="vistas/Mamba/assets/vendor/icofont/icofont.min.css" rel="stylesheet">
        <link href="vistas/Mamba/assets/vendor/boxicons/css/boxicons.min.css" rel="stylesheet">
        <link href="vistas/Mamba/assets/vendor/animate.css/animate.min.css" rel="stylesheet">
        <link href="vistas/Mamba/assets/vendor/venobox/venobox.css" rel="stylesheet">
        <link href="vistas/Mamba/assets/vendor/owl.carousel/assets/owl.carousel.min.css" rel="stylesheet">
        <link href="vistas/Mamba/assets/vendor/aos/aos.css" rel="stylesheet">

        <!-- Template Main CSS File -->
        <link href="vistas/Mamba/assets/css/style.css" rel="stylesheet">

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
                    <a href="vistas/jsp/login.jsp" class="">Admin</a>
                </div>
            </div>
        </section>

        <!-- ======= Header ======= -->
        <header id="header">
            <div class="container">
                <div class="logo float-left">
                    <h1 class="text-light"><a href="index.jsp"><span>Súper Suelas La Unión</span></a></h1>
                    <!-- Uncomment below if you prefer to use an image logo -->
                    <!-- <a href="index.html"><img src="assets/img/logo.png" alt="" class="img-fluid"></a>-->
                </div>
                <nav class="nav-menu float-right d-none d-lg-block">
                    <ul>
                        <li class="active"><a href="index.jsp">Inicio</a></li>
                        <li><a href="#about">Sobre Nosotros</a></li>
                        <li><a href="#portfolio">Portafolio</a></li>
                        <li><a href="loginCliente?accion=catalogo">Catálogo</a></li>
                        <li><a href="#contact">Contáctanos</a></li>
                        <li><a href="vistas/jsp/loginCliente.jsp">Login</a></li>
                        <li>
                            <a href="vistas/jsp/carritoCompras.jsp"><svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart4" viewBox="0 0 16 16">
                                <path fill-rule="evenodd" d="M0 2.5A.5.5 0 0 1 .5 2H2a.5.5 0 0 1 .485.379L2.89 4H14.5a.5.5 0 0 1 .485.621l-1.5 6A.5.5 0 0 1 13 11H4a.5.5 0 0 1-.485-.379L1.61 3H.5a.5.5 0 0 1-.5-.5zM3.14 5l.5 2H5V5H3.14zM6 5v2h2V5H6zm3 0v2h2V5H9zm3 0v2h1.36l.5-2H12zm1.11 3H12v2h.61l.5-2zM11 8H9v2h2V8zM8 8H6v2h2V8zM5 8H3.89l.5 2H5V8zm0 5a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0zm9-1a1 1 0 1 0 0 2 1 1 0 0 0 0-2zm-2 1a2 2 0 1 1 4 0 2 2 0 0 1-4 0z"/>
                                </svg> (<label><%=(request.getSession().getAttribute("productosEnCarrito") != null) ? request.getSession().getAttribute("productosEnCarrito") : 0%></label>) Carrito</a>
                        </li>
                        <!-- colocar ruta del login -->
                    </ul>
                </nav><!-- .nav-menu -->

            </div>
        </header><!-- End Header -->

        <!-- ======= Hero Section ======= -->
        <section id="hero">
            <div class="hero-container">
                <div id="heroCarousel" class="carousel slide carousel-fade" data-ride="carousel">

                    <ol class="carousel-indicators" id="hero-carousel-indicators"></ol>

                    <div class="carousel-inner" role="listbox">

                        <!-- Slide 1 -->
                        <div class="carousel-item active" style="background-image: url('vistas/Mamba/assets/img/slide/slide-1.png');">
                            <div class="carousel-container">
                                <div class="carousel-content container">
                                    <h2 class="animate__animated animate__fadeInDown">Bienvenido</h2>
                                    <h2 class="animate__animated animate__fadeInDown"><span>Súper Suelas La Unión</span></h2>
                                    <p class="animate__animated animate__fadeInUp">Súper Suelas la Unión pone a su disposición una amplia gama de novedosos productos donde descubrirá las tendencias en diseño, nuevos materiales y excelentes acabados de nuestras colecciones</p>
                                    <a href="#about" class="btn-get-started animate__animated animate__fadeInUp scrollto">Leer Más</a>
                                </div>
                            </div>
                        </div>

                        <!-- Slide 2 -->
                        <div class="carousel-item" style="background-image: url('vistas/Mamba/assets/img/slide/slide-2.jpg');">
                            <div class="carousel-container">
                                <div class="carousel-content container">
                                    <h2 class="animate__animated animate__fadeInDown">Fabricación y comercialización de suelas para calzado de calidad</h2>
                                    <a href="#about" class="btn-get-started animate__animated animate__fadeInUp scrollto">Leer Más</a>
                                </div>
                            </div>
                        </div>

                        <!-- Slide 3 -->
                        <div class="carousel-item" style="background-image: url('vistas/Mamba/assets/img/slide/slide-3.jpg');">
                            <div class="carousel-container">
                                <div class="carousel-content container">
                                    <h2 class="animate__animated animate__fadeInDown">Innovación</h2>
                                    <p class="animate__animated animate__fadeInUp">La innovación es uno de los pilares básicos de nuestra filosofía ya que tenemos como objetivo la excelencia de nuestros productos.</p>
                                    <a href="#about" class="btn-get-started animate__animated animate__fadeInUp scrollto">Leer Más</a>
                                </div>
                            </div>
                        </div>

                    </div>

                    <a class="carousel-control-prev" href="#heroCarousel" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon icofont-rounded-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#heroCarousel" role="button" data-slide="next">
                        <span class="carousel-control-next-icon icofont-rounded-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>

                </div>
            </div>
        </section><!-- End Hero -->

        <main id="main">

            <!-- ======= About Us Section ======= -->
            <section id="about" class="about">
                <div class="container">

                    <div class="row no-gutters">
                        <div class="col-lg-6 video-box">
                            <img src="vistas/Mamba/assets/img/about.jpg" class="img-fluid" alt="">
                        </div>

                        <div class="col-lg-6 d-flex flex-column justify-content-center about-content">

                            <div class="section-title">
                                <h2>Sobre Nosotros</h2>
                                <p style="text-align:justify">Nuestro principal objetivo es incentivar y apoyar la industria del calzado en Colombia y Latinoamérica. Ofrecemos suelas de calidad superior, livianas y resistentes al mejor precio del mercado. ¡Compruébalo
                                    tú mismo! Trabajamos solo
                                    con materiales de alta calidad siguiendo las últimas tendencias del mercado a nivel nacional e internacional.
                                </p>
                            </div>

                            <div class="icon-box" data-aos="fade-up" data-aos-delay="100">
                                <div class="icon"><i class="bx bxs-star"></i></div>
                                <h4 class="title"><a href="">Mision</a></h4>
                                <p class="description" style="text-align:justify">Nuestra misión es crear confianza con los diferentes clientes que requieren variedad en insumos para el calzado de niña y dama de todas las edades, ofreciendo así productos de calidad, a
                                    precios ecuánimes, con el mejor servicio de atención por parte de sus empleados y dueños, siempre capacitados a dar a conocer los productos de moda, permitiendo así seguir creciendo en el tiempo con el fin de mantener y reafirmar la
                                    permanencia en el mercado cucuteño.</p>
                            </div>

                            <div class="icon-box" data-aos="fade-up" data-aos-delay="100">
                                <div class="icon"><i class="bx bxs-star"></i></div>
                                <h4 class="title"><a href="">Vision</a></h4>
                                <p class="description" style="text-align:justify">Súper suelas la unión será en el futuro una empresa consolidada con el personal capacitado, con una organización fortalecida en todas sus áreas, que comercializará en Cúcuta
                                    y a nivel nacional sus productos fabricados dentro de nuestro nicho de mercado y a su vez
                                    contará con nuevos puntos ubicados estratégicamente, cumpliento así con el pleno bienestar
                                    de sus clientes, bajo una responsabilidad, respeto y cumplimiento hacia sus empleados y proveedores.</p>
                            </div>

                        </div>
                    </div>

                </div>
            </section><!-- End About Us Section -->

            <!-- ======= Counts Section ======= -->


            <!-- ======= Our Portfolio Section ======= -->
            <section id="portfolio" class="portfolio section-bg">
                <div class="container" data-aos="fade-up" data-aos-delay="100">

                    <div class="section-title">
                        <h2>Nuestro Portafolio</h2>
                        <p>Le invitamos a que recorra esta web y descubra nuestras últimas innovaciones en el campo de las suelas.</p>
                    </div>

                    <div class="row">
                        <div class="col-lg-12">
                            <ul id="portfolio-flters">
                                <li data-filter="*" class="filter-active">Suelas</li>
                            </ul>
                        </div>
                    </div>

                    <div class="row portfolio-container">

                        <div class="col-lg-4 col-md-6 portfolio-item filter-app">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-1.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Tuty</h4>
                                    <p>21-23</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-1.jpg" data-gall="portfolioGallery" class="venobox" title="App 1"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-web">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-2.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Estefy</h4>
                                    <p>34-40</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-2.jpg" data-gall="portfolioGallery" class="venobox" title="Web 3"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-app">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-3.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Trenza</h4>
                                    <p>34-40</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-3.jpg" data-gall="portfolioGallery" class="venobox" title="App 2"><i class="icofont-eye"></i></a>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-card">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-4.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Yeni</h4>
                                    <p>34-40</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-4.jpg" data-gall="portfolioGallery" class="venobox" title="Card 2"><i class="icofont-eye"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-web">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-5.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Corbatin</h4>
                                    <p>21-33</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-5.jpg" data-gall="portfolioGallery" class="venobox" title="Web 2"><i class="icofont-eye"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-app">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-6.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Salomé</h4>
                                    <p>34-40</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-6.jpg" data-gall="portfolioGallery" class="venobox" title="App 3"><i class="icofont-eye"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-card">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-7.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Sol</h4>
                                    <p>34-40</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-7.jpg" data-gall="portfolioGallery" class="venobox" title="Card 1"><i class="icofont-eye"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-card">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-8.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Liz</h4>
                                    <p>34-40</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-8.jpg" data-gall="portfolioGallery" class="venobox" title="Card 3"><i class="icofont-eye"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-lg-4 col-md-6 portfolio-item filter-web">
                            <div class="portfolio-wrap">
                                <img src="vistas/Mamba/assets/img/portfolio/portfolio-9.jpg" class="img-fluid" alt="">
                                <div class="portfolio-info">
                                    <h4>Tuty</h4>
                                    <p>21-33</p>
                                    <div class="portfolio-links">
                                        <a href="vistas/Mamba/assets/img/portfolio/portfolio-9.jpg" data-gall="portfolioGallery" class="venobox" title="Web 3"><i class="icofont-eye"></i></a>

                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>

                </div>
            </section><!-- End Our Portfolio Section -->
            <div class="section-title">
                <p>Puedes encontrar esto y mucho más en Super Suelas La Union.</p>
            </div>



            <!-- ======= Contact Us Section ======= -->
            <section id="contact" class="contact">
                <div class="container">

                    <div class="section-title">
                        <h2>Contáctanos</h2>
                    </div>

                    <div class="row">

                        <div class="col-lg-6 d-flex align-items-stretch" data-aos="fade-up">
                            <div class="info-box">
                                <i class="bx bx-map"></i>
                                <h3>Nuestra Dirección</h3>
                                <p>Cl. 10 #9-90, Cúcuta, Norte de Santander Cúcuta, Colombia</p>
                            </div>
                        </div>

                        <div class="col-lg-3 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="100">
                            <div class="info-box">
                                <i class="bx bx-envelope"></i>
                                <h3>Escríbenos</h3>
                                <p>supersuelaslaunion@gmail.com</p>
                            </div>
                        </div>

                        <div class="col-lg-3 d-flex align-items-stretch" data-aos="fade-up" data-aos-delay="200">
                            <div class="info-box ">
                                <i class="bx bx-phone-call"></i>
                                <h3>Llámanos</h3>
                                <p>+57 321 2030377</p>
                            </div>
                        </div>

                        <div class="col-lg-12" data-aos="fade-up" data-aos-delay="300">
                            <form action="vistas/Mamba/forms/contact.php" method="post" role="form" class="php-email-form">
                                <div class="form-row">
                                    <div class="col-lg-6 form-group">
                                        <input type="text" name="name" class="form-control" id="name" placeholder="Nombre" data-rule="minlen:4" data-msg="Please enter at least 4 chars" />
                                        <div class="validate"></div>
                                    </div>
                                    <div class="col-lg-6 form-group">
                                        <input type="email" class="form-control" name="email" id="email" placeholder="Correo Electronico" data-rule="email" data-msg="Please enter a valid email" />
                                        <div class="validate"></div>
                                    </div>
                                </div>
                                <div class="form-group">
                                    <input type="text" class="form-control" name="subject" id="subject" placeholder="Asunto" data-rule="minlen:4" data-msg="Please enter at least 8 chars of subject" />
                                    <div class="validate"></div>
                                </div>
                                <div class="form-group">
                                    <textarea class="form-control" name="message" rows="5" data-rule="required" data-msg="Please write something for us" placeholder="Mensaje"></textarea>
                                    <div class="validate"></div>
                                </div>
                                <div class="mb-3">
                                    <div class="loading">Cargando</div>
                                    <div class="error-message"></div>
                                    <div class="sent-message">Tu mensaje ha sido enviado. ¡Gracias!</div>
                                </div>
                                <div class="text-center"><button type="submit" disabled="true">Enviar Mensaje</button></div> 

                            </form>
                        </div>

                    </div>

                </div>
            </section><!-- End Contact Us Section -->

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
        <script src="vistas/Mamba/assets/vendor/jquery/jquery.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/jquery.easing/jquery.easing.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/php-email-form/validate.js"></script>
        <script src="vistas/Mamba/assets/vendor/jquery-sticky/jquery.sticky.js"></script>
        <script src="vistas/Mamba/assets/vendor/venobox/venobox.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/waypoints/jquery.waypoints.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/counterup/counterup.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/owl.carousel/owl.carousel.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/isotope-layout/isotope.pkgd.min.js"></script>
        <script src="vistas/Mamba/assets/vendor/aos/aos.js"></script>

        <!-- Template Main JS File -->
        <script src="vistas/Mamba/assets/js/main.js"></script>
    </body>
</html>

