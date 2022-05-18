<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<c:if test="${not empty car}">
    <script>
        window.onload = function () {
            $("#carmodal").modal("show");
        };
    </script>
</c:if>
<c:if test="${not empty order}">
    <script>
        window.onload = function () {
            $("#ordermodal").modal("show");
        };
    </script>
</c:if>
<!-- <div id="loading">
</div>
<script>
  $(window).on('load', function () {
    $('#loading').hide();
    $("div#main").removeClass("hidden");
  }) 
</script> -->  <!--set div class hidden -->

<nav class="navbar navbar-expand-md navbar-dark">
	<div class="container">
		<a href="#" class="navbar-brand"><img style="height: 50px" src="resources/assets/images/logo.png">  IDRISCAR</a>
		
		<button type="button" class="navbar-toggler collapsed" data-toggle="collapse" data-target="#main-nav">
			<span class="menu-icon-bar"></span>
			<span class="menu-icon-bar"></span>
			<span class="menu-icon-bar"></span>
		</button>
		
		<div id="main-nav" class="collapse navbar-collapse">
			<ul class="navbar-nav ml-auto">
				<li><a id="hometag" href="#" class="nav-item nav-link">Home</a></li>
				<li><a id="footertag" class="nav-item nav-link">About Us</a></li>
				<li><a id="cartag" href="cars/" class="nav-item nav-link">Cars</a></li>
				<li class="dropdown">
					<a id="exploretag" href="#" class="nav-item nav-link" data-toggle="dropdown">Explore</a>
					<div class="dropdown-menu">
						<a id="videotag" href="#videos" class="dropdown-item">Video</a>
						<a id="typetag" href="#types" class="dropdown-item">Type</a>
						<a id="brandtag" href="#brands" class="dropdown-item">Brand</a>
					</div>
				</li>
				<li><a id="ordertag" href="orders/" class="nav-item nav-link">Orders</a></li>
				<c:if test="${logged=='2'}">
				<li><a id="admin" href="admin/" class="nav-item nav-link">Administrator</a></li>
				</c:if>
				<li><a  href="login.htm" class="nav-item nav-link"><span id="logintag">Login</span></a></li>
			</ul>
		</div>
	</div>
</nav>
