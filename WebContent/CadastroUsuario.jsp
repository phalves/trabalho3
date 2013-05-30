<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="br.com.trab3.Model.*" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>Administração de Usuários</title>
</head>
<body>

<a href="AtividadesAdministrativas.jsp">Inicio</a> | <a href="Logout">Logout</a>

<form action="UsuarioHandler" method="post">
	<table >
		<tr><td>
			<fieldset>
			<legend>Novo usuário</legend>
					Username:
					<input type="text" name="username"/><br>
					Senha:
					<input type="password" name="senha"/><br>
					Confirmação de Senha:
					<input type="password" name="confirmacaosenha"/><br>
					Nome Completo:
					<input type="text" name="nomecompleto"/><br>
					E-mail:
					<input type="text" name="email"/><br>
					Papel:
					<select name="administrador">
						<option value="0">Usuário</option>
						<option value="1">Administrador</option>
					</select><br>
					<button type="submit" name="tipo" value="usuario">Cadastrar</button>
			</fieldset>
		</td>
		<td>
			<fieldset>
				<legend>Lista de  usuários Cadastrados</legend>
					<table border="1">
						<tr><td>Remover</td><td>Nome Completo</td></tr>
						<c:forEach var="usuario" items = "${requestScope.usuarios}">
							<tr><td><button type="submit" name="tipo" value="${usuario.getIdUsuario() }">X</button> </td><td><c:out value="${usuario.getNomeCompleto() }"></c:out></td></tr>
						</c:forEach>
					</table>
			</fieldset>
		</tr>
	</table>
</form>

</body>
</html>