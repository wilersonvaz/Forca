package com.example.forca.Model;

public class Jogo {
	Palavra palavra;
	//As tentativas verificam se as letras digitas existem
	private int tentativa;
	//à cada rodada uma nova palavra
	private int rodada;
	private int nivelJogo;
	private int qtdeLetras;
	//Recebe a letra digitada pelo usuário;
	private String letraTl;
	//Recebe a palavra da tela, (as que resultam do jogo)
	private String palavraTl;
	//Verifica no final se o usuário acertou ou errou a letra
	private boolean acertaLetra;
	
	public Palavra getPalavra() {
		return palavra;
	}

	public void setPalavra(Palavra palavra) {
		this.palavra = palavra;
	}

	public int getTentativa() {
		return tentativa;
	}

	public void setTentativa(int tentativa) {
		this.tentativa = tentativa;
	}

	public int getNivelJogo() {
		return nivelJogo;
	}

	public void setNivelJogo(int nivelJogo) {
		this.nivelJogo = nivelJogo;
	}

	public int getRodada() {
		return rodada;
	}

	public void setRodada(int rodada) {
		this.rodada = rodada;
	}

	public String getLetraTl() {
		return letraTl;
	}

	public void setLetraTl(String letraTl) {
		this.letraTl = letraTl;
	}

	public String getPalavraTl() {
		return palavraTl;
	}

	public void setPalavraTl(String palavraTl) {
		this.palavraTl = palavraTl;
	}

	public boolean isAcertaLetra() {
		return acertaLetra;
	}

	public void setAcertaLetra(boolean acertaLetra) {
		this.acertaLetra = acertaLetra;
	}

	public int getQtdeLetras() {
		return qtdeLetras;
	}

	public void setQtdeLetras(int qtdeLetras) {
		this.qtdeLetras = qtdeLetras;
	}
}
