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
 * Servlet implementation class MarcacaoServlet
 */
@WebServlet("/MarcacaoServlet")
public class MarcacaoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MarcacaoServlet() {
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
		HttpSession session = request.getSession();
		session.removeAttribute("mensagemSucesso");
		
		System.out.println("Request POST recebido");
		System.out.println("Data Inicio recebida: " + request.getParameter("startDay") + " " + request.getParameter("startMonth") + " " + request.getParameter("startYear"));
		System.out.println("Date Fim recebida: " + request.getParameter("endDay") + " " + request.getParameter("endMonth") + " " + request.getParameter("endYear"));
		System.out.println("idSala recebido: " + request.getParameter("idSala"));
		
		//
		// Guarda no request uma string com a data recebida
		// Esse parametro é lido pelo javascript no marcacao.jsp para manter
		// a semana selecionada no componente
		//
		session.setAttribute("selectedDateString", request.getParameter("startDay")+"/"+request.getParameter("startMonth")+"/"+request.getParameter("startYear"));
		
		String mensagem;
		String opcao = request.getParameter("opcao");
	
		DataController d = new DataController();

		String dia = (request.getParameter("startDay"));
		String mes = (request.getParameter("startMonth"));
		String ano = (request.getParameter("startYear"));
		String hora = (request.getParameter("hora"));
		String idSala = (request.getParameter("idSala"));
		int flag= -1;
		
		if (dia != null)
		{
			session.setAttribute("dia", dia);
			session.setAttribute("mes", mes);
			session.setAttribute("ano", ano);
			session.setAttribute("idSala", idSala);
			
			session.removeAttribute("mensagem");
			flag=0;
		}
		else if (flag == 0)
		{
			dia = (String)session.getAttribute("dia");
			mes = (String)session.getAttribute("mes");
			ano = (String)session.getAttribute("ano");
			idSala = (String)session.getAttribute("idSala");
			flag = 1;
		}
		else 
		{
			dia = (String)request.getParameter("dia");
			mes = (String)session.getAttribute("mes");
			ano = (String)session.getAttribute("ano");
			idSala = (String)session.getAttribute("idSala");
		}
		System.out.println("Saiu ->"+dia);
		
		Date dataFormatada;
		
		ArrayList<Reserva> reservas = (ArrayList<Reserva>)session.getAttribute("reservas");
		
		if (reservas == null)
		{
			reservas = new ArrayList<Reserva>();
		}
		
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy hh");
		String dataString = dia+"-"+mes+"-"+ano+ " " +hora;
		
		if ( flag == 0 )
		{
			int segunda = Integer.parseInt(dia);
			int terca = Integer.parseInt(dia) +1;
			int quarta = Integer.parseInt(dia) +2;
			int quinta = Integer.parseInt(dia) +3;
			int sexta = Integer.parseInt(dia) +4;
			int sabado = Integer.parseInt(dia) +5;
			int domingo = Integer.parseInt(dia) +6;

			session.setAttribute("segunda", segunda);
			session.setAttribute("terca", terca);
			session.setAttribute("quarta", quarta);
			session.setAttribute("quinta", quinta);
			session.setAttribute("sexta", sexta);
			session.setAttribute("sabado", sabado);
			session.setAttribute("domingo", domingo);
		}
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		System.out.println("Usuario: "+usuario.getNomeCompleto());
		
		//
		// Adicionar
		//
		if ( opcao != null && opcao.equals("adicionar") )
		{
			try {
				dataFormatada = formato.parse(dataString);
				System.out.println("Data Formatada: "+ dataFormatada);

				Reserva reserva = new Reserva();
				reserva.setData(dataFormatada);
				reserva.setIdSala(Integer.parseInt(idSala));
				reserva.setIdUsuario(usuario.getIdUsuario());
				reserva.setConfirmado(0);
				reservas.add(reserva);
				session.setAttribute("reservas", reservas);
				
			} catch (ParseException e) {
				mensagem = "Selecione uma semana no calendario primeiro";
				session.setAttribute("mensagem", mensagem);
				e.printStackTrace();
			}
		}
		//
		// Marcar
		//
		else if ( opcao != null && opcao.equals("marcar") )
		{
			try {
				d.insereRelacao(reservas);
				mensagem = "Pedido enviado com sucesso!";
				session.setAttribute("mensagemSucesso", mensagem);
				session.removeAttribute("reservas");
				
			} catch (ClassNotFoundException | SQLException e1) {
				e1.printStackTrace();
			}
		}
		request.getRequestDispatcher("Marcacao.jsp").forward(request, response);
	}
}
