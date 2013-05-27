<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autenticação</title>
</head>
<body>
<table>

<form action="AutenticacaoServlet" method="post">
	<fieldset>
	<legend>Autenticação</legend>	
	
	<tr>
		<td align="right">
			Usuário:
		</td>
		<td>
			<input type="text" name="username"/><br>
		</td>
	</tr>
	<tr>
		<td align="right">
			Senha:
		</td>
		<td>
			<input type="password" name="senha"/><br>
		</td>
	</tr>
	<tr>
		<td>
			<button type="submit" name="tipo" value="usuario">Entrar</button>
		</td>
	</tr>
		
	
</form>
</fieldset>

</table>

</body>
</html>