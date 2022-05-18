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
   		<a class="d-flex align-items-center mb-2">Username: ${user.username }</a>
   		
   		<div class="d-flex align-items-center">
   		<form:form action="changepass.htm" method="post" modelAttribute="user">
	   		<form:input type="hidden" path="username" name="username" placeholder="enter your username"/>
	   		<form:input type="hidden" path="pass" name="password" placeholder="enter your password"/>
	   		<input type="password" name="passwordnew" id="password" class="form-control" placeholder="enter new password" required/>
   			<button class="btn btn-dark mt-3 px-4" type="submit">Change</button>
   		</form:form>
   		</div>
    </div>
</div>
</body>
</html>