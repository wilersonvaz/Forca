package com.example.forca.Model;

public class Jogo {
	Palavra palavra;
	private int tentativa;
	private int nivelJogo;
	private int corpo;
	//Recebem a palavra exposta na tela e letra digitada pelo usuário
	private String palavraTl;
	private String letraTl;
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

	public int getCorpo() {
		return corpo;
	}

	public void setCorpo(int corpo) {
		this.corpo = corpo;
	}

	public String getPalavraTl() {
		return palavraTl;
	}

	public void setPalavraTl(String palavraTl) {
		this.palavraTl = palavraTl;
	}

	public String getLetraTl() {
		return letraTl;
	}

	public void setLetraTl(String letraTl) {
		this.letraTl = letraTl;
	}

	public boolean isAcertaLetra() {
		return acertaLetra;
	}

	public void setAcertaLetra(boolean acertaLetra) {
		this.acertaLetra = acertaLetra;
	}
}
