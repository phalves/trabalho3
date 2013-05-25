package br.com.trab3.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trab3.Controller.DataController;
import br.com.trab3.Model.Sala;

/**
 * Servlet implementation class UsuarioHandler
 */
@WebServlet("/UsuarioHandler")
public class UsuarioHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioHandler() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO
		DataController d = new DataController();
		
		try 
		{
			//System.out.println(d.getUsuarios().toString());
			request.setAttribute("usuarios", d.getUsuarios());
			request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
		} 
		catch (ClassNotFoundException | SQLException e) 
		{
			System.err.println("Erro ao chamar getUsuarios: " + e.toString());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}
}
