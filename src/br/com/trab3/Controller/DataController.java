package br.com.trab3.Controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.trab3.Model.Conexao;
import br.com.trab3.Model.Reserva;
import br.com.trab3.Model.Sala;
import br.com.trab3.Model.Usuario;

public class DataController {

	public DataController() {
		super();
	}

	Connection con;
	String sql;
	PreparedStatement pstmt;
	ResultSet resultSet;
	Statement stmt;

	public void criaTabelas() throws ClassNotFoundException, SQLException {	
		con = Conexao.conexao();
		stmt = con.createStatement();

		sql = "Drop table if exists Reserva;";
		stmt.execute(sql);

		sql = "Drop table if exists Sala;";
		stmt.execute(sql);

		sql = "Drop table if exists Usuario;";
		stmt.execute(sql);

		/* Busca pelo maior numero da relacao para poder incrementar  */
		/* select relacao from reserva order by Relacao DESC LIMIT 1; */

		sql = "Create table Sala (Id_Sala INT AUTO_INCREMENT NOT NULL PRIMARY KEY, Local Varchar(20));";
		stmt.execute(sql);

		sql = "Create table Usuario (Id_Usuario INT AUTO_INCREMENT NOT NULL PRIMARY KEY, Username varchar(50), NomeCompleto varchar(50), Email varchar(50), Senha varchar(30), Administrador integer);";
		stmt.execute(sql);

		sql = "CREATE TABLE Reserva (Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Relacao integer, Id_Sala INT NOT NULL, Id_Usuario INT NOT NULL, Data DateTime, Responsavel varchar(50), Motivo varchar(200), Projeto varchar(50), Descricao varchar(300), Confirmado bool, FOREIGN KEY (Id_Sala) REFERENCES Sala(Id_Sala), FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario));";
		stmt.execute(sql);

		sql = "INSERT INTO Usuario (Username, Email , NomeCompleto, Senha, Administrador) value ('adm','inf1407envia@gmail.com','adm','adm','1');";
		stmt.execute(sql);

		stmt.close();
		con.close();	
	}

	public int criaUsuario(Usuario usr) throws ClassNotFoundException, SQLException {
		int pos = 1;
		int status;
		con = Conexao.conexao();
		sql = "Insert into Usuario (username, senha, nomecompleto, email, administrador) values (?, ?, ?, ?, ?);";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(pos++, usr.getUsername());
		pstmt.setString(pos++, usr.getSenha());
		pstmt.setString(pos++, usr.getNomeCompleto());
		pstmt.setString(pos++, usr.getEmail());
		pstmt.setInt(pos++, usr.getAdministrador());

		status = pstmt.executeUpdate();

		return status == 1 ? 1 : 0;
	}

	public ArrayList <Usuario> getUsuarios() throws ClassNotFoundException, SQLException {
		ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

		con = Conexao.conexao();
		sql = "select * from Usuario;";
		pstmt = con.prepareStatement(sql);			
		resultSet = pstmt.executeQuery();

		while(resultSet.next())
		{
			Usuario usr = new Usuario();
			usr.setIdUsuario(Integer.parseInt(resultSet.getString("Id_Usuario")));
			usr.setUsername(resultSet.getString("Username"));
			usr.setNomeCompleto(resultSet.getString("NomeCompleto"));
			usr.setEmail(resultSet.getString("Email"));
			usr.setSenha(resultSet.getString("Senha"));
			usr.setAdministrador(Integer.parseInt(resultSet.getString("Administrador")));

			usuarios.add(usr);
		}

		pstmt.close();
		con.close();

		return usuarios;
	}

	public int removeUsuario(int id) throws ClassNotFoundException, SQLException {
		System.out.print("removeUsuario: ");
		System.out.print(id);
		
		con = Conexao.conexao();
		sql = "delete from Usuario where Id_Usuario = ?";
		pstmt = con.prepareStatement(sql);

		int pos = 1;
		int status;
		pstmt.setInt(pos++, id);

		status = pstmt.executeUpdate();

		pstmt.close();
		con.close();

		return status == 1 ? 1 : 0;
	}

	public int criaSala(String nome) throws ClassNotFoundException, SQLException {
		int pos = 1;
		int status;
		con = Conexao.conexao();
		sql = "Insert into Sala (Local) value (?) ;";
		pstmt = con.prepareStatement(sql);

		pstmt.setString(pos++, nome);

		status = pstmt.executeUpdate();

		return status == 1 ? 1 : 0;
	}

	public ArrayList <Sala> getSalas() throws ClassNotFoundException, SQLException {
		ArrayList<Sala> salas = new ArrayList<Sala>();

		con = Conexao.conexao();
		sql = "select * from Sala;";
		pstmt = con.prepareStatement(sql);			
		resultSet = pstmt.executeQuery();

		while(resultSet.next())
		{
			Sala sala = new Sala();
			sala.setId(Integer.parseInt(resultSet.getString("Id_Sala")));
			sala.setLocal(resultSet.getString("Local"));

			salas.add(sala);
		}

		pstmt.close();
		con.close();

		return salas;
	}

	public int removeSala(int id) throws ClassNotFoundException, SQLException {
		System.out.print("removeSala: ");
		System.out.print(id);
		
		con = Conexao.conexao();
		sql = "delete from Sala where Id_Sala = ?";
		pstmt = con.prepareStatement(sql);

		int pos = 1;
		int status;
		pstmt.setInt(pos++, id);

		status = pstmt.executeUpdate();

		pstmt.close();
		con.close();
		
		return status == 1 ? 1 : 0;
	}
	
	public Usuario autentica (String username, String senha) throws ClassNotFoundException, SQLException{
		int pos = 1;
		
		con = Conexao.conexao();
		sql = "select * from Usuario where Username = ? and Senha = ?;";
		pstmt = con.prepareStatement(sql);
		
		pstmt.setString(pos++, username);
		pstmt.setString(pos++, senha);
		
					
		resultSet = pstmt.executeQuery();

		if(resultSet.next()){
			Usuario usuario = new Usuario();
			usuario.setIdUsuario(Integer.parseInt(resultSet.getString("Id_Usuario")));
			usuario.setAdministrador(Integer.parseInt(resultSet.getString("Administrador")));
			usuario.setEmail(resultSet.getString("Email"));
			usuario.setNomeCompleto(resultSet.getString("NomeCompleto"));
			usuario.setUsername(resultSet.getString("Username"));
			
			pstmt.close();
			con.close();
			
			return usuario;
		}

		pstmt.close();
		con.close();
		
		return null;
	}
	
	public void EnviarEmail (Usuario usuario,String sala)
	{
		// Recipient's email ID needs to be mentioned.
		String to = usuario.getEmail();

		// Sender's email ID needs to be mentioned
		final String from = "inf1407envia@gmail.com";
		final String password = "Web2013#";

		// Get system properties
		Properties props = System.getProperties();

		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");

		// Get the default Session object.
		Session session = Session.getInstance(props,
				new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("inf1407envia@gmail.com", password);
			}
		});


		try{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(to));
			message.setSubject("Pedido de reserva de sala");
			message.setText("O usuario "+usuario.getNomeCompleto()+" deseja reservar a sala "+ sala + "..");

			Transport.send(message);

			System.out.println("Enviado...");

		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}

	}
	
	//
	// Métodos relacionados a reserva
	//
	
	//
	// Retorna um ArrayList contendo objetos de Reserva 
	//
	// Parâmetros:
	// mondayDate - Objeto Date contendo a data da segunda-feira da semana desejada
	//
	//
	protected ArrayList<Reserva> reservationsForHourInWeek( Date mondayDate ) throws ClassNotFoundException, SQLException
	{
		ArrayList<Reserva> arr = new ArrayList<Reserva>();
		
		con = Conexao.conexao();
		// TODO: Query não está pronta
		sql = "SELECT * FROM reserva WHERE data BETWEEN '?' AND '?';";
		
		pstmt = con.prepareStatement(sql);
		pstmt.setDate(1, mondayDate);
		
		// Seta a data para domingo, para pegarmos o range de dias
		Calendar cal = Calendar.getInstance();
		cal.setTime(mondayDate);
		cal.add(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);		
		
		pstmt.setDate(2, (Date)cal.getTime());
		
		resultSet = pstmt.executeQuery();
		
		while(resultSet.next())
		{
			Reserva r = new Reserva();
		}

		pstmt.close();
		con.close();
		
		return arr;
	}
	
	public void insereRelacao(ArrayList<Reserva> reservas) throws ClassNotFoundException, SQLException, ParseException {
		int pos = 1;
		int maxRelacao;
		con = Conexao.conexao();
		sql = "select Relacao from reserva order by Relacao DESC LIMIT 1;";
		
		pstmt = con.prepareStatement(sql);			
		resultSet = pstmt.executeQuery();
		
		if(!resultSet.next())
		{
			maxRelacao = 0;
		}
		else
		{
			maxRelacao = (Integer.parseInt(resultSet.getString("Relacao")))+1;
		}
		
		for(Reserva reserva : reservas)
		{
			pos = 1;
			
			sql = "Insert into reserva (Relacao, Id_Sala, Id_Usuario, Data, Confirmado, Responsavel, Motivo, Projeto, Descricao) values (?, ?, ?, ?,?,?,?,?,?);";
			pstmt = con.prepareStatement(sql);
			
			DateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH");
			java.sql.Timestamp data = new java.sql.Timestamp(fmt.parse(reserva.getDataStringCompleta()).getTime());
			
			pstmt.setInt(pos++, maxRelacao);
			pstmt.setInt(pos++, reserva.getIdSala());
			pstmt.setInt(pos++, reserva.getIdUsuario());
			pstmt.setTimestamp(pos++, data);
			pstmt.setInt(pos++, reserva.getConfirmado());
			pstmt.setString(pos++, reserva.getResponsavel());
			pstmt.setString(pos++, reserva.getMotivo());
			pstmt.setString(pos++, reserva.getProjeto());
			pstmt.setString(pos++, reserva.getDescricao());
			

			pstmt.executeUpdate();			
		}
	}

	// Pega os simbolos da marcação no banco
	// X - Reserva Confirmada
	// ? - Reserva Não Confirmada
	// vazio - sem reservas no horário

	//TODO Tratar troca de mês dias 28, 30 e 31.. Acho que usando o between resolve	
	@SuppressWarnings("deprecation")
	public String[][] getMarcacao(java.util.Date dataInicio, int idsala) throws ClassNotFoundException, SQLException, ParseException {
		String dias[][] = new String[17][8];
		int confirmado;
		int horas = 17;
		int diaSemana = 7;
	
		con = Conexao.conexao();
	
		GregorianCalendar calendar = new GregorianCalendar();
			
		for(int i=0; i<horas; i++)
		{
			
			calendar.setTime(dataInicio);
			java.sql.Timestamp t = new Timestamp(calendar.getTime().getTime());
			calendar.add(Calendar.DAY_OF_MONTH, 7);			
			java.sql.Timestamp t2 = new Timestamp(calendar.getTime().getTime());
			
			sql = "SELECT * FROM Reserva where hour(Data)=? and Id_Sala = ? and data BETWEEN ? AND ? order by data";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, i+7);
			pstmt.setInt(2, idsala);
			pstmt.setTimestamp(3, t);
			pstmt.setTimestamp(4, t2);
			
			resultSet = pstmt.executeQuery();
			
			
			while(resultSet.next())
			{
				for(int j=0; j<diaSemana;j++)
				{
					java.sql.Timestamp data = resultSet.getTimestamp("Data");
					
					int weekDay = data.getDay()-1;
					
					if (weekDay == -1) weekDay = 6;
					
					if(weekDay==j)
					{
						confirmado = Integer.parseInt(resultSet.getString("Confirmado"));
						if(confirmado==1)
							dias[data.getHours()-7][j]="X";
						else
							dias[data.getHours()-7][j]="?";
					}
				}		
			}
		}
		
		for(int i=0;i<horas;i++)
		{
			for(int j=0; j<diaSemana; j++)
			{
				if(dias[i][j]==null)
					dias[i][j] = "vazio";
					
			}
		}
		
		pstmt.close();
		con.close();

		return dias;
	}
	
	public String getNomeSala(int id) throws ClassNotFoundException, SQLException{

		String nome=null;
		
		con = Conexao.conexao();
		sql = "select * from Sala where Id_Sala = ?;";
		pstmt = con.prepareStatement(sql);			
		pstmt.setInt(1, id);
		resultSet = pstmt.executeQuery();

		if(resultSet.next())
		{
			Sala sala = new Sala();
			sala.setId(Integer.parseInt(resultSet.getString("Id_Sala")));
			sala.setLocal(resultSet.getString("Local"));
			nome = sala.getLocal();
		}

		pstmt.close();
		con.close();

		return nome;
		
		
	}

}
