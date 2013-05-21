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
	
	public void criaTabela() throws ClassNotFoundException, SQLException {
		
		con = Conexao.conexao();
		stmt = con.createStatement();
		
		sql = "Drop table if exists Sala;";
		stmt.execute(sql);
		
		sql = "Create table Sala (Id_Sala INT NOT NULL PRIMARY KEY, Local Varchar(20));";
		stmt.execute(sql);
		
		stmt.close();
		con.close();	
		
		
	}
	

}
