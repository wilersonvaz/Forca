package com.example.forca.ModelView;

import com.example.forca.Model.Jogo;

import java.util.ArrayList;

public class JogoView<jogo> {
	String plvServidor, plvTela, letraDigitada;
	

	public JogoView() {

	}

	public JogoView(String plvServidor, String plvTela, String letraDigitada) {
		this.plvServidor = plvServidor;
		this.plvTela = plvTela;
		this.letraDigitada = letraDigitada;
	}

	

	public Jogo jogar(){
		Jogo jogo = new Jogo();
		
		try{
			// Log.i("Log # ", "Entrou no JogoView plvServidor: "+this.plvServidor+" plvTela: "+this.plvTela+" letraDigitada: "+this.letraDigitada);
			
			
			this.plvTela = this.plvTela.replaceAll(" ","");
			// Log.i("Log # ", "plvTela: "+this.plvTela);
			
			ArrayList<String> arrayPalavra = new ArrayList<String>();

			for (int i = 0; i <  this.plvTela.length(); i++){
				arrayPalavra.add( Character.toString( this.plvTela.charAt(i) ) );
				// Log.i("Log # ", "arrayPalavra iniciando com : "+arrayPalavra.get(i));
			}

			if((plvServidor.contains(letraDigitada) || plvServidor.contains( letraDigitada.toLowerCase() )) ){
				// Log.i("Log # ", "A letra "+letraDigitada+" existe e a plvTela agora é: "+plvTela);
				
				for(int i = 0; i < this.plvServidor.length(); i++){
					// Log.i("Log # ", "Letra do servidor: "+this.plvServidor.charAt(i)+" "+Character.toLowerCase( this.plvServidor.charAt(i) )+" substring: "+this.plvServidor.substring(i,i+1)+" letra digitada: "+letraDigitada);
					
		 			if( ( letraDigitada.equals( Character.toString( this.plvServidor.charAt(i) ))) || (letraDigitada.toLowerCase().equals( Character.toString( this.plvServidor.charAt(i) )))) {
						// Log.i("Log # ", "A letra "+letraDigitada+" existe, Adicionando a letra: "+Character.toString( this.plvServidor.charAt(i))+" no arrayPalavra");
						arrayPalavra.set(i, Character.toString( this.plvServidor.charAt(i)) );
					}					
				}
				
				String newString = "";
				
				for (int i = 0; i <  this.plvTela.length(); i++){
					newString += arrayPalavra.get(i);					
				}
				
				jogo.setLetraTl(newString);
				jogo.setQtdeLetras(this.plvServidor.length());
				jogo.setAcertaLetra(true);				
				
			}else{
				// Log.i("Log # ", "A letra "+letraDigitada+" não existe");
				jogo.setAcertaLetra(false);
			}

			

		}catch(Exception e){
			e.printStackTrace();
		}

		return jogo;
	}

	public String criaPalavraTela(String sTring){
        String str = "";
        try{
            for(int i = 0; i < sTring.length(); i++){
                str+="_";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }

}
