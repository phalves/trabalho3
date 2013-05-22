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
 * Servlet implementation class CadastroHandler
 */
@WebServlet("/CadastroHandler")
public class CadastroHandler extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CadastroHandler() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String tipo = request.getParameter("tipo");
		String mensagem;
		DataController d = new DataController();
		
		if(tipo!=null && tipo.equals("sala") )
		{
			try {
				ArrayList<Sala> salas = new ArrayList<Sala>();
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
		else{
			try {
				
				ArrayList<Sala> salas;
				salas = d.getSalas();
				request.setAttribute("salas", salas);
				System.out.println("AAAAAAAAAAA");
				request.getRequestDispatcher("CadastroSala.jsp").forward(request, response);
				
			}catch (ClassNotFoundException | SQLException e) {
				System.err.println("Erro ao tentar criar tabela: " + e.toString());
				request.getRequestDispatcher("erro.jsp").forward(request, response);
			}
		}
			
	}

}
