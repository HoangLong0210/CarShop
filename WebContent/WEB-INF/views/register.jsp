<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<base href="${pageContext.servletContext.contextPath}/">
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta http-equiv="X-UA-Compatible" content="ie=edge">
  <title>Login Template</title>
  <link href="https://fonts.googleapis.com/css?family=Karla:400,700&display=swap" rel="stylesheet">
  <link rel="stylesheet" href="https://cdn.materialdesignicons.com/4.8.95/css/materialdesignicons.min.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
  <link rel="stylesheet" href="resources/assets/css/login.css">
</head>
<script>
function myFunction() {
	var alerts = "${alerts}";
	if(alerts != "") alert(alerts);
}
</script>
<body onload="myFunction()">
  <div>
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-6 login-section-wrapper">
          <div class="brand-wrapper">
            <img src="resources/assets/images/logo.png" alt="logo" class="logo">
            <a style="font-family: 'Lobster', cursive; color: black" href="" class="navbar-brand">IDRISCAR</a>
          </div>
          <div class="login-wrapper my-auto">
            <h1 class="login-title">Register</h1>
            <form:form action="register.htm" method="post" modelAttribute="user">
              <div class="form-group">
                <label for="username">Username</label>
                <form:input type="text" path="username" id="username" class="form-control" placeholder="enter your username"/>
                <form:errors path="username" element="errors"></form:errors>
              </div>
              <div class="form-group mb-4">
                <label for="pass">Password</label>
                <form:input type="password" path="pass" id="pass" class="form-control" placeholder="enter your passsword"/>
                <form:errors path="pass" element="errors"></form:errors>
              </div>
              <div class="form-group mb-4">
                <label for="email">Email</label>
                <form:input type="text" path="email" id="email" class="form-control" placeholder="abc@gmail.com"/>
                <form:errors path="email" element="errors"></form:errors>
              </div>
              <input id="login" class="btn btn-block login-btn" type="submit" value="Register">
            </form:form>
            ${message }
            <p>
            <p class="login-wrapper-footer-text">Have an account? <a href="login.htm" class="text-reset">Login here</a></p>
          </div>
        </div>
        <div class="col-sm-6 px-0 d-none d-sm-block">
          <img src="resources/assets/images/login.jpg" alt="login image" class="login-img">
        </div>
      </div>
    </div>
  </div>
  		<div class="modal fade" id="forgotpassmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Forgot password</h5>
                    </div>
                    <div class="modal-body">
                        <form action="getpass.htm" method="post">
                        	<label class="mt-1">Email:</label>
                        	<input type="email" name="email" id="email" class="form-control" placeholder="enter your email" required/>
                        	<input class="btn btn-block login-btn" type="submit" value="Get password">
                        </form>
                    </div>
                </div>
            </div>
        </div>
  <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</body>
</html>
