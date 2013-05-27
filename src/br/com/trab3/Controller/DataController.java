package br.com.trab3.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import br.com.trab3.Model.Conexao;
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

		sql = "CREATE TABLE Reserva (Id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Relacao integer, Id_Sala INT NOT NULL, Id_Usuario INT NOT NULL, Data Date, Confirmado bool, FOREIGN KEY (Id_Sala) REFERENCES Sala(Id_Sala), FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario));";
		stmt.execute(sql);

		sql = "INSERT INTO Usuario (NomeCompleto, Senha, Administrador) value ('adm','adm','1');";
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

		if (status == 1)
			return 1;
		else
			return 0;
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

		if (status == 1)
			return 1;
		else
			return 0;
	}

	public int criaSala(String nome) throws ClassNotFoundException, SQLException {
		int pos = 1;
		int status;
		con = Conexao.conexao();
		sql = "Insert into Sala (Local) value (?) ;";
		pstmt = con.prepareStatement(sql);

		pstmt.setString(pos++, nome);

		status = pstmt.executeUpdate();

		if (status == 1)
			return 1;
		else
			return 0;
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

		if (status == 1)
			return 1;
		else
			return 0;
	}
}
