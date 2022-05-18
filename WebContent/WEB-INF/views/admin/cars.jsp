<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@include file="/WEB-INF/views/include/taglib.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!doctype html>
<html>
   <head>
      <%@include file="/WEB-INF/views/include/adminheader.jsp"%>
      <base href="${pageContext.servletContext.contextPath}/">
      
   </head>
   <body id="body-pd" class=" bg-light">
      	<%@include file="/WEB-INF/views/include/adminmenu.jsp"%>
		<script>
				$("#carnav").addClass("active");
		</script>
      <!--Container Main start-->
      <div class=" bg-light">
         
         <div class="container-fluid mt-5">
            <div class="row">
            
            	<!-- Form car -->
               <div class="col-sm-3 login-section-wrapper">
                  <div class="mt-3">
                  <h4 class="card-title">Car Information</h4>
                  <form:form action="admin/cars/" method="post" modelAttribute="car" enctype="multipart/form-data">
                  	 <div class="form-group">
                        <form:input path="id" type="hidden" class="form-control" placeholder="Car id"/>
                     </div>
                     <div class="form-group">
                        <label for="inputAddress2">Name</label>
                        <form:input path="name" type="text" class="form-control" id="inputAddress2"/>
                        <form:errors path="name" element="errors"></form:errors>
                     </div>
                     <div class="form-group">
                        <label for="inputAddress">Image</label>
                        <div class="form-check">
						  <input class="form-check-input" type="radio" name="exampleRadios" id="radiolink" value="option1" onclick="EnableDisableTextBox()"  checked>
						  <form:input path="img" type="text" class="form-control" id="inputlink" placeholder="Link image"/>
						  <form:errors path="img" element="errors"></form:errors>
						</div>
						<div class="form-check">
						  <input class="form-check-input" type="radio" name="exampleRadios" id="radiofile" value="option2" onclick="EnableDisableTextBox()" >
						  <input type="file" name="imageFile" class="form-control-file" id="inputfile" disabled required /> 
						</div>
                     </div>
                     
                     <div class="form-group">
                        <label for="inputAddress2">Video</label>
                        <form:input path="video" type="text" class="form-control" id="inputAddress2" />
                        <form:errors path="video" element="errors"></form:errors>
                     </div>
                     <div class="form-row">
                        <div class="form-group col-md-6">
                           <label for="inputEmail4">Amount</label>
                           <form:input path="amount" type="number" class="form-control" id="inputEmail4" min="0" />
                           <form:errors path="amount" element="errors"></form:errors>
                        </div>
                        <div class="form-group col-md-6">
                           <label for="inputPassword4">Price</label>
                           <form:input path="price" type="number" class="form-control" id="inputPassword4" min="0" max="1000000000000000" />
                           <form:errors path="price" element="errors"></form:errors>
                        </div>
                     </div>
                     <div class="form-group">
                        <label for="exampleFormControlTextarea1">Description</label>
                        <form:textarea path="disc" class="form-control" id="exampleFormControlTextarea1" rows="3"></form:textarea>
                        <form:errors path="disc" element="errors"></form:errors>
                     </div>
                     <div class="form-row">
                        <div class="form-group col-md-5">
                           <label for="sel1">Types:</label>
                           <form:select path="type.id" items="${types }"
								itemValue="id" itemLabel="name"
								class="form-control">
							</form:select>
                        </div>
                        <div class="form-group col-md-5">
                           <label for="sel1">Brands:</label>
                           <form:select path="brand.id" items="${brands }"
								itemValue="id" itemLabel="name"
								class="form-control">
		
							</form:select>
                        </div>
                     </div>
                     <div class="modal-footer">
                     	<div class="row">
                     	<a href="admin/cars/?btnCancel" class="btn">Cancel</a>
                     	<button name="${btnStatus}" type="submit" class="btn btn-primary">Save</button>
                     	</div>
                        
                     </div>
                  </form:form>
                  </div>
               </div>
               
               
               <!-- Table -->
               <div class="col-sm-9 px-0 d-none d-sm-block">
                  <div class="row">
                  	<div class="col-sm-2">
                  		<div class="mb-2 d-flex justify-content-between align-items-center">
		                     <a class="px-2" data-bs-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false" aria-controls="collapseExample"> <span>Filters <i class="fa fa-angle-down"></i></span></a>
		                  </div>
                  	</div>
                  	<div class="col-sm-1">
                  		<a href="admin/cars/?clear=1"><button type="submit" class="btn badge badge-info">Clear filter</button></a>
                  	</div>
                  </div>
                  
                  
                  <!-- Filter -->
                  <div class="collapse container" id="collapseExample">
                     <div class="row">
                        <form:form action="admin/cars/" class="row g-3" modelAttribute="filter_car">
                           <div class="col-12">
                              <label for="inputAddress" class="form-label">Name</label>
                              <form:input path="nameFilter" type="text" class="form-control" id="inputAddress" placeholder="Car name..."/>
                           </div>
                           <div class="col-md-6">
                              <label for="inputEmail4" class="form-label">From</label>
                              <form:input path="minFilter" type="number" class="form-control" id="min_price" min="0" onchange="document.getElementById('max_price').min=this.value;"  placeholder="Minimum price" />
                           </div>
                           <div class="col-md-6">
                              <label for="inputPassword4" class="form-label">To</label>
                              <form:input path="maxFilter" type="number" class="form-control" id="max_price" max="1000000000000000" min="document.getElementById('min_price').value" placeholder="Maximum price" />
                           </div>
                           <div class="col-md-2 form-group">
                              <label for="sel1">Types:</label>
                              <form:select path="typeFilter" items="${typesSearch }"
								itemValue="name" itemLabel="name"
								class="form-control">
								</form:select>
                           </div>
                           <div class="col-md-2 form-group">
                              <label for="sel1">Brands:</label>
                              <form:select path="brandFilter" items="${brandsSearch }"
								itemValue="name" itemLabel="name"
								class="form-control">
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
                  
                  <jsp:useBean id="pagedListHolder" scope="request"
                     type="org.springframework.beans.support.PagedListHolder" />
                  <c:url value="admin/cars/" var="pagedLink">
                     <c:param name="p" value="~" />
                  </c:url>
                  <div>
                     <tg:paging pagedListHolder="${pagedListHolder}"
                        pagedLink="${pagedLink}" />
                  </div>
                  
                  
                  <!-- Table -->
                  <div class="page-content page-container" id="page-content">
                     <div class="padding">
                        <div class="container">
                           <div class="grid-margin stretch-card">
                              <div class="card">
                                 <div class="card-body">
                                    <h4 class="card-title">Table Car</h4>
                                    <p class="card-description"> All car in shop </p>
                                    <div class="table-responsive">
                                       <table class="table">
                                          <thead>
                                             <tr class="bg-light">
                                                <th scope="col" width="5%">Status</th>
                                                <th scope="col" width="15%">#</th>
                                                <th scope="col" width="15%">Name</th>
                                                <th scope="col" width="15%">Type</th>
                                                <th scope="col" width="15%">Brand</th>
                                                <th scope="col" width="15%">Amount</th>
                                                <th scope="col" class="text-center" width="20%"><span>Price</span></th>
                                                <th colspan="2" scope="col" class="text-center" width="20%"><span>Action</span></th>
                                             </tr>
                                          </thead>
                                          <tbody>
                                             <c:forEach var="c" items="${pagedListHolder.pageList}">
                                                <tr>
                                                   <td>
                                                      <c:choose>
                                                         <c:when test="${c.amount == 0 }">
                                                            <i class="fa fa-dot-circle-o text-danger"></i><span class="ms-1"></span>
                                                         </c:when>
                                                         <c:when test="${c.amount > 0 }">
                                                            <i class="fa fa-check-circle-o green"></i><span class="ms-1"></span>
                                                         </c:when>
                                                      </c:choose>
                                                   </td>
                                                   <td><img src="${c.img }" width="50"></td>
                                                   <td>${c.name }</td>
                                                   <td>${c.type.name }</td>
                                                   <td>${c.brand.name }</td>
                                                   <td>${c.amount }</td>
                                                   <td class="text-center"><span class="fw-bolder">${c.price } $</span></td>
                                                   <td><a href="admin/cars/${c.id}.htm?linkEdit">
                                                      <button type="button" class="btn btn-info btn-just-icon btn-sm">
                                                      <i class="material-icons">Edit</i>
                                                      </button></a>
                                                   </td>
                                                   <td><a href="admin/cars/${c.id}.htm?linkDelete" role="button">
                                                      <button type="button" class="btn btn-danger btn-just-icon btn-sm">
                                                      <i class="material-icons">Delete</i>
                                                      </button></a>
                                                   </td>
                                                </tr>
                                             </c:forEach>
                                          </tbody>
                                       </table>
                                    </div>
                                 </div>
                              </div>
                           </div>
                        </div>
                     </div>
                     <p style="height:10">
                     </p>
                  </div>
               </div>
            </div>
         </div>
      </div>
      <!--Container Main end--> 
		
      <%@include file="/WEB-INF/views/include/adminfooter.jsp"%>
   </body>
</html>