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
      	<link rel="stylesheet" href="<c:url value='resources/assets/css/public/cars.css'/>">
      	<link rel="stylesheet" href="<c:url value='resources/assets/css/public/orders.css'/>">

      	<%@include file="/WEB-INF/views/include/header.jsp"%>
   </head>
<body>

<header class="header-area overlay">
    <div class="page-header">
        <div class="container">
            <div class="row">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <div class="page-caption">
                        <%@include file="/WEB-INF/views/include/menu.jsp"%>
                    </div>
                </div>
            </div>
        </div>
    </div>
	
</header>
<div class="card-section" id="main">
   <div class="container ">
       <div class="card-block bg-white mb30 card" style="padding: 10px !important">
           <div class="row mb-4">
               <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                   <!-- section-title -->
                   <div class="section-title mb-0 ">
                       <form action="orders/" class="row" method="post">
                           <div class="col-md-9">
                              <label for="inputAddress" class="form-label">OID</label>
                              <input name="oid" name="oid" type="text" class="form-control" id="inputAddress" placeholder="OID" required/>
                           </div>
                           <div class="col-md-3">
                              <button type="submit" class="btn btn-primary mt-5">Search</button>
                           </div>
                        </form>
                   </div>
                   <!-- /.section-title -->
               </div>
           </div>
       </div>
      
       
       	<c:forEach var="order" items="${orders}">
       <div class="row">
			<div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                   <!-- section-title -->
                   <div class="section-title mb-0">
						<div class="container page-top">
						<div class="row" >
							<div class="card">
							    <div class="title">Car infomation</div>
							    <img  src="${order.car.img }">
							    <div class="info">
							        <div class="row">
							            <div class="col-6"> <span id="heading">Type</span><br> <span id="details">${order.car.type.name } car</span> </div>
							            <div class="col-6 pull-right"> <span id="heading">Brand</span><br> <span>${order.car.brand.name } </span> </div>
							        </div>
							        <div class="row">
							            <div class="col-7"> <span id="heading">Price</span><br> <span>${order.car.price } $</span> </div>
							        </div>
							        <div class="row">
							            <div class="col-12"> <span id="heading">Discription</span><br> <span>${order.car.disc }</span> </div>
							        </div>
							    </div>
							    
							</div>
							
							<div class="card">
							    <div class="title">Purchase Reciept</div>
							    <div class="info">
							        <div class="row">
							            <div class="col-7"> <span id="heading">Date</span><br> <span id="details">${order.datebuy }</span> </div>
							            <div class="col-5 pull-right"> <span id="heading">Order No.</span><br> <span id="details">${order.oid }</span> </div>
							        </div>
							        <div class="row">
							            <div class="col-12"> <span id="heading">Customer</span><br> <span>${order.customer }</span> </div>
							        </div>
							        <div class="row">
							            <div class="col-7"> <span id="heading">Email</span><br> <span>${order.email }</span> </div>
							            <div class="col-5 pull-right"> <span id="heading">Phone</span><br> <span id="details">${order.phone }</span> </div>
							        </div>
							        <div class="row">
							            <div class="col-12"> <span id="heading">Address</span><br> <span>${order.addres }</span> </div>
							        </div>
							    </div>
							    <div class="pricing">
							        <div class="row">
							            <div class="col-8"> <span id="name">${order.car.name } x ${order.amount }</span> </div>
							            <div class="col-4"> <span id="price" style="float: right !important">${order.car.price } $</span> </div>
							        </div>
							    </div>
							    <div class="total">
							        <div class="row">
							            <div class="col-12" ><big style="float: right !important">${order.total } $</big></div>
							        </div>
							    </div>
							    <div class="tracking">
							        <div class="title">Tracking Order</div>
							    </div>
							    <div class="progress-track">
							        <ul id="progressbar">
							            <li class="step0 active " id="step1">Ordered</li>
							            
							            <c:choose>
							            <c:when test="${order.stat == -2 }">
							            <li class="step0 text-center" id="step2">Confirmed</li>
							            <li class="step0 text-right" id="step3">Pending</li>
							            <li class="step0 text-right" id="step4">Accepted</li>
							            </c:when>
							            <c:when test="${order.stat == -1 }">
							            <li class="step0 active text-center" id="step2">Confirmed</li>
							            <li class="step0 active text-right" id="step3">Pending</li>
							            <li class="step0 text-right" id="step4">Accepted</li>
							            </c:when>
							            <c:when test="${order.stat == 1 }">
							            <li class="step0 active text-center" id="step2">Confirmed</li>
							            <li class="step0 active text-right" id="step3">Pending</li>
							            <li class="step0 active text-right" id="step4">Accepted</li>
							            </c:when>
							            <c:when test="${order.stat == 2 }">
							            <li class="step0 active text-center" id="step2">Confirmed</li>
							            <li class="step0 active text-right" id="step3">Pending</li>
							            <li class="step0 active text-right" id="step4">Denied</li>
							            </c:when>
							            </c:choose>
							            
							        </ul>
							    </div>
							    <div class="footer">
							        <div class="row">
							            <div class="col-2"><img src="<c:url value='resources/assets/images/logo.png'/>"></div>
							            <div class="col-10">Want any help? Please &nbsp;<a> contact us</a></div>
							        </div>
							    </div>
							</div>
						</div>
						</div>	
                   </div>
                   <!-- /.section-title -->
               </div>
       </div>
       </c:forEach>
   </div>
</div>


		
		<%@include file="/WEB-INF/views/include/footer.jsp"%>
		<script>
			window.onload = function () {
				var logged = ${logged};
				if(logged != "0"){
					$("#logintag").text("Logout");
				}
				$("#ordertag").addClass("active");
			};
		</script>
</body>
</html>