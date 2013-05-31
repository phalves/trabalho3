<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="br.com.trab3.Model.*" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<link href="<c:url value="/"/>bootstrap.css" rel="stylesheet"/>
	<title>Administração de Usuários</title>
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
		<div class="row">
			<div class="page-header">
				<h1 style="text-align: center">Cadastro de Usuários</h1>
			</div>
			<div class="span12">
				<div class="row">
					<div class="span6 offset2">
						<form action="UsuarioHandler" method="post" class="form-horizontal">
							<div class="control-group">
								<label class="control-label" for="username">Username</label>
								<div class="controls">
									<input type="text" name="username" placeholder="Username">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="senha">Senha</label>
								<div class="controls">
									<input type="password" name="senha" placeholder="Senha">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="senha">Confirmação de
									Senha</label>
								<div class="controls">
									<input type="password" name="confirmacaosenha" placeholder="Confirmação de Senha">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="nomecompleto">Nome
									Completo</label>
								<div class="controls">
									<input type="text" name="nomecompleto" placeholder="Nome Completo">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="email">Email</label>
								<div class="controls">
									<input type="text" name="email" placeholder="Email">
								</div>
							</div>
							<div class="control-group">
								<label class="control-label" for="administrador">Papel</label>
								<div class="controls">
									<select name="administrador">
										<option value="0">Usuário</option>
										<option value="1">Administrador</option>
									</select>
								</div>
								<div class="control-group">
									<div class="controls">
										<label class="checkbox"></label>
										<button class="btn btn-primary" type="submit" name="tipo"
											value="usuario">Cadastrar</button>
									</div>
								</div>
							</div>
						</form>
					</div>
					<div class="span4">
						<form action="UsuarioHandler" method="post" class="form-horizontal">
							<table class="table table-bordered">
								<colgroup>
									<col span="1" style="width: 95%;">
									<col span="1" style="width: 5%;">
								</colgroup>
								<thead>
									<tr>
										<th>Usuários Cadastrados</th>
									</tr>
									<tr>
										<th>Nome Completo</th>
										<th>Remover</th>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="usuario" items="${requestScope.usuarios}">
										<tr>
											<td>
												<c:out value="${usuario.getNomeCompleto() }"></c:out>
											</td>
											<td>
												<button class="btn btn-danger" type="submit"name="tipo" value="${usuario.getIdUsuario() }">X</button>
											</td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>