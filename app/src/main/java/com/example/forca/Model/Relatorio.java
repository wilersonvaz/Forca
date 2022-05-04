package com.example.forca.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Relatorio implements Serializable {
	private Palavra palavra;
	private int acertos;
	private int erros;
	private int tentativas;
	private int totalDeJogadas;
	ArrayList<Palavra> arrayPalavra;

	
	public Relatorio(ArrayList<Palavra> arrayPalavra, int acertos, int erros, int tentativas, int qtdeRodadas) {
		this.arrayPalavra = arrayPalavra;
		this.acertos =acertos;
		this.erros = erros;
		this.tentativas = tentativas;
		this.totalDeJogadas = qtdeRodadas;
	}

	public Relatorio(Palavra palavra) {
		this.palavra = palavra;
	}

	public Relatorio() {

	}

	public ArrayList<Palavra> getArrayPalavra() {
		return arrayPalavra;
	}

	public void setArrayPalavra(ArrayList<Palavra> arrayPalavra) {
		this.arrayPalavra = arrayPalavra;
	}

	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.palavra = palavra;
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
