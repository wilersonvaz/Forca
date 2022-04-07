package com.example.forca.ModelView;

import android.util.Log;

import com.example.forca.Model.Jogo;

public class JogoView<jogo> {
	Jogo jogo;

	public JogoView(Jogo jogo) {
		this.jogo = jogo;
	}

	public Jogo jogar(){
		try{
			Log.i("Log # ", "JogoView: "+jogo.getPalavra().getPalavra()+" "+jogo.getPalavraTl()+" "+jogo.getLetraTl() );
			
			String pl = jogo.getPalavra().getPalavra();
			String l = jogo.getLetraTl();
			String[] str = jogo.getPalavraTl().split(" ");
			String oldString = jogo.getPalavraTl().replaceAll(" ","");
			String newString = "";
			Log.i("Log # ", "oldString: "+oldString);
			char[] charArray = oldString.toCharArray();
			// for(int i = 0; i < jogo.getPalavraTl().length(); i++){
			// 	Log.i("Log # ", "A string separada: "+str[i]);
			// }
			if(pl.contains(l)){
				for(int i = 0; i < pl.length(); i++){
					if(pl.substring(i,i+1).equals(l)){
						charArray[i] = l.charAt(0);

						newString = String.valueOf(charArray);

						Log.i("Log # ", "newString: "+newString);
					}
				}
				jogo.setLetraTl(newString);
				jogo.setAcertaLetra(true);				
				
			}else{
				jogo.setAcertaLetra(false);
			}
			int i = jogo.getTentativa()+1;
			jogo.setTentativa(i);
		}catch(Exception e){
			e.printStackTrace();
		}

		return this.jogo;
	}


}
