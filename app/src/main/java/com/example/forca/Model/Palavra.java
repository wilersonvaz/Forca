package com.example.forca.Model;

public class Palavra {
    Identificador identificador;
    int idPalavra;
    String palavra;
    String urlPalavra;
    int letras;

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

    public int getLetras() {
        return letras;
    }

    public void setLetras(int letras) {
        this.letras = letras;
    }
}
