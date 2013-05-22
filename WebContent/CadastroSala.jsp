<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<table >
		<tr><td>
			<fieldset>
			<legend>Nova sala</legend>
				<form action="CadastroHandler" method="post">
					
					Nome da Sala:
					<input type="text" name="local"/>
					<button type="submit" name="tipo" value="sala">Cadastrar</button>
				</form>
				
			</fieldset>
		</td></tr>
	</table>
</body>
</html>