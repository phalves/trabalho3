<%@page import="java.lang.ProcessBuilder.Redirect"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="<c:url value="/"/>bootstrap.css" rel="stylesheet"/>	
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Administração de Salas</title>
</head>
<body>
	<c:if test="${sessionScope.usuario.getAdministrador() != 1 }">
		<c:redirect url="errorpage.jsp"/>
	</c:if>

	<div class="navbar navbar-inverse">
		<div class="navbar-inner">
			<a class="brand" href="#">Sistema de Reserva de Salas</a>
			<ul class="nav">
				<li><a href="AtividadesAdministrativas.jsp">Início</a></li>
				<li><a href="Logout">Logout</a></li>
			</ul>
		</div>
	</div>
	
	<div class="container">
		<div class="row">
			<div class="page-header">
				<h1 style="text-align: center">Cadastro de Salas</h1>
			</div>
			<div class="span12">
				<div class="row">
					<div class="span8">
						<form action="SalaHandler" method="post" class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="local">Nome da Sala</label>
								<div class="controls">
									<input type="text" name="local" placeholder="Nome da Sala">
								</div>
							</div>
							<div class="control-group">
								<div class="controls">
									<label class="checkbox"></label>
									<button class="btn btn-primary" type="submit" name="tipo" value="sala">Cadastrar</button>
								</div>
							</div>
						</form>
					</div>
					<div class="span4">
						<form action="SalaHandler" method="post"
							class="form-horizontal">
							<table class="table table-bordered">
								<tr>
									<th>Salas Cadastradas</th>
								</tr>
								<tr>
									<td>Remover</td>
									<td>Sala</td>
								</tr>
								<c:forEach var="sala" items="${requestScope.salas}">
									<tr>
										<td>
											<button class="btn btn-danger" type="submit" name="tipo" value="${sala.getId() }">X</button>
										</td>
										<td>
											<p><c:out value="${sala.getLocal()}"></c:out></p>
										</td>
									</tr>
								</c:forEach>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>