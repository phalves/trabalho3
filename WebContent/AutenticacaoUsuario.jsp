<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Autentica��o</title>
</head>
<body>
<table>
	<tr><td>
	<fieldset>
		<legend>Autentica��o</legend>
	<table>
	
	<form action="AutenticacaoServlet" method="post">
			
		
		<tr>
			<td align="right">
				Usu�rio:
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
			<td  colspan="2" align="center">
				<button type="submit" name="tipo" value="usuario">Entrar</button>
			</td>
		</tr>
			
		
	</form>
	</table>
	</fieldset></td></tr>
</table>
</body>
</html>