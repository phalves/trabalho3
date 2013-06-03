package br.com.trab3.Model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Reserva {
	
	private int IdReserva;
	private int Relacao;
	private int IdSala;
	private int IdUsuario;
	private Date Data;
	private int Confirmado;
	
	public int getIdReserva() {
		return IdReserva;
	}
	public void setIdReserva(int idReserva) {
		IdReserva = idReserva;
	}
	public int getRelacao() {
		return Relacao;
	}
	public void setRelacao(int relacao) {
		Relacao = relacao;
	}
	public int getIdSala() {
		return IdSala;
	}
	public void setIdSala(int idSala) {
		IdSala = idSala;
	}
	public int getIdUsuario() {
		return IdUsuario;
	}
	public void setIdUsuario(int idUsuario) {
		IdUsuario = idUsuario;
	}
	public Date getData() {
		return Data;
	}
	public String getDataString() {
		SimpleDateFormat outputDf = new SimpleDateFormat("dd/MM/yyyy HH");
		return outputDf.format(this.getData());
	}
	public void setData(Date data) {
		Data = data;
	}
	public int getConfirmado() {
		return Confirmado;
	}
	public void setConfirmado(int confirmado) {
		Confirmado = confirmado;
	}
	

}
