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
				$("#brandnav").addClass("active");
		</script>
      <!--Container Main start-->
      <div class=" bg-light">
         
         <div class="container-fluid mt-5">
            <div class="row">
            
            	<!-- Form type -->
               <div class="col-sm-3 login-section-wrapper">
               
              	  <div class="container mt-3">
                     <div class="row">
                        <form:form action="admin/brands/" class="row g-3" modelAttribute="filter_brand">
                           <div class="col-12">
                              <label for="inputAddress" class="form-label">Name</label>
                              <form:input path="nameFilter" type="text" class="form-control" id="inputAddress" placeholder="Category name..."/>
                           </div>
                           <div class="col-12">
                              <button type="submit" class="btn btn-primary mt-3">Search</button>
                           </div>
                           <div class="col-12">
                              <p>
                           </div>
                        </form:form>
                        
                        <div >
							<a href="admin/brands/?clear=1"><button type="submit" class="btn badge badge-info">Clear filter</button></a>
						</div>
                     </div>
                  </div>
               
                  <div class="mt-5">
                  <h4 class="typed-title">Type Information</h4>
                  <form:form action="admin/brands/" method="post" modelAttribute="brand" enctype="multipart/form-data">
                  	<form:input path="id" type="hidden" class="form-control" placeholder="Id brand"/>
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
                        <label for="exampleFormControlTextarea1">Discription</label>
                        <form:textarea path="disc" class="form-control" id="exampleFormControlTextarea1" rows="3"></form:textarea>
                        <form:errors path="disc" element="errors"></form:errors>
                     </div>
                     <div class="modal-footer">
                     	<div class="row">
                     	<a href="admin/brands/?btnCancel" class="btn">Cancel</a>
                     	<button name="${btnStatus}" type="submit" class="btn btn-primary">Save</button>
                     	</div>
                        
                     </div>
                  </form:form>
                  </div>
               </div>
               
               <jsp:useBean id="pagedListHolder" scope="request"
                  type="org.springframework.beans.support.PagedListHolder" />
               <c:url value="admin/brands/" var="pagedLink">
                  <c:param name="p" value="~" />
               </c:url>
               
               <!-- Table -->
				<div class="col-sm-9 px-0 d-none d-sm-block">
                  
              
                  
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
                                    <h4 class="card-title">Table Brand</h4>
                                    <p class="card-description"> All brand in shop </p>
                                    <div class="table-responsive">
                                       <table class="table">
                                          <thead>
                                             <tr class="bg-light">
                                                <th scope="col" width="5%">#</th>
                                                <th scope="col" width="15%">Name</th>
                                                <th scope="col" width="60%">Description</th>
                                                <th colspan="2" scope="col" class="text-center" width="20%"><span>Action</span></th>
                                             </tr>
                                          </thead>
                                          <tbody>
                                             <c:forEach var="c" items="${pagedListHolder.pageList}">
                                                <tr>
                                                   <td><img src="${c.img }" width="50"></td>
                                                   <td>${c.name } car</td>
                                                   <td>${c.disc }</td>
                                                   <td><a href="admin/brands/${c.id}.htm?linkEdit">
                                                      <button type="button" class="btn btn-info btn-just-icon btn-sm">
                                                      <i class="material-icons">Edit</i>
                                                      </button></a>
                                                   </td>
                                                   <td><a href="admin/brands/${c.id}.htm?linkDelete" role="button">
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