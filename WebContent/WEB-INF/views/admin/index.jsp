<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html>
<html>
    <head>
    	<%@include file="/WEB-INF/views/include/adminheader.jsp"%>
      	<base href="${pageContext.servletContext.contextPath}/">
   </head>

    <body id="body-pd" class=" bg-light">
    
		<%@include file="/WEB-INF/views/include/adminmenu.jsp"%>
		<script>
				$("#ordernav").addClass("active");
		</script>
		<!--Container Main start-->
		<div class=" bg-light">
			<div class="content mt-5">
				<div class="row col-12">
					<div class="col-sm-9 login-section-wrapper">
						<div class="row">
							<div class="col-sm-2">
								<div class="mb-2 d-flex justify-content-between align-items-center">
	     							<a class="px-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"> <span>Filters <i class="fa fa-angle-down"></i></span></a>
	  							</div>
							</div>
							<div class="col-sm-1">
								<a href="admin/?clear=1"><button type="submit" class="btn badge badge-info mt-1">Clear filter</button></a>
							</div>
						</div>
	                
	                	<!-- Filter -->  
		                <div class="collapse container" id="collapseExample">
		                     <div class="row">
		                        <form:form action="admin/" class="row g-3" modelAttribute="filter_order">
		                           <div class="col-md-3">
		                              <label for="inputAddress" class="form-label">OID</label>
		                              <form:input path="oidFilter" name="oid" type="text" class="form-control" id="inputAddress" placeholder="OID"/>
		                           </div>
		                           <div class="col-md-9">
		                              <label for="inputAddress" class="form-label">Customer</label>
		                              <form:input path="customerFilter" name="customer" type="text" class="form-control" id="inputAddress" placeholder="Customer name..." />
		                           </div>
		                           <div class="col-md-5">
		                              <label for="inputEmail4" class="form-label">Email</label>
		                              <form:input path="emailFilter" name="email" type="text" class="form-control" id="min_price"  placeholder="Email" />
		                           </div>
		                           <div class="col-md-5">
		                              <label for="inputPassword4" class="form-label">Phone</label>
		                              <form:input path="phoneFilter" name="phone" type="text" class="form-control"   placeholder="Phone" />
		                           </div>
		                           <div class="col-md-2 form-group">
		                              <label for="sel1">Status:</label>
		                              <form:select path="statusFilter" class="form-control">
		                                 <form:options items="${status}" />
		                              </form:select>
		                           </div>
		                           <div class="col-12">
		                              <button type="submit" class="btn btn-primary">Search</button>
		                           </div>
		                           <div class="col-12">
		                              <p>
		                           </div>
		                        </form:form>
		                        
		                     </div>
		                  </div>
					</div>
					
					
					<div class="col-sm-3 login-section-wrapper">
						<jsp:useBean id="pagedListHolder" scope="request"
				           type="org.springframework.beans.support.PagedListHolder" />
				        <c:url value="admin/" var="pagedLink">
				           <c:param name="p" value="~" />
				        </c:url>
				        <div>
				           <tg:paging pagedListHolder="${pagedListHolder}"
				              pagedLink="${pagedLink}" />
				        </div>
					</div>
				</div>
			</div>
        

	        <div class="card mt-1">
	            <div class="card-body">
	                <h4 class="card-title">Basic Table</h4>
	                <p class="card-description"> Basic table with card </p>
	                <div class="table-responsive">
	                    <table class="table">
	                        <thead>
	                            <tr>
	                                <th scope="col" width="10%">OID</th>
	                                <th scope="col" width="15%">Car</th>
	                                <th scope="col" width="10%">Customer</th>
	                                <th scope="col" width="10%">Email</th>
	                                <th scope="col" width="10%">Phone</th>
	                                <th scope="col" width="10%">Address</th>
	                                <th scope="col" width="10%">Date</th>
	                                <th scope="col" width="10%">Amount</th>
	                                <th scope="col" class="text-center" width="15%"><span>Total</span></th>
	                                <th scope="col" width="10%">Status</th>
	                                <th colspan="2" scope="col" class="text-center" width="20%"><span>Action</span></th>
	                            </tr>
	                        </thead>
	                        <tbody>
	                        	<c:forEach var="o" items="${pagedListHolder.pageList}">
	                               <tr>
	                                  
	                                  <td>${o.oid }</td>
	                                  <td>${o.car.name }</td>
	                                  <td>${o.customer }</td>
	                                  <td>${o.email }</td>
	                                  <td>${o.phone }</td>
	                                  <td>${o.addres }</td>
	                                  <td>${o.datebuy }</td>
	                                  <td>${o.amount }</td>
	                                  <td class="text-center"><span class="fw-bolder">${o.total } $</span></td>
	                                  <td>
	                                     <c:choose>
	                                        <c:when test="${o.stat == 2}">
	                                           <label class="badge badge-danger">Denied</label>
	                                        </c:when>
	                                        <c:when test="${o.stat == -1 }">
	                                           <label class="badge badge-warning">Pending</label>
	                                        </c:when>
	                                        <c:when test="${o.stat == 1 }">
	                                           <label class="badge badge-success">Accepted</label>
	                                        </c:when>
	                                     </c:choose>
	                                  </td>
	                                  <c:choose>
	                                  <c:when test="${o.stat == -1 }">
	                                  <td><a href="admin/${o.id }.htm?linkAccept&p=~">
	                                   <button type="button" class="btn btn-info btn-just-icon btn-sm">
	                                   <i class="material-icons">Accept</i>
	                                   </button></a>
	                                  </td>
	                                  <td><a href="admin/${o.id }.htm?linkDeny&p=~">
	                                   <button type="button" class="btn btn-danger btn-just-icon btn-sm">
	                                   <i class="material-icons">Deny</i>
	                                   </button></a>
	                                  </td>
	                                  </c:when>
	                                  <c:when test="${o.stat != -1 }">
	                                  <td> </td>
	                                  <td> </td>
	                                  </c:when>
	                                  </c:choose>
	                               </tr>
	                            </c:forEach>
	                        </tbody>
	                    </table>
	                </div>
	            </div>
	        </div>
		</div>

		
            
        
        
        
        
        <!-- Order modal -->
        
        <div class="modal fade" id="orderacceptmodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Order</h5>
                    </div>
                    <div class="modal-body">
						<div class="image-cover card bg-dark text-white">
						  <img class="card-img" src="${order.car.img }" alt="Card image">
						  <div class="card-img-overlay">						  
						    <h5 class="card-title">Car: ${order.car.name }</h5>
						    <p class="card-text">Customer: ${order.customer }</p>
						    <p class="card-text">Email: ${order.email }</p>
						    <p class="card-text">Phone: ${order.phone }</p>
						    <p class="card-text">Address: ${order.addres }</p>
						    <p class="card-text">Amount: ${order.amount }</p>
						    
						  </div>
						</div>
						<p class="card-text mt-2">Choose an expected date</p>
                        <form:form action="admin/" modelAttribute="order">
                        	<form:input path="id" type="hidden" class="form-control" />
                        	<form:input path="oid" type="hidden" class="form-control" />
                        	<form:input path="datebuy" type="hidden" class="form-control" />
                        	<form:input path="car.id" type="hidden" class="form-control" />
                        	<form:input path="admin.id" type="hidden" class="form-control" />
                        	<form:input path="customer" type="hidden" class="form-control" />
                        	<form:input path="email" type="hidden" class="form-control" />
                        	<form:input path="phone" type="hidden" class="form-control" />
                        	<form:input path="addres" type="hidden" class="form-control" />
                        	<form:input path="amount" type="hidden" class="form-control" />
                        	<form:input path="stat" type="hidden" class="form-control" />
                        	<form:input path="total" type="hidden" class="form-control" />
                        	<input name="expecteddate" id="datepicker" width="276" required/>
                        	<button name="btnAccept" type="submit" class="btn mt-2 btn-info btn-just-icon btn-sm">
	                        <i class="material-icons">Accept</i>
	                        </button>
                        </form:form>
					    <script>
					        $('#datepicker').datepicker({
					            uiLibrary: 'bootstrap4'
					        });
					        $.datepicker.formatDate('dd-mm-yy', dateTypeVar);
					    </script>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="orderdenymodal" tabindex="-1" role="dialog" aria-labelledby="exampleModalCenterTitle" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLongTitle">Order</h5>
                    </div>
                    <div class="modal-body">
						<div class="image-cover card bg-dark text-white">
						  <img class="card-img" src="${order.car.img }" alt="Card image">
						  <div class="card-img-overlay">						  
						    <h5 class="card-title">Car: ${order.car.name }</h5>
						    <p class="card-text">Customer: ${order.customer }</p>
						    <p class="card-text">Email: ${order.email }</p>
						    <p class="card-text">Phone: ${order.phone }</p>
						    <p class="card-text">Address: ${order.addres }</p>
						    <p class="card-text">Amount: ${order.amount }</p>
						    
						  </div>
						</div>
                        <form:form action="admin/" method="post" modelAttribute="order">
                        	<form:input path="id" type="hidden" class="form-control"/>
                        	<form:input path="oid" type="hidden" class="form-control" />
                        	<form:input path="datebuy" type="hidden" class="form-control" />
                        	<form:input path="car.id" type="hidden" class="form-control" />
                        	<form:input path="admin.id" type="hidden" class="form-control" />
                        	<form:input path="customer" type="hidden" class="form-control" />
                        	<form:input path="email" type="hidden" class="form-control" />
                        	<form:input path="phone" type="hidden" class="form-control" />
                        	<form:input path="addres" type="hidden" class="form-control" />
                        	<form:input path="amount" type="hidden" class="form-control" />
                        	<form:input path="stat" type="hidden" class="form-control" />
                        	<form:input path="total" type="hidden" class="form-control" />
                        	<label class="mt-1">Reason:</label>
                        	<textarea name="messagebody" class="form-control mt-1" id="exampleFormControlTextarea1" rows="3"></textarea>
                        	<button name="btnDeny" type="submit" class="btn mt-2 btn-danger btn-just-icon btn-sm">
	                        <i class="material-icons">Deny</i>
	                        </button>
                        </form:form>
					    <script>
					        $('#datepicker').datepicker({
					            uiLibrary: 'bootstrap4'
					        });
					        
					    </script>
                    </div>
                </div>
            </div>
        </div>
        
        <%@include file="/WEB-INF/views/include/adminfooter.jsp"%>
    </body>
</html>
