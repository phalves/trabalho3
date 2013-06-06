<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value="/"/>bootstrap.css" rel="stylesheet"/>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.1/jquery.js"></script>
    <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/jquery-ui.min.js"></script>
    <link rel="stylesheet" type="text/css" media="screen" href="http://ajax.googleapis.com/ajax/libs/jqueryui/1.8.14/themes/base/jquery-ui.css">
	<script type="text/javascript">
		//
		// Cria um request POST a partir de um elemento form
		//
		function postToUrl(path, params, method) {
		    method = method || "post"; // Set method to post by default if not specified.
	
		    var form = document.createElement("form");
		    form.setAttribute("method", method);
		    form.setAttribute("action", path);
	
		    for(var key in params) {
		        if(params.hasOwnProperty(key)) {
		            var hiddenField = document.createElement("input");
		            hiddenField.setAttribute("type", "hidden");
		            hiddenField.setAttribute("name", key);
		            hiddenField.setAttribute("value", params[key]);
	
		            form.appendChild(hiddenField);
		         }
		    }
	
		    document.body.appendChild(form);
		    form.submit();
		}
		
		var startDate;
		var endDate;
		
		$(function() {
			
	
			var selectCurrentWeek = function() {
				window.setTimeout(function() {
					$('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active');
				}, 1);
			};			
	
			$('.week-picker').datepicker({
						firstDay : 1,
						showOtherMonths : true,
						selectOtherMonths : true,
						onSelect : function(dateText, inst) {
							//
							// Detecta a data da semana selecionada
							//
							var date = $(this).datepicker('getDate');
							
							var weekDay = date.getDay();
							var delta = (weekDay == 0) ? 7 : 0;
							
							startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1 - delta);
							endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7 - delta);
							
							var dateFormat = inst.settings.dateFormat || $.datepicker._defaults.dateFormat;
							
							//
							// Apenas para debugging
							//
							//$('#startDate').text($.datepicker.formatDate(dateFormat, startDate, inst.settings));
							//$('#endDate').text($.datepicker.formatDate(dateFormat, endDate, inst.settings));
							
							//console.log($.datepicker.formatDate(dateFormat, startDate, inst.settings));
							//console.log($.datepicker.formatDate(dateFormat, endDate, inst.settings));
							//console.log("\n");
							
							// Pega o id da Sala selecionada
							var e = document.getElementById("salaPicker");
							var selectedSalaId = e.options[e.selectedIndex].value;
							
							// Pega a data da segunda-feira da semana selecionada como string
							var selectedDateString = $.datepicker.formatDate(dateFormat, startDate, inst.settings);
							
							// Cria o dicionario de parametros
							var parameters = {
									"idSala" : selectedSalaId,
									"startDay" : startDate.getDate(),
									"startMonth" : startDate.getMonth()+1,
									"startYear" : startDate.getFullYear(),
									"endDay" : endDate.getDate(),
									"endMonth" : endDate.getMonth()+1,
									"endYear" : endDate.getFullYear()
							};
							
							console.log(selectedDateString);
							
							// Faz o request pro servlet passando os parametros
							postToUrl("HomologacaoServlet", parameters);
							
							selectCurrentWeek();
						},
						beforeShowDay : function(date) {
							var cssClass = '';
							if (date >= startDate && date <= endDate) {
								cssClass = 'ui-datepicker-current-day';
							}
							return [ true, cssClass ];
						},
						onChangeMonthYear : function(year, month, inst) {
							selectCurrentWeek();
						}
					});
	
			$('.week-picker .ui-datepicker-calendar tr').live('mousemove',
					function() {
						$(this).find('td a').addClass('ui-state-hover');
					});
			$('.week-picker .ui-datepicker-calendar tr').live('mouseleave',
					function() {
						$(this).find('td a').removeClass('ui-state-hover');
					});
			
			function selectWeekFromDate( date ) {
				return;
				$('.week-picker').datepicker( "setDate", date );
				var date = $('.week-picker').datepicker('getDate');
				
				var weekDay = date.getDay();
				var delta = (weekDay == 0) ? 7 : 0;
				
				startDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 1 - delta);
				endDate = new Date(date.getFullYear(), date.getMonth(), date.getDate() - date.getDay() + 7 - delta);
				$( ".week-picker" ).datepicker( "refresh" );
				selectCurrentWeek();
			}
			
			//
			// TODO: Passar data selecionada como parametro
			//
			jQuery(document).ready(function($) {
				console.log("Imprimindo: " + "<c:out value="${sessionScope.selectedDateString}"></c:out>");
				<c:choose>
			      <c:when test="${sessionScope.selectedDateString == null}">
			      	selectWeekFromDate( new Date() );
			      </c:when>
			      <c:otherwise>
			      	selectWeekFromDate( <c:out value="${sessionScope.selectedDateString}"></c:out> );
			      </c:otherwise>
				</c:choose>
			});
		});
	</script>
	<title>Homolocação de reservas</title>
</head>
<body>
	<c:if test="${sessionScope.usuario.getAdministrador() != 1 }">
		<c:redirect url="errorpage.jsp"/>
	</c:if>
	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<a class="brand" href="#">Sistema de Reserva de Salas</a>
			<ul class="nav">
				<li><a href="Logout">Logout</a></li>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<c:if test="${sessionScope.mensagem != null}">
			<div class="alert alert-error">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Aviso:</strong>
				<c:out value="${sessionScope.mensagem}"></c:out>
			</div>
		</c:if>
		<c:if test="${sessionScope.mensagemSucesso != null}">
			<div class="alert alert-success">
				<button type="button" class="close" data-dismiss="alert">&times;</button>
				<strong>Aviso:</strong>
				<c:out value="${sessionScope.mensagemSucesso}"></c:out>
			</div>
		</c:if>

		<div class="row">
			<div class="span4">
				<p>1 - Selecione a Unidade:</p>
				<select name="tipo" id="salaPicker">
					<c:forEach var="sala" items="${sessionScope.salas}">
						<c:if test="${sessionScope.idSala == sala.getId() }">
							<option selected value="${sala.getId() }" >
								<c:out value="${sala.getLocal() }"></c:out>
							</option>
						</c:if>
						<c:if test="${sessionScope.idSala != sala.getId() }">
							<option value="${sala.getId() }" >
								<c:out value="${sala.getLocal() }"></c:out>
							</option>
						</c:if>
					</c:forEach>
				</select>
				<p>2 - Selecione mês e semana:</p><br>
				<div class="week-picker" class="span4" style="margin-bottom: 14px;"></div>
				
				<form action="HomologacaoServlet" method="post" class="form">
					<fieldset>

						<table class="table table-condensed">
							<thead>
								<tr>
									<th>Nome</th>
									<th>Relacao</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="reserva" items = "${sessionScope.reservas}">
									<tr>
										<td><c:out value="${sessionScope.nomeSala }"></c:out></td>
										<td>
											<c:out value="${reserva.getRelacao() }"></c:out>
										</td>	
										<td>
											<button class="btn btn-primary" type="submit" name="relacao" value="${reserva.getRelacao() }">Detalhes</button>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</fieldset>
				</form>
			</div>
			<div class="span8">
					<table class="table table-bordered table-hover" width="100%">
						<thead>
							<tr>
								<th width="9px">Horário</th>
								<th>SEG</th>
								<th>TER</th>
								<th>QUA</th>
								<th>QUI</th>
								<th>SEX</th>
								<th>SÁB</th>
								<th>DOM</th>
							</tr>
						</thead>
						<tbody>
							<tr>
							
							</tr>
						</tbody>
					</table>
			</div>
		</div>
	</div>
	
</body>
</html>