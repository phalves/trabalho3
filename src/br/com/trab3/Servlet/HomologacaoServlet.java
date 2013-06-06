package br.com.trab3.Servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.com.trab3.Controller.DataController;
import br.com.trab3.Model.Reserva;
import br.com.trab3.Model.Usuario;

/**
 * Servlet implementation class HomologacaoServlet
 */
@WebServlet("/HomologacaoServlet")
public class HomologacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public HomologacaoServlet() {
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
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		String relacao = request.getParameter("relacao");
		String opcao = request.getParameter("opcao");
		session.removeAttribute("mensagemSucesso");
		session.removeAttribute("mensagem");
		
		DataController d = new DataController();
		
		String startDay = request.getParameter("startDay");
		String dia = (request.getParameter("startDay"));
		String mes = (request.getParameter("startMonth"));
		String ano = (request.getParameter("startYear"));
		String idSala = (request.getParameter("idSala"));
		
		ArrayList<Reserva> reservas = (ArrayList<Reserva>)session.getAttribute("reservas");
		
		if (reservas == null)
		{
			reservas = new ArrayList<Reserva>();
		}
		
		if (dia != null)
		{
				
			session.setAttribute("dia", dia);
			session.setAttribute("mes", mes);
			session.setAttribute("ano", ano);
			session.setAttribute("idSala", idSala);
			session.setAttribute("startDay", startDay);
			
			session.removeAttribute("mensagem");

		}
		else 
		{
			dia = (String)request.getParameter("dia");
			mes = (String)session.getAttribute("mes");
			ano = (String)session.getAttribute("ano");
			idSala = (String)session.getAttribute("idSala");
			startDay = (String)session.getAttribute("startDay");
		}
				
		Date dataFormatada;
		SimpleDateFormat formato2 = new SimpleDateFormat("dd-MM-yyyy");
		String dataString2 = startDay+"-"+mes+"-"+ano;
		System.out.println(dataString2);
		
		try {
			if ( relacao != null && opcao==null )
			{
				int idRelacao = Integer.parseInt(relacao);
				reservas = d.getReservasComRelacaoId(idRelacao);
				String nomeSala = d.getNomeSala(Integer.parseInt(idSala));
				session.setAttribute("nomeSala", nomeSala);
				session.setAttribute("pedidoReservas", reservas);
				request.getRequestDispatcher("DetalheReserva.jsp").forward(request, response);
			}
			else if(startDay!=null && opcao==null)
			{
				//Busca o NomeCompleto de cada relacao na semana selecionada

				dataFormatada = formato2.parse(dataString2);
				reservas = d.getRelacoes(dataFormatada, Integer.parseInt(idSala));
				String nomeSala = d.getNomeSala(Integer.parseInt(idSala));
				session.setAttribute("nomeSala", nomeSala);
				session.setAttribute("reservas", reservas);
				request.getRequestDispatcher("Homologacao.jsp").forward(request, response);
			}
			else if(opcao!=null && opcao.equals("aceitar"))
			{
				ArrayList<Reserva> pedidoReservas = (ArrayList<Reserva>)session.getAttribute("pedidoReservas");
				d.confirmaReservas(pedidoReservas);
				session.setAttribute("mensagemSucesso", "Pedido Confirmado");
				session.removeAttribute("reservas");
				
				//Envia email
				Reserva reserva = pedidoReservas.get(0);
				Usuario usuario = d.getUsuario(reserva.getIdUsuario());
				String mensagem = "Seu pedido de reserva de sala foi aceito";
				String nomeSala = d.getNomeSala(Integer.parseInt(idSala));
				d.EnviarEmail(usuario, nomeSala, mensagem);
				request.getRequestDispatcher("AtividadesAdministrativas.jsp").forward(request, response);
			}
			else if(opcao!=null && opcao.equals("rejeitar"))
			{
				ArrayList<Reserva> pedidoReservas = (ArrayList<Reserva>)session.getAttribute("pedidoReservas");
				d.rejeitaReservas(pedidoReservas);
				session.setAttribute("mensagem", "Pedido Rejeitado");
				session.removeAttribute("reservas");
				
				Reserva reserva = pedidoReservas.get(0);
				Usuario usuario = d.getUsuario(reserva.getIdUsuario());
				String mensagem = "Seu pedido de reserva de sala foi rejeitado";
				String nomeSala = d.getNomeSala(Integer.parseInt(idSala));
				d.EnviarEmail(usuario, nomeSala, mensagem);
				request.getRequestDispatcher("AtividadesAdministrativas.jsp").forward(request, response);
			}
			else
			{
				request.getRequestDispatcher("Homologacao.jsp").forward(request, response);
			}
		} catch (NumberFormatException | ClassNotFoundException
				| SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
