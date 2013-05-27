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
 * Servlet implementation class SalaHandler
 */
@WebServlet("/SalaHandler")
public class SalaHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SalaHandler() {
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
		System.out.println("doPost SalaHandler 0");
		
		String tipo = request.getParameter("tipo");
		String mensagem;
		DataController d = new DataController();
		ArrayList<Sala> salas;

		if(tipo == null)
		{
			System.out.println("doPost SalaHandler 1");
			
			try {
				salas = d.getSalas();
				request.setAttribute("salas", salas);
				request.getRequestDispatcher("CadastroSala.jsp").forward(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Erro ao tentar criar tabela: " + e.toString());
				request.getRequestDispatcher("erro.jsp").forward(request, response);
			}
		}
		else
		{			
			if(tipo.equals("sala"))
			{
				System.out.println("doPost SalaHandler 2");
				
				try {
					int status;

					status = d.criaSala(request.getParameter("local"));
					salas = d.getSalas();

					if(status == 1)
						mensagem = "Sala inserida com sucesso!";
					else
						mensagem = "Algo errado aconteceu";

					request.setAttribute("mensagem", mensagem);
					request.setAttribute("salas", salas);
					request.getRequestDispatcher("CadastroSala.jsp").forward(request, response);

				} catch (ClassNotFoundException | SQLException e) {
					System.err.println("Erro ao tentar criar tabela: " + e.toString());
					request.getRequestDispatcher("erro.jsp").forward(request, response);
				}
			}
			else
			{
				System.out.println("doPost SalaHandler 3");
				
				try {
					int status;
					int idSala;

					idSala = Integer.parseInt(request.getParameter("tipo"));
					status = d.removeSala(idSala);

					salas = d.getSalas();
					request.setAttribute("salas", salas);

					if(status == 1)
						mensagem = "Sala removida com sucesso!";
					else
						mensagem = "Algo errado aconteceu";

					request.getRequestDispatcher("CadastroSala.jsp").forward(request, response);
				}
				catch (ClassNotFoundException | SQLException e) {
					System.err.println("Erro ao tentar criar tabela: " + e.toString());
					request.getRequestDispatcher("erro.jsp").forward(request, response);
				}
			}
		}
	}
}
