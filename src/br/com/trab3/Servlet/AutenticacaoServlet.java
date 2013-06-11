package br.com.trab3.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.trab3.Controller.DataController;
import br.com.trab3.Model.Sala;
import br.com.trab3.Model.Usuario;

/**
 * Servlet implementation class AutenticacaoServlet
 */
@WebServlet("/AutenticacaoServlet")
public class AutenticacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AutenticacaoServlet() {
        super();
        // TODO Auto-generated constructor stub
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
		String username = request.getParameter("username");
		String senha= request.getParameter("senha");
		String mensagem;
		Usuario usuario;
		DataController d = new DataController();
		HttpSession session = request.getSession();
		
		session.removeAttribute("reservas");
		session.removeAttribute("salas");
		session.removeAttribute("dia");
		session.removeAttribute("dias");
		session.removeAttribute("mes");
		session.removeAttribute("ano");
		session.removeAttribute("idSala");
		session.removeAttribute("startDay");
		session.removeAttribute("segunda");
		session.removeAttribute("nomeSala");
		session.removeAttribute("pedidoReservas");
		session.removeAttribute("mensagem");
		session.removeAttribute("mensagemSucesso");
		session.removeAttribute("usuario");
		
		try{
			ArrayList<Sala> salas;
			salas = d.getSalas();
			session.setAttribute("salas", salas);
			
			if(tipo.equals("adm"))
			{
				usuario = d.autentica(username, senha);
				if(usuario!=null)
				{
					if (usuario.getAdministrador()==1)
					{
						session.setAttribute("usuario", usuario);
						request.getRequestDispatcher("AtividadesAdministrativas.jsp").forward(request, response);
					}
					else
					{
						mensagem = "Você não é administrador..";
						request.setAttribute("mensagem", mensagem);
						request.getRequestDispatcher("errorpage.jsp").forward(request, response);
					}
				}
				else{
					mensagem = "Usuário ou senha incorrtetos..";
					request.setAttribute("mensagem", mensagem);
					request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				}
			}
			else{
				usuario = d.autentica(username, senha);
				if (usuario!=null)
				{
					if(salas.isEmpty())
					{
						request.setAttribute("mensagem", "Nao há nenhuma sala cadastrada no momento");
						request.getRequestDispatcher("errorpage.jsp").forward(request, response);
					}
					else
					{
						session.setAttribute("usuario", usuario);
						request.getRequestDispatcher("Marcacao.jsp").forward(request, response);
					}
				}
								
				else{
					mensagem = "Usuário ou senha incorrtetos..";
					request.setAttribute("mensagem", mensagem);
					request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				}

			}
			
		}
		catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erro ao tentar consultar tabela: " + e.toString());
			request.getRequestDispatcher("errorpage.jsp").forward(request, response);
		}
		
		
	}
}