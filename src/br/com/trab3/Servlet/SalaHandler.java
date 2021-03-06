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
		
		String tipo = request.getParameter("tipo");
		String mensagem;
		DataController d = new DataController();
		ArrayList<Sala> salas;	

		if(tipo == null)
		{
			try {
				salas = d.getSalas();
				request.setAttribute("salas", salas);
				request.getRequestDispatcher("CadastroSala.jsp").forward(request, response);

			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Erro ao tentar consultar tabela: " + e.toString());
				request.getRequestDispatcher("errorpage.jsp").forward(request, response);
			}
		}
		else
		{			
			if(tipo.equals("sala"))
			{	
				try {
					int status;

					String sala = request.getParameter("local");
					
					status = d.criaSala(sala);
					salas = d.getSalas();

					if(status == 1)
						mensagem = "Sala inserida com sucesso!";
					else
						mensagem = "Algo errado aconteceu";

					request.setAttribute("mensagem", mensagem);
					request.setAttribute("salas", salas);
					request.getRequestDispatcher("CadastroSala.jsp").forward(request, response);

				} catch (ClassNotFoundException | SQLException e) {
					System.err.println("Erro ao tentar consultar tabela: " + e.toString());
					request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				}
			}
			else
			{				
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
					request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				}
			}
		}
	}
}
