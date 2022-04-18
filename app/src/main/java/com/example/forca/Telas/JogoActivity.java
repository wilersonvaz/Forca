package com.example.forca.Telas;

import android.content.Intent;
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
import com.example.forca.Model.Relatorio;
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
    int acertaLetra = 0, erroLetra = 0, contRodadas = 0, contJogadas = 0, acertaPalavra = 0, erroPalavra = 0, tentativas = 0;
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

            }

            if(qtdeRodadas > 0){

                
                //Se as rodadas jogadas forem iguais à quantidade escolhia finaliza o jogo e vai para o relatório
                // if(contRodadas == qtdeRodadas){
//                     // Toast.makeText(getApplicationContext(),"Rodadas: "+qtdeRodadas+" acertaPalavra: "+acertaPalavra+" erroPalavra: "+erroPalavra , Toast.LENGTH_LONG).show();
//                     Log.i("Log # ", "Rodadas: "+qtdeRodadas+" acertaPalavra: "+acertaPalavra+" erroPalavra: "+erroPalavra);

//                     Relatorio relatorio = new Relatorio(acertaPalavra, erroPalavra, contJogadas, qtdeRodadas);
//                     // relatorio.setacertaLetra(acertaLetra);
//                     // relatorio.seterroLetra(erroLetra);
//                     // relatorio.setTentativas(contJogadas);
//                     // relatorio.setTotalDeJogadas(qtdeRodadas);

//                     // RelatorioView relatorioView = new RelatorioView(relatorio);

// //                    Bundle b = new Bundle();
// //                    b.putExtra("relatorio", relatorio);

//                     Intent intent = new Intent(getApplicationContext(), RelatorioActivity.class);
//                     intent.putExtra("relatorio", relatorio);
//                     startActivity(intent);
                // }else{
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

                    
                // }

                
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void touchLetter(View v) {
        String letraDigitada = ((Button) v).getText().toString();
        
        if(contRodadas < qtdeRodadas){

            Jogo jogo = new Jogo();
        
            plvTela = txtPalavra.getText().toString();
            JogoView jogoview = new JogoView(plvServidor, plvTela, letraDigitada);
            if(contJogadas == 0){
              txtPalavra.setText( jogoview.criaPalavraTela( plvTela ) );  
            } 

            jogo = jogoview.jogar();
            

            if(!jogo.isAcertaLetra()){

                Log.i("Log # ", "Errou: "+erroLetra+" vezes");
                switch(erroLetra){
                    case 0:
                        mudaImagem(cabeca, erroLetra);
                        break;
                    case 1:
                        mudaImagem(tronco, erroLetra);
                        break;
                    case 2:
                        mudaImagem(bracodireito, erroLetra);
                        break;
                    case 3:
                        mudaImagem(bracos, erroLetra);
                        break;
                    case 4:
                        mudaImagem(pernadireita, erroLetra);
                        break;
                    case 5:
                        mudaImagem(corpo, erroLetra);
                        break;                
                    default:
                        mudaImagem(forca, 6);
                }

                erroLetra++; 
                
                
                if(erroLetra >= 6){
                    Log.i("Log # ", "Errou: "+erroLetra+" vezes, reiniciando o jogo!");                
                    mudaImagem(forca, erroLetra);
                    contRodadas++;
                    buscaPalavraServidor();
                    erroLetra = 0;
                    acertaLetra = 0;
                    contJogadas = -1;
                    erroPalavra++;
                }           
                

            }else{
                
                acertaLetra++;
                txtPalavra.setText( jogo.getLetraTl() );   
                int contaLetras = txtPalavra.getText().toString().replace("_","").length();
                Log.i("Log # ", "QtdeLetras: "+jogo.getQtdeLetras()+" acertaLetra: "+acertaLetra+" contaLetras: "+contaLetras);
                
                if(contaLetras == jogo.getQtdeLetras()){
                                   
                    contRodadas++;
                    mudaImagem(forca, 6);
                    buscaPalavraServidor();
                    erroLetra = 0;
                    acertaLetra = 0;
                    acertaPalavra++;

                    Log.i("Log # ", "Acertou "+acertaLetra+" vezes, acertou "+acertaPalavra+" palavras, reiniciando o jogo!"); 
                }     
                    
            }

            contJogadas++; 
            tentativas++;

            


        }else{

            Log.i("Log # ", "Rodadas: "+qtdeRodadas+", Rodadas jogadas: "+contRodadas+" acertaPalavra: "+acertaPalavra+" erroPalavra: "+erroPalavra);

            Relatorio relatorio = new Relatorio(acertaPalavra, erroPalavra, tentativas, qtdeRodadas);
            Intent intent = new Intent(getApplicationContext(), RelatorioActivity.class);
            intent.putExtra("relatorio", relatorio);
            startActivity(intent);

            
            // relatorio.setacertaLetra(acertaLetra);
            // relatorio.seterroLetra(erroLetra);
            // relatorio.setTentativas(contJogadas);
            // relatorio.setTotalDeJogadas(qtdeRodadas);

            // RelatorioView relatorioView = new RelatorioView(relatorio);

//                    Bundle b = new Bundle();
//                    b.putExtra("relatorio", relatorio);

            
        }
        
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