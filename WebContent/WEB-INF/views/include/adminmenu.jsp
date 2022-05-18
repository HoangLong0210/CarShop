<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<header class="header" id="header">
   <div class="header_toggle"> <i class='bx bx-menu' id="header-toggle"></i> </div>
   <div class="header_img"> <img src="https://i.pinimg.com/originals/9d/5f/14/9d5f14a67e7e5a00101a6baf8a59fe7e.jpg" alt=""> </div>
</header>

<h4>Main Components</h4>
<ul></ul>
<c:if test="${not empty message}">
	<div class="alert alert-primary mt-5" role="alert">
	  ${message }
	  <button type="button" class="close" data-dismiss="alert" aria-label="Close">
	    <span aria-hidden="true">&times;</span>
	  </button>
	</div>
</c:if>

<c:if test="${not empty orderAccept}">
    <script>
        window.onload = function () {
            $("#orderacceptmodal").modal("show");
        };
    </script>
</c:if>
<c:if test="${not empty orderDeny}">
    <script>
        window.onload = function () {
            $("#orderdenymodal").modal("show");
            
        };
    </script>
</c:if>

<div class="l-navbar" id="nav-bar">
   <nav class="nav">
      <div>
         <a href="" class="nav_logo">  <img style="height: 25px" src="resources/assets/images/logo.png"  class="bx bx-layer nav_logo-icon"></img> <span class="nav_logo-name">IDRISCAR</span> </a>
         <div class="nav_list">
            <a id="ordernav" href="admin/?clear=1" class="nav_link"> <i class="bx bx-grid-alt nav_icon"></i> <span class="nav_name">Order</span> </a>
            <a id="carnav" href="admin/cars/?clear=1" class="nav_link"> <i class="bx bx-user nav_icon"></i> <span class="nav_name">Cars</span> </a>
            <a id="categorynav" href="admin/types/?clear=1" class="nav_link"> <i class="bx bx-message-square-detail nav_icon"></i> <span class="nav_name">Category</span> </a>
            <a id="brandnav" href="admin/brands/?clear=1" class="nav_link"> <i class="bx bx-bookmark nav_icon"></i> <span class="nav_name">Brand</span> </a> 
            <a id="chartnav" href="admin/chart/" class="nav_link"> <i class="bx bx-bar-chart-alt-2 nav_icon"></i> <span class="nav_name">Chart</span> </a>
         </div>
      </div>
      <a href="login.htm" class="nav_link"> <i class="bx bx-log-out nav_icon"></i> <span class="nav_name">SignOut</span> </a>
   </nav>
</div>


