<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="<c:url value="/"/>bootstrap.css" rel="stylesheet"/>
	<title>Página de Erro</title>
</head>
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
		<div class="page-header">
			<h1 style="text-align: center">Algo errado aconteceu</h1>
		</div>
		<div class="alert alert-error">
			<h4><c:out value="${requestScope.mensagem}"></c:out></h4>
		</div>
		<a href="index.html" class="btn">Volte para a página principal.</a>
	</div>
 
</body>
</html>