package com.example.forca.Model;

import java.io.Serializable;

public class Palavra implements Serializable {
    Identificador identificador;
    int idPalavra;
    String palavra;
    String urlPalavra;
    int qtdeLetras;
    boolean acertou;

    public Palavra(int idPalavra, String palavra, int qtdeLetras) {
        this.idPalavra = idPalavra;
        this.palavra = palavra;
        this.qtdeLetras = qtdeLetras;
    }

    public Palavra() {

    }

    public Palavra(String palavra, Boolean acertouPalavra) {
        this.palavra = palavra;
        this.acertou = acertouPalavra;
    }

    public boolean isAcertou() {
        return acertou;
    }

    public void setAcertou(boolean acertou) {
        this.acertou = acertou;
    }

    public Identificador getIdentificador() {
        return identificador;
    }

    public void setIdentificador(Identificador identificador) {
        this.identificador = identificador;
    }

    public int getIdPalavra() {
        return idPalavra;
    }

    public void setIdPalavra(int idPalavra) {
        this.idPalavra = idPalavra;
    }

    public String getPalavra() {
        return palavra;
    }

    public void setPalavra(String palavra) {
        this.palavra = palavra;
    }

    public String getUrlPalavra() {
        return urlPalavra;
    }

    public void setUrlPalavra(String urlPalavra) {
        this.urlPalavra = urlPalavra;
    }

    public int getQtdeLetras() {
        return qtdeLetras;
    }

    public void setQtdeLetras(int qtdeLetras) {
        this.qtdeLetras = qtdeLetras;
    }
}
