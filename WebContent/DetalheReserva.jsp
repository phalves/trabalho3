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
	
	<c:forEach var="reserva" items="${sessionScope.reservas }">
		<c:out value="${reserva.getDataStringCompleta() }"/> <c:out value="${reserva.getIdSala() }"/><br>			
	</c:forEach>

</form>
</body>
</html>