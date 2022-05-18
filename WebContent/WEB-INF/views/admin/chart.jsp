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
		<link rel="stylesheet" href="<c:url value='resources/assets/css/chart.css'/>">
		<base href="${pageContext.servletContext.contextPath}/">
      	<%-- <script src="<c:url value='resources/assets/scripts/script.js'/>" ></script> --%>
      
   </head>
   <body id="body-pd" class=" bg-light">
      	<%@include file="/WEB-INF/views/include/adminmenu.jsp"%>
		<script>
				$("#chartnav").addClass("active");
		</script>
      <!--Container Main start-->
      <div class=" bg-light">

<script type="text/javascript">
$(document).ready(function(){

	google.charts.load('current', {'packages':['corechart']});
	google.charts.setOnLoadCallback(drawChart);

	function drawChart() {


	var a = [];
	var year = ${year};
	a.push(['Car', 'Amount']);
	<c:forEach var="c" items="${revenues}">
	var amount = ${c.amount};
    a.push(['${c.name}',amount]);                                  
	</c:forEach>
	
	var data = google.visualization.arrayToDataTable(a);

	var options = {
	title: 'Number of cars sold in ' + year,
	is3D:true
	};

	var chart = new google.visualization.PieChart(document.getElementById('piechart3d'));

	chart.draw(data, options);
	}


	});
</script>
<script src='https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.4/Chart.bundle.min.js'></script>
<script>
    $(document).ready(function() {
        var ctx = $("#chart-line");
        var year = ${year};
        var car = [], amount = [], total = [];
    	<c:forEach var="c" items="${revenues}">
	    	var a = ${c.amount};
			var t = ${c.total};
    		car.push('${c.name}' +  ': ' + a + ' car'); 
    		amount.push(a);
    		total.push(t);
    	</c:forEach>
        var myLineChart = new Chart(ctx, {
            type: 'bar',
            data: {
                labels: car,
                datasets: [{
                    data: total,
                    label: "Total revenue of each car",
                    borderColor: "#4723D9",
                    backgroundColor: '#4723D9',
                    fill: false
                }]
            },
            options: {
                title: {
                    display: true,
                    text: 'All cars sold in ' + year
                }
            }
        });
    });
</script>

<div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="row">
        	<div class="container-fluid d-flex justify-content-center mt-1">
				<form action="admin/chart/">
				<select name="year" class="form-control form-control-lg" onchange="this.form.submit()">
					<c:forEach var="c" items="${list_year}">
						<c:if test="${year == c }">
							<option selected value="${c }">${c }</option>
						</c:if>
						<c:if test="${year != c }">
							<option value="${c }">${c }</option>
						</c:if>
					</c:forEach>
				</select>
				</form>
			</div>
            <div class="container d-flex justify-content-center">
                <div class="col-sm-12 col-md-10">
                    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
					<div class="container d-flex justify-container-center mt-2">
					    <div class="row">
					        <div class="col-md-12">
					            <div id="piechart3d" style="width: 900px; height: 500px;"></div>
					        </div>
					    </div>
					</div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="page-content page-container" id="page-content">
    <div class="padding">
        <div class="row">
        	
            <div class="container d-flex justify-content-center">
                <div class="col-sm-12 col-md-10">
                    <div class="card mt-2">
                    	<div class="row">
                    		<div class="container-fluid d-flex justify-content-center mt-1">
								<form action="admin/chart/">
								<select name="year" class="form-control form-control-lg" onchange="this.form.submit()">
									<c:forEach var="c" items="${list_year}">
										<c:if test="${year == c }">
											<option selected value="${c }">${c }</option>
										</c:if>
										<c:if test="${year != c }">
											<option value="${c }">${c }</option>
										</c:if>
									</c:forEach>
								</select>
								</form>
							</div>
                    	</div>
                        
                        <div class="card-body" style="height: 100%">
                            <div class="chartjs-size-monitor" style="position: absolute; left: 0px; top: 0px; right: 0px; bottom: 0px; overflow: hidden; pointer-events: none; visibility: hidden; z-index: -1;">
                                <div class="chartjs-size-monitor-expand" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                    <div style="position:absolute;width:1000000px;height:1000000px;left:0;top:0"></div>
                                </div>
                                <div class="chartjs-size-monitor-shrink" style="position:absolute;left:0;top:0;right:0;bottom:0;overflow:hidden;pointer-events:none;visibility:hidden;z-index:-1;">
                                    <div style="position:absolute;width:200%;height:200%;left:0; top:0"></div>
                                </div>
                            </div> <canvas id="chart-line" width="299" height="200" class="chartjs-render-monitor" style="display: block; width: 299px; height: 200px;"></canvas>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

      </div>
      <!--Container Main end--> 
		
		
      <%@include file="/WEB-INF/views/include/adminfooter.jsp"%>
   </body>
</html>