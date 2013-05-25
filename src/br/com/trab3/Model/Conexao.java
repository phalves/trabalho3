package br.com.trab3.Model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	public static Connection conexao() throws ClassNotFoundException, SQLException
	{
		String url;
		try {
			Class.forName("com.mysql.jdbc.Driver");

			/*
			Antes de entregar alterar para:

			String user = "aluno";
			String senha = "8752";

			url = "jdbc:mysql://Copacabana/Reserva";
			*/

			String user = "root";
			String senha = "server";

			url = "jdbc:mysql://139.82.24.103:3306/reserva";

			return DriverManager.getConnection(url, user, senha);
		} 

		catch (ClassNotFoundException e) {
			System.err.println("Classe nao encontrada: " + e.toString());
			throw e;
		} 

		catch(SQLException e)
		{
			System.err.println("Erro no SQL: " + e.toString());
			throw e;
		}	
	}
}