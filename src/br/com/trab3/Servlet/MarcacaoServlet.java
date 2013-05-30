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
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		System.out.print("Post recebido");
		request.getRequestDispatcher("Marcacao.jsp").forward(request, response);
		
		if(true) return;
		
		String opcao = request.getParameter("opcao");
	
		DataController d = new DataController();

		int dia = Integer.parseInt(request.getParameter("dia"));
		int hora = Integer.parseInt(request.getParameter("hora"));
		int idSala = Integer.parseInt(request.getParameter("sala"));
		
		Date dataFormatada;
		
		ArrayList<Reserva> reservas = (ArrayList<Reserva>)session.getAttribute("reservas");
		
		if(reservas == null)
			reservas = new ArrayList<Reserva>();
		
		SimpleDateFormat formato = new SimpleDateFormat("dd-MM-yyyy hh");
		String dataString = dia+"-"+"12"+"-"+"2013"+" "+hora;		
		
		Usuario usuario = (Usuario)session.getAttribute("usuario");
		
		if(opcao.equals("adicionar"))
		{
			try {
				dataFormatada = formato.parse(dataString);
				
				Reserva reserva = new Reserva();
				reserva.setData(dataFormatada);
				reserva.setIdSala(idSala);
				reserva.setIdUsuario(usuario.getIdUsuario());
				reserva.setConfirmado(0);
				reservas.add(reserva);
				session.setAttribute("reservas", reservas);
				
				request.getRequestDispatcher("Marcacao.jsp").forward(request, response);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		else if (opcao.equals("marcar"))
		{
			try {
				dataFormatada = formato.parse(dataString);
				d.insereRelacao(reservas);
			} catch (ClassNotFoundException | SQLException | ParseException e1) {
				e1.printStackTrace();
			}
		}
	}
}
