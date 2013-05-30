<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de Erro</title>
</head>
<body>
<h1>Gerenciamento de reserva de salas informa: Algo errado aconteceu</h1>
<h2><c:out value="${requestScope.mensagem}"></c:out></h2>
<p>
	<a href="index.html">Volte para a página principal.</a>
</p>
 
</body>
</html>