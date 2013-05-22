package br.com.trab3.Controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import br.com.trab3.Model.Conexao;

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
		
		/* Busca pelo maior numero da relação para poder incrementar */
		/*select relacao from reserva order by Relacao DESC LIMIT 1 ;*/
		
		
		sql = "Create table Sala (Id_Sala INT AUTO_INCREMENT NOT NULL PRIMARY KEY, Local Varchar(20));";
		stmt.execute(sql);
		
		sql = "Create table Usuario (Id_Usuario INT AUTO_INCREMENT NOT NULL PRIMARY KEY, Username varchar(50), NomeCompleto varchar(50), Email varchar(50), Senha varchar(30), Administrador integer);";
		stmt.execute(sql);
		
		sql = "CREATE TABLE Reserva (Id  INT NOT NULL AUTO_INCREMENT PRIMARY KEY, Relacao integer, Id_Sala INT NOT NULL, Id_Usuario INT NOT NULL, Data Date, Confirmado bool, FOREIGN KEY (Id_Sala) REFERENCES Sala(Id_Sala), FOREIGN KEY (Id_Usuario) REFERENCES Usuario(Id_Usuario));";
		stmt.execute(sql);
		
		sql = "INSERT INTO Usuario (NomeCompleto, Senha, Administrador) value ('adm','adm','1'); ";
		stmt.execute(sql);
		
		stmt.close();
		con.close();	
		
		
	}
	

}
