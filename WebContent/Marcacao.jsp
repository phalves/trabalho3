<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" errorPage="errorpage.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Em construção</h1>

<form method="post" action="MarcacaoServlet">

	dia
	<select name="dia" >
		<option  value="1">1</option>
		<option  value="2">2</option>
	</select><br/>
	hora
	<select name="hora">
		<option value="1">1</option>
		<option value="2">2</option>
	</select><br/>
	
	sala
	<select name="sala">
		<c:forEach var="sala" items = "${sessionScope.salas}">
			<option  value="${sala.getId() }"><c:out value="${sala.getLocal() }"></c:out></option>
		</c:forEach>
	</select><br/>
	
	Usuário: <c:out value="${usuario.getNomeCompleto() }"></c:out><br>
	
	<button type="submit" name="opcao" value="adicionar">Adicionar</button><br>
	<Button type="submit" name="opcao" value="marcar">Marcar</Button>

</form>

<br><br><br><br>

<table border=1>
	<tr>
		<td valign="top">
			<!-- Unidade, Quem, Motivo, Projeto, Descrição -->
			Unidade:
			
			<select>
				<c:forEach var="sala" items = "${sessionScope.salas}">
					<option name="tipo" value="${sala.getId() }"><c:out value="${sala.getLocal() }"></c:out></option>
				</c:forEach>
			</select><br/>
			<p>Legenda:</p>
			<ul>
				<li>X - Reserva confirmada</li>
				<li>? - Pedido de reserva em análise</li>
			</ul>
		</td>
		<td align="right">
			<!-- Calendário -->
			<table border="1">
				<tr>
					<td>&lt;</td>
					<td colspan="5">Setembro/2013</td>
					<td>&gt;</td>
				</tr>
				<tr>
					<td>S</td><td>T</td><td>Q</td><td>Q</td><td>S</td><td bgcolor="#808080">S</td><td bgcolor="#808080">D</td>
				</tr>
				<tr>
					<td><span style="color: #C0C0C0">26</span></td><td><span style="color: #C0C0C0">27</span></td><td><span style="color: #C0C0C0">28</span></td><td><span style="color: #C0C0C0">29</span></td><td><span style="color: #C0C0C0">30</span></td><td bgcolor="#808080"><span style="color: #C0C0C0">31</span></td><td bgcolor="#808080">1</td>
				</tr>
				<tr>
					<td>2</td><td>3</td><td>4</td><td>5</td><td>6</td><td bgcolor="#808080">7</td><td bgcolor="#808080">8</td>
				</tr>
				<tr>
					<td>9</td><td>10</td><td>11</td><td>12</td><td>13</td><td bgcolor="#808080">14</td><td bgcolor="#808080">15</td>
				</tr>
				<tr>
					<td>16</td><td>17</td><td>18</td><td>19</td><td>20</td><td bgcolor="#808080">21</td><td bgcolor="#808080">22</td>
				</tr>
				<tr>
					<td>23</td><td>25</td><td>25</td><td>26</td><td>27</td><td bgcolor="#808080">28</td><td bgcolor="#808080">29</td>
				</tr>
				<tr>
					<td>30</td><td><span style="color: #C0C0C0">1</span></td><td><span style="color: #C0C0C0">2</span></td><td><span style="color: #C0C0C0">3</span></td><td><span style="color: #C0C0C0">4</span></td><td bgcolor="#808080"><span style="color: #C0C0C0">5</span></td><td bgcolor="808080"><span style="color: #C0C0C0">6</span></td>
				</tr>
			</table>
		</td>
	</tr>
	<tr>
		<td colspan="2">
			<!-- horários -->
			<table border="1" width="100%">
				<tr>
					<td></td><td align="center">SEG</td><td align="center">TER</td><td align="center">QUA</td><td align="center">QUI</td><td align="center">SEX</td><td bgcolor="#808080" align="center">SAB</td><td bgcolor="#808080" align="center">DOM</td>
				</tr>
				<tr>
					<td align="right">7:00<br/>8:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">8:00<br/>9:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">9:00<br/>10:00</td><td></td><td align="center">X</td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">10:00<br/>11:00</td><td></td><td align="center">X</td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">11:00<br/>12:00</td><td></td><td align="center">X</td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">12:00<br/>13:00</td><td></td><td></td><td align="center">?</td><td align="center">?</td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">13:00<br/>14:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">14:00<br/>15:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">15:00<br/>16:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">16:00<br/>17:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">17:00<br/>18:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">18:00<br/>19:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">19:00<br/>20:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">20:00<br/>21:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">21:00<br/>22:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
				<tr>
					<td align="right">22:00<br/>23:00</td><td></td><td></td><td></td><td></td><td></td><td></td><td></td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<c:out value="${sessionScope.usuario.getNomeCompleto() }"></c:out>
<a href="index.html">Página Inicial</a>
</body>
</html>