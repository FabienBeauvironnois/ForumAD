<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri = "http://www.springframework.org/tags/form" prefix="f" %>
<!DOCTYPE html>
<html >

<head>

<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Grayscale - Start Bootstrap Theme</title>

<!-- Bootstrap Core CSS -->
<link
	href="<%=request.getContextPath()%>/resources/theme_bootstrap/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Custom CSS -->
<link
	href="<%=request.getContextPath()%>/resources/theme_bootstrap/css/grayscale.css"
	rel="stylesheet">

<!-- Custom Fonts -->
<link
	href="<%=request.getContextPath()%>/resources/theme_bootstrap/font-awesome/css/font-awesome.min.css"
	rel="stylesheet" type="text/css">
<link
	href="<%=request.getContextPath()%>/resources/theme_bootstrap/css/styleHome.css"
	rel="stylesheet" type="text/css">
<link
	href="http://fonts.googleapis.com/css?family=Lora:400,700,400italic,700italic"
	rel="stylesheet" type="text/css">
<link href="http://fonts.googleapis.com/css?family=Montserrat:400,700"
	rel="stylesheet" type="text/css">

<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

	<!-- Navigation -->
	<nav class="navbar navbar-custom navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse"
					data-target=".navbar-main-collapse">
					<i class="fa fa-bars"></i>
				</button>
				<a class="navbar-brand page-scroll" href="#page-top"> <i><img
						src="<%= request.getContextPath()%>/resources/theme_bootstrap/img/logointi.png"
						height="40px"></i> INTI FORMATION
				</a>
			</div>

			<!-- Collect the nav links, forms, and other content for toggling -->
			<div
				class="collapse navbar-collapse navbar-right navbar-main-collapse">
				<ul class="nav navbar-nav">
					<!-- Hidden li included to remove active class from about link when scrolled up past about section -->
					<li class="hidden"><a href="#page-top"></a></li>
					<li><a class="page-scroll" href="#about">Add Clients</a></li>
					<li><a class="page-scroll" href="#download">Add Employe</a></li>
					<li><a class="page-scroll" href="#contact">Add Compte</a></li>
					<li><a class="page-scroll" href="#">BNP</a></li>
					<li><a class="page-scroll" href="#">CIC</a></li>
					<li><a class="page-scroll" href="#">LCL</a></li>
				</ul>
			</div>
			<!-- /.navbar-collapse -->
		</div>
		<!-- /.container -->
	</nav>

	<!-- Intro Header -->
	<header class="intro">
		<div class="intro-body">
			<div class="container">
				<div class="row">
					<div class="col-md-8 col-md-offset-2">
						<h1 class="brand-heading"></h1>
						<p class="intro-text"></p>
						<a href="#about" class="btn btn-circle page-scroll"> <i
							class="fa fa-angle-double-down animated"></i>
						</a>
					</div>
				</div>
			</div>
		</div>
	</header>

	<!-- Add Clients Section -->
	<section id="about" class="container content-section text-center">
			<p>Renseignez tous les informations sur les employes</p>
		<div class="row ">
				<f:form modelAttribute="modelEmploye" action="addEmploye" method="post" class="form-horizontal">
					<div class="form-group">
						<label class="control-label col-sm-2">Nom Employe :</label>
						<div class="col-sm-4">
							<f:input type="text" path="nomEmploye" class="form-control" placeholder="Entrer nom"/>
						    <f:errors path="nomEmploye" cssClass="error"></f:errors>
						</div>
					</div>
					<!-- button -->
				        <div class="form-group">
					         <button class="btn btn-default btn-lg">Save</button>
					    </div> 
				</f:form>
			</div> 
	</section>

	<!-- Download Section -->
	<section id="download" class="content-section text-center">
		<div class="download-section">
			<div class="container">
				<div class="col-lg-8 col-lg-offset-2">
					<h2>Download Grayscale</h2>
					<p>You can download Grayscale for free on the preview page at
						Start Bootstrap.</p>
					<a href="http://startbootstrap.com/template-overviews/grayscale/"
						class="btn btn-default btn-lg">Visit Download Page</a>
				</div>
			</div>
		</div>
	</section>

	<!-- Contact Section -->
	<section id="contact" class="container content-section text-center">
		<div class="row">
			<div class="col-lg-8 col-lg-offset-2">
				<h2>Contact Start Bootstrap</h2>
				<p>Feel free to email us to provide some feedback on our
					templates, give us suggestions for new templates and themes, or to
					just say hello!</p>
				<p>
					<a href="mailto:feedback@startbootstrap.com">feedback@startbootstrap.com</a>
				</p>
				<ul class="list-inline banner-social-buttons">
					<li><a href="https://twitter.com/SBootstrap"
						class="btn btn-default btn-lg"><i class="fa fa-twitter fa-fw"></i>
							<span class="network-name">Twitter</span></a></li>
					<li><a
						href="https://github.com/IronSummitMedia/startbootstrap"
						class="btn btn-default btn-lg"><i class="fa fa-github fa-fw"></i>
							<span class="network-name">Github</span></a></li>
					<li><a href="https://plus.google.com/+Startbootstrap/posts"
						class="btn btn-default btn-lg"><i
							class="fa fa-google-plus fa-fw"></i> <span class="network-name">Google+</span></a>
					</li>
				</ul>
			</div>
		</div>
	</section>

	<!-- Map Section -->
	<div id="map"></div>

	<!-- Footer -->
	<footer>
		<div class="container text-center">
			<p>Copyright &copy; Adaming 2016</p>
		</div>
	</footer>

	<!-- jQuery -->
	<script
		src="<%=request.getContextPath()%>/resources/theme_bootstrap/js/jquery.js"></script>

	<!-- Bootstrap Core JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/theme_bootstrap/js/bootstrap.min.js"></script>

	<!-- Plugin JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/theme_bootstrap/js/jquery.easing.min.js"></script>

	<!-- Google Maps API Key - Use your own API key to enable the map feature. More information on the Google Maps API can be found at https://developers.google.com/maps/ -->
	<script type="text/javascript"
		src="https://maps.googleapis.com/maps/api/js?key=AIzaSyCRngKslUGJTlibkQ3FkfTxj3Xss1UlZDA&sensor=false"></script>

	<!-- Custom Theme JavaScript -->
	<script
		src="<%=request.getContextPath()%>/resources/theme_bootstrap/js/grayscale.js"></script>

</body>

</html>
