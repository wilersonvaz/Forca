package com.example.forca.Model;

import java.io.Serializable;

public class Relatorio implements Serializable {
	private int acertos;
	private int erros;
	private int tentativas;
	private int totalDeJogadas;

	
	public Relatorio(int acertos, int erros, int tentativas, int qtdeRodadas) {

		this.acertos =acertos;
		this.erros = erros;
		this.tentativas = tentativas;
		this.totalDeJogadas = qtdeRodadas;
	}

	public int getAcertos() {
		return acertos;
	}

	public void setAcertos(int acertos) {
		this.acertos = acertos;
	}

	public int getErros() {
		return erros;
	}

	public void setErros(int erros) {
		this.erros = erros;
	}

	public int getTentativas() {
		return tentativas;
	}

	public void setTentativas(int tentativas) {
		this.tentativas = tentativas;
	}

	public int getTotalDeJogadas() {
		return totalDeJogadas;
	}

	public void setTotalDeJogadas(int totalDeJogadas) {
		this.totalDeJogadas = totalDeJogadas;
	}

	
}
