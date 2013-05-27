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
import br.com.trab3.Model.Usuario;

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
		System.out.println("doPost UsuarioHandler 0");
		
		String tipo = request.getParameter("tipo");
		String mensagem;
		DataController d = new DataController();
		ArrayList<Usuario> usuarios;

		if(tipo == null)
		{
			System.out.println("doPost UsuarioHandler 1");
			
			try {
				usuarios = d.getUsuarios();
				request.setAttribute("usuarios", usuarios);
				request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Erro ao chamar getUsuarios: " + e.toString());
				request.getRequestDispatcher("erro.jsp").forward(request, response);
			}
		}
		else
		{
			if(tipo.equals("usuario"))
			{
				System.out.println("doPost UsuarioHandler 2");

				try {
					int status;

					Usuario usuario = new Usuario();
					
					usuario.setUsername(request.getParameter("username"));
					usuario.setSenha(request.getParameter("senha"));
					usuario.setNomeCompleto(request.getParameter("nomecompleto"));
					usuario.setEmail(request.getParameter("email"));
					usuario.setAdministrador(0); // TODO pegar valor do dropdown, tentativa abaixo mas esta vindo null do get
					// usuario.setAdministrador(Integer.parseInt(request.getParameter("administrador")));
					
					status = d.criaUsuario(usuario);
					usuarios = d.getUsuarios();

					if(status == 1)
						mensagem = "Usuario inserido com sucesso!";
					else
						mensagem = "Algo errado aconteceu";

					request.setAttribute("mensagem", mensagem);
					request.setAttribute("usuarios", usuarios);
					request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);

				} catch (ClassNotFoundException | SQLException e) {
					System.err.println("Erro ao tentar criar tabela: " + e.toString());
					request.getRequestDispatcher("erro.jsp").forward(request, response);
				}
			}
			else
			{
				System.out.println("doPost UsuarioHandler 3");

				try {
					int status;
					int idUsuario;

					idUsuario = Integer.parseInt(request.getParameter("tipo"));
					status = d.removeUsuario(idUsuario);

					usuarios = d.getUsuarios();
					request.setAttribute("usuarios", usuarios);

					if(status == 1)
						mensagem = "Usuario removido com sucesso!";
					else
						mensagem = "Algo errado aconteceu";

					request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
				}
				catch (ClassNotFoundException | SQLException e) {
					System.err.println("Erro ao tentar criar tabela: " + e.toString());
					request.getRequestDispatcher("erro.jsp").forward(request, response);
				}
			}
		}
	}
}
