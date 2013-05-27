package br.com.trab3.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.trab3.Controller.DataController;
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
		
		
		try{
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
						request.getRequestDispatcher("Resultado.jsp").forward(request, response);
					}
				}
				else{
					mensagem = "Usuário ou senha incorrtetos..";
					request.setAttribute("mensagem", mensagem);
					request.getRequestDispatcher("Resultado.jsp").forward(request, response);
				}
			}
			else{
				usuario = d.autentica(username, senha);
				if (usuario!=null)
				{
					session.setAttribute("usuario", usuario);
					request.getRequestDispatcher("PedidoReserva.jsp").forward(request, response);
				}
								
				else{
					mensagem = "Usuário ou senha incorrtetos..";
					request.setAttribute("mensagem", mensagem);
					request.getRequestDispatcher("Resultado.jsp").forward(request, response);
				}

			}
			
		}
		catch (ClassNotFoundException | SQLException e) {
			System.err.println("Erro ao tentar criar tabela: " + e.toString());
			request.getRequestDispatcher("erro.jsp").forward(request, response);
		}
		
		
	}
}