<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="br.com.trab3.Model.*" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Administração de Salas</title>
</head>
<body>

<h1>Cadastro de Salas</h1>
<a href="AtividadesAdministrativas.jsp">Inicio</a> | <a href="Logout">Logout</a>
<form action="SalaHandler" method="post">
	<table >
		<tr><td>
			<fieldset>
			<legend>Nova sala</legend>
					Nome da Sala:
					<input type="text" name="local"/>
					<button type="submit" name="tipo" value="sala">Cadastrar</button>
			</fieldset>
		</td>
		<td>
			<fieldset>
				<legend>Lista de salas cadastradas</legend>
					<table border="1">
						<tr><td>Cancelar</td><td>Sala</td></tr>
						<c:forEach var="sala" items = "${requestScope.salas}">
							<tr><td><button type="submit" name="tipo" value="${sala.getId() }">X</button> </td><td><c:out value="${sala.getLocal() }"></c:out></td></tr>
						</c:forEach>
					</table>
			</fieldset>
		</tr>
	</table>
</form>
</body>
</html>