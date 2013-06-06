package br.com.trab3.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
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
	
	//
	// Retorna um booleano indicando se o request tem algum campo vazio
	//
	private boolean hasEmptyFields( HttpServletRequest request )
	{
		ArrayList<String> list = new ArrayList<String>();
		list.add("username");
		list.add("senha");
		list.add("confirmacaosenha");
		list.add("nomecompleto");
		list.add("email");
		list.add("administrador");
		
		for ( String e : list )
		{
			String var = (String)request.getParameter(e);
			
			if (var == null)
			{
				return true;
			}
			if (var.toString().length() <= 0)
			{
				return true;
			}
		}
		
		return false;
	}
	
	private boolean isValidEmailAddress(String email) {
		   boolean result = true;
		   try {
		      InternetAddress emailAddr = new InternetAddress(email);
		      emailAddr.validate();
		   } catch (AddressException ex) {
		      result = false;
		   }
		   return result;
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
		ArrayList<Usuario> usuarios;

		if(tipo == null)
		{
			
			try {
				usuarios = d.getUsuarios();
				request.setAttribute("usuarios", usuarios);
				request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
			} catch (ClassNotFoundException | SQLException e) {
				System.err.println("Erro ao chamar getUsuarios: " + e.toString());
				request.getRequestDispatcher("errorpage.jsp").forward(request, response);
			}
		}
		else
		{
			if(tipo.equals("usuario"))
			{
				try {
					int status;
					
					// Valida se todos os campos foram preenchidos
					if ( this.hasEmptyFields(request) )
					{
						request.setAttribute("mensagem", "Todos os campos são de preenchimento obrigatório.");
						request.getRequestDispatcher("errorpage.jsp").forward(request, response);
						return;
					}
					
					// Verifica se as senhas conferem antes de criar um novo objeto Usuário
					if ( !request.getParameter("senha").equals(request.getParameter("confirmacaosenha")) )
					{
						request.setAttribute("mensagem", "A senha e a confirmação de senha informadas não conferem.");
						request.getRequestDispatcher("errorpage.jsp").forward(request, response);
						return;
					}
					
					String emailString = request.getParameter("email").toString();
					
					// Valida se o email é válido
					if ( !this.isValidEmailAddress(emailString) )
					{
						request.setAttribute("mensagem", "O email '" + emailString + "' informado não é válido.");
						request.getRequestDispatcher("errorpage.jsp").forward(request, response);
						return;
					}					

					Usuario usuario = new Usuario();
					
					usuario.setUsername(request.getParameter("username"));
					usuario.setSenha(request.getParameter("senha"));
					usuario.setNomeCompleto(request.getParameter("nomecompleto"));
					usuario.setEmail(request.getParameter("email"));
					usuario.setAdministrador(Integer.parseInt(request.getParameter("administrador")));
					
					
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
					request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				}
			}
			else
			{
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

					request.setAttribute("mensagem", mensagem);
					request.getRequestDispatcher("CadastroUsuario.jsp").forward(request, response);
				}
				catch (ClassNotFoundException | SQLException e) {
					System.err.println("Erro ao tentar criar tabela: " + e.toString());
					request.getRequestDispatcher("errorpage.jsp").forward(request, response);
				}
			}
		}
	}
}
