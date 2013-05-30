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
		
		$(function() {
			var startDate;
			var endDate;
	
			var selectCurrentWeek = function() {
				window.setTimeout(function() {
					$('.week-picker').find('.ui-datepicker-current-day a').addClass('ui-state-active')
				}, 1);
			}
	
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
									"date" : selectedDateString,
							};
							
							// Faz o request pro servlet passando os parametros acima
							postToUrl("MarcacaoServlet", parameters);
							
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
		});
	</script>
	<title>Marcação de Salas</title>
</head>
<body>

	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<a class="brand" href="#">Sistema de Reserva de Salas</a>
			<ul class="nav">
				<li><a href="Logout">Logout</a></li>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="span4">
				<p>1 - Selecione a Unidade:</p>
				<select name="tipo" id="salaPicker">
					<c:forEach var="sala" items="${sessionScope.salas}">
						<option value="${sala.getId() }">
							<c:out value="${sala.getLocal() }"></c:out>
						</option>
					</c:forEach>
				</select>
				<p>2 - Selecione mês e semana:</p>
				<div class="week-picker" class="span4" style="margin-bottom: 14px;"></div>
				<p>Legenda da tabela ao lado:</p>
				<ul>
					<li>X - Reserva confirmada</li>
					<li>? - Pedido de reserva em análise</li>
				</ul>
				<form action="" method="post" class="form">
					<fieldset>
						<legend>Informações da Reserva</legend>
						<input type="text" placeholder="Responsável"> 
						<input type="text"placeholder="Motivo"> 
						<input type="text" placeholder="Projeto">
						<textarea rows="2" placeholder="Descrição"></textarea>
						<table class="table table-condensed">
							<thead>
								<tr>
									<th>Dia</th>
									<th>Horário</th>
									<th> </th>
								</tr>
							</thead>
							<tbody>
								<tr>
									<td>01/09/2013</td>
									<td>08:00 - 09:00</td>
									<td><button class="btn btn-small btn-danger">X</button></td>
								</tr>
								<tr>
									<td>01/09/2013</td>
									<td>09:00 - 10:00</td>
									<td><button class="btn btn-small btn-danger">X</button></td>
								</tr>
								<tr>
									<td>01/09/2013</td>
									<td>11:00 - 12:00</td>
									<td><button class="btn btn-small btn-danger">X</button></td>
								</tr>
							</tbody>
						</table>
						<button class="btn btn-primary" type="submit">Enviar pedido</button>
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
								<td align="right">7:00<br />8:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">8:00<br />9:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">9:00<br />10:00
								</td>
								<td></td>
								<td align="center">X</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">10:00<br />11:00
								</td>
								<td></td>
								<td align="center">X</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">11:00<br />12:00
								</td>
								<td></td>
								<td align="center">X</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">12:00<br />13:00
								</td>
								<td></td>
								<td></td>
								<td align="center">?</td>
								<td align="center">?</td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">13:00<br />14:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">14:00<br />15:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">15:00<br />16:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">16:00<br />17:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">17:00<br />18:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">18:00<br />19:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">19:00<br />20:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">20:00<br />21:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">21:00<br />22:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
							<tr>
								<td align="right">22:00<br />23:00
								</td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
								<td></td>
							</tr>
						</tbody>
					</table>
			</div>
		</div>
	</div>

<c:out value="${sessionScope.usuario.getNomeCompleto() }"></c:out>
</body>
</html>