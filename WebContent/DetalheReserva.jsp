<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Detalhes Reserva</title>
</head>
<body>
<form method="post" action="HomologacaoServlet">
	
	
	Descrição da Reserva<br>
	Sala: <c:out value="${sessionScope.nomeSala }"/><br>
	Responsável: <c:out value="${reserva.getResponsavel() }"/><br>
	Motivo: <c:out value="${reserva.getMotivo() }"/><br>
	Projeto: <c:out value="${reserva.getProjeto() }"/><br>
	Descrição: <c:out value="${reserva.getDescricao() }"/><br>
	
	<br>
	
	Datas:<br>
	<c:forEach var="reserva" items="${sessionScope.reservas }">
		<c:out value="${reserva.getDataStringCompleta() }"/>:00<br>
	</c:forEach>
	

</form>
</body>
</html>