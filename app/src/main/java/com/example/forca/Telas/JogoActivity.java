package com.example.forca.Telas;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forca.Model.Identificador;
import com.example.forca.Model.Jogo;
import com.example.forca.Model.Palavra;
import com.example.forca.ModelView.IdentificadorView;
import com.example.forca.ModelView.JogoView;
import com.example.forca.ModelView.PalavraView;
import com.example.forca.R;

public class JogoActivity extends AppCompatActivity {

    String urlPalavra, plvServidor, plvTela;
    String str;
    int nivelJogo;
    int qtdeRodadas;
    TextView txtPalavra, idRodadas;
    EditText edtLetra;
    // Button btnLetra;
    static int acertos = 0, erros = 0, contRodadas = 0, contJogadas = 0;
    public static ImageView resultadoImg, forca, corpo, cabeca, bracodireito, bracos, pernadireita, tronco;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        idRodadas = (TextView) findViewById(R.id.idRodadas);

        txtPalavra = (TextView) findViewById(R.id.txtPalavra);
        resultadoImg = (ImageView) findViewById(R.id.resultadoImg);
        
        buscaPalavraServidor();
    }

    private void buscaPalavraServidor(){
        try{
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                Log.i("Log # ", "Estamos na buscaPalavraServidor");
                
                nivelJogo = bundle.getInt("nivelJogo");
                qtdeRodadas = bundle.getInt("qtdeRodadas");

                idRodadas.setText("Rodada "+( contRodadas+1) );

                String urlIdentificador = "http://nobile.pro.br/forcaws/identificadores/"+nivelJogo;

                // http://nobile.pro.br/forcaws/palavra/232

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                //Usado para pegar o id de um identificar e após recuperar a palavra
                Identificador identificador = new Identificador();
                identificador.setNivelJogo(nivelJogo);
                identificador.setUrlIdentificador(urlIdentificador);

                IdentificadorView identificadorView = new IdentificadorView(identificador);
                urlPalavra = identificadorView.getUrlPalavra();

                Palavra palavra = new Palavra();
                palavra.setIdentificador(identificador);
                palavra.setUrlPalavra(urlPalavra);

                PalavraView palavraView = new PalavraView(palavra);
                //String que recebe a palavra do servidor
                plvServidor = palavraView.getDadosPalavraFromServidor().getPalavra();
                
                Jogo jogo = new Jogo();
                jogo.setPalavra(palavra);

                JogoView jogoview = new JogoView();
                plvTela = jogoview.criaPalavraTela(plvServidor);
                txtPalavra.setText( plvTela );
                
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void touchLetter(View v) {
        String letraDigitada = ((Button) v).getText().toString();
        Log.i("Log # ", "Letra digitada na touchLetter: "+letraDigitada);

        Jogo jogo = new Jogo();
        
        plvTela = txtPalavra.getText().toString();
        JogoView jogoview = new JogoView(plvServidor, plvTela, letraDigitada);
        if(contJogadas == 0){
          txtPalavra.setText( jogoview.criaPalavraTela( plvTela ) );  
        } 

        jogo = jogoview.jogar();
        

        if(!jogo.isAcertaLetra()){

            Log.i("Log # ", "Errou: "+erros+" vezes");
            switch(erros){
                case 0:
                    mudaImagem(cabeca, erros);
                    break;
                case 1:
                    mudaImagem(tronco, erros);
                    break;
                case 2:
                    mudaImagem(bracodireito, erros);
                    break;
                case 3:
                    mudaImagem(bracos, erros);
                    break;
                case 4:
                    mudaImagem(pernadireita, erros);
                    break;
                case 5:
                    mudaImagem(corpo, erros);
                    break;                
                default:
                    mudaImagem(forca, 6);
            }

            erros++; 
            
            
            if(erros >= 6){
                Log.i("Log # ", "Errou: "+erros+" vezes, reiniciando o jogo!");                
                // mudaImagem(forca, erros);
                contRodadas++;
                buscaPalavraServidor();
                erros = 0;
                contJogadas = -1;
            }           
            

        }else{
            
            acertos++;
            txtPalavra.setText( jogo.getLetraTl() );   
            int contaLetras = txtPalavra.getText().toString().replace("_","").length();
            Log.i("Log # ", "QtdeLetras: "+jogo.getQtdeLetras()+" acertos: "+acertos+" contaLetras: "+contaLetras);
            
            if(contaLetras == jogo.getQtdeLetras()){
                Log.i("Log # ", "Acertou "+acertos+" vezes, reiniciando o jogo!");                
                contRodadas++;
                mudaImagem(forca, 6);
                buscaPalavraServidor();
                acertos = 0;
            }     
                
        }

        contJogadas++; 
        
    }


    public static void mudaImagem(ImageView image, int img){
        try{
            Log.i("Log # ", "img: "+img);

            int[] imagensIds = {

                    // R.drawable.corpo, //1
                    R.drawable.cabeca, //0
                    R.drawable.tronco, //1
                    R.drawable.bracodireito, //2
                    R.drawable.bracos, //3
                    R.drawable.pernadireita, //4
                    R.drawable.corpo, //5
                    R.drawable.forca, //6
                    
            };

            resultadoImg.setImageResource(imagensIds[img]);

        }catch (Exception e){
            e.printStackTrace();
        }
    }


}