package br.com.trab3.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.trab3.Controller.DataController;

/**
 * Servlet implementation class CriaTabela
 */
@WebServlet("/CriaTabela")
public class CriaTabela extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriaTabela() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DataController d = new DataController();
		String mensagem;
		
		try {
			
			d.criaTabelas();
			
			mensagem = "Tabela criada com sucesso!";
			
			request.setAttribute("mensagem", mensagem);
			request.getRequestDispatcher("Resultado.jsp").forward(request, response);
			
		} catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erro ao tentar criar tabela: " + e.toString());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
	}

}
