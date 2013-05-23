<%@ page language="java" contentType="text/html; charset=US-ASCII"
    pageEncoding="US-ASCII" import="br.com.trab3.Model.*" import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=US-ASCII">
<title>Insert title here</title>
</head>
<body>
<%	
	ArrayList<Usuario> usuarios =  (ArrayList<Usuario>)request.getAttribute("usuarios");
%>
<form action="UsuarioHandler" method="post">
	<table >
		<tr><td>
			<fieldset>
			<legend>Novo usuário</legend>
					Username:
					<input type="text" name="username"/>
					Senha:
					<input type="text" name="senha"/>
					Confirmação de Senha:
					<input type="text" name="confirmacaosenha"/>
					Nome Completo:
					<input type="text" name="nomecompleto"/>
					e-Mail:
					<input type="text" name="email"/>
					Papel:
					<select>
						<option>Usuário</option>
						<option>Administrador</option>
					</select>
					Username:
					<input type="text" name="username"/>
					<button type="submit" name="tipo" value="usuario">Cadastrar</button>
			</fieldset>
		</td>
		<td>
			<fieldset>
				<legend>Usuários Cadastrados</legend>
					<table border="1">
						<tr><td>Cancelar</td><td>Sala</td></tr>
						<% for (Usuario usr : usuarios) {%>
						<tr><td><button value=<%=usr.getIdUsuario() %>>X</button> </td><td><%=usr.getNomeCompleto() %></td></tr>
						<%} %>
						<tr><td>x</td><td>teste</td></tr>
					</table>
			</fieldset>
		</tr>
	</table>
</form>

</body>
</html>