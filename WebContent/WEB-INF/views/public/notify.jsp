<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
	<head>
      <base href="${pageContext.servletContext.contextPath}/">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
      <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
      <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.bundle.min.js">
      <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
      <style type="text/css">
     	body {
          background: #BCBCCC
		}
		
		.card {
		    width: 350px;
		    flex-direction: column;
		    min-width: 0;
		    word-wrap: break-word;
		    background-color: #fff;
		    background-clip: border-box;
		    border: 1px solid #d2d2dc;
		    border-radius: 6px;
		    -webkit-box-shadow: 0px 0px 5px 0px rgb(249, 249, 250);
		    -moz-box-shadow: 0px 0px 5px 0px rgba(212, 182, 212, 1);
		    box-shadow: 0px 0px 5px 0px rgb(161, 163, 164)
		}
		
		.cookies a {
		    text-decoration: none;
		    color: #000;
		    margin-top: 8px
		}
		
		.cookies a:hover {
		    text-decoration: none;
		    color: blue;
		    margin-top: 8px
		}
      </style>
   </head>
<body>
<div class="d-flex justify-content-center mt-5 h-100">
    <div class="d-flex align-items-center align-self-center card p-3 text-center cookies">
    	<c:if test="${status==1}">
    	<img src="https://icons.veryicon.com/png/o/miscellaneous/cloud-call-center/success-24.png" width="50">
    	<span class="mt-2">${message }</span>
   		<a class="d-flex align-items-center" href="#footer">Contact us<i class="fa fa-angle-right ml-2"></i></a>
   		<a class="d-flex align-items-center" href="orders/?oid=${order.oid }">See order detail<i class="fa fa-angle-right ml-2"></i></a>
    	</c:if>
    	<c:if test="${status==0}">
    	<img src="https://jumeirahroyal.com/wp-content/uploads/d7e50cb89c.png" width="50">
    	<span class="mt-2">${message }</span>
    	<a class="d-flex align-items-center" href="#footer">Contact us<i class="fa fa-angle-right ml-2"></i></a>
    	</c:if>
   		
   		<a class="d-flex align-items-center" href=""><button class="btn btn-dark mt-3 px-4" type="button">Back to Home</button> </a>
    </div>
</div>
</body>
</html>