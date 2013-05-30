<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value="/"/>bootstrap.css" rel="stylesheet"/>
	<title>Atividades Administrativas</title>
</head>
<c:if test="${sessionScope.usuario.getAdministrador() != 1 }">
	<c:redirect url="errorpage.jsp"/>
</c:if>
<body>

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
					<h1 style="text-align: center">Atividades Administrativas</h1>
			</div>
			<div class="span4 offset4">
				<table class="table table-bordered">
					<tr>
						<td>
							<a href="HomologacaoReserva" class="btn btn-link">Homologação de pedido de reserva</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="UsuarioHandler" class="btn btn-link">Cadastro de usuário</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="SalaHandler" class="btn btn-link">Cadastro de sala</a>
						</td>
					</tr>
					<tr>
						<td>
							<a href="Logout" class="btn btn-link">Logout</a>
						</td>
					</tr>
				</table>
			</div>
		</div>
	</div>

</body>
</html>