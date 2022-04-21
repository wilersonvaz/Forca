package com.example.forca.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.util.ArrayList;
import java.util.Random;

public class JogoActivity extends AppCompatActivity {

    String urlPalavra, plvServidor, plvTela;
    String str;
    int nivelJogo;
    int qtdeRodadas;
    TextView txtPalavra, idRodadas, txtIdMensagem, txtIdTentativas, txtIdNovaPalavra;
    EditText edtLetra;
    // Button btnLetra;
    int acertaLetra = 0, erroLetra = 0, contRodadas = 0, contJogadas = 0, acertaPalavra = 0, erroPalavra = 0, tentativas = 0;
    public static ImageView resultadoImg, forca, corpo, cabeca, bracodireito, bracos, pernadireita, tronco;
    String[] arrayErrorMessage = {"Ah não, errou de novo!", "KKKK... errou!", "Tente de novo, essa você errou!","Como diria o Faustão, errrrou....", "Oh-no, oh-no, oh-no no no no no", "Não, essa letra não existe!", "Talvez na próxima você consiga!"};
    String[] arrayAcertaMessage = {"Essa você acertou!", "Boa, acertou mais uma!","Ai sim, você é fera! Acerta todas!", "Parabéns, acertou de novo!","You are the champion","Boa!", "Parabéns!"};
    public static String urlIdentificador = "http://nobile.pro.br/forcaws/identificadores/";
    public static String urlPalavraFinal = "http://nobile.pro.br/forcaws/palavra/";
    ArrayList idPalavraJaDigitada = new ArrayList();
    boolean jogando = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        idRodadas = (TextView) findViewById(R.id.idRodadas);
        txtIdTentativas = (TextView) findViewById(R.id.txtIdTentativas);
        txtIdNovaPalavra = (TextView) findViewById(R.id.txtIdNovaPalavra);
        txtPalavra = (TextView) findViewById(R.id.txtPalavra);
        resultadoImg = (ImageView) findViewById(R.id.resultadoImg);
        txtIdMensagem = (TextView) findViewById(R.id.txtIdMensagem);
        
        buscaPalavraServidor();

    }

    private void buscaPalavraServidor(){
        try{
            Bundle bundle = getIntent().getExtras();
            if (bundle != null) {
                Log.i("Log # ", "Estamos na buscaPalavraServidor");
                
                nivelJogo = bundle.getInt("nivelJogo");
                qtdeRodadas = bundle.getInt("qtdeRodadas");

            
                // String urlIdentificador = "http://nobile.pro.br/forcaws/identificadores/"+nivelJogo;

                // http://nobile.pro.br/forcaws/palavra/232

                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
                StrictMode.setThreadPolicy(policy);

                int palavraJaDigitada = 0;
                

                //Usado para pegar o id de um identificar para recuperar a palavra
                Identificador identificador = new Identificador();
                identificador.setNivelJogo(nivelJogo);
                identificador.setUrlIdentificador(urlIdentificador+nivelJogo);

                IdentificadorView identificadorView = new IdentificadorView(identificador);
                urlPalavra = identificadorView.getUrlPalavra();

                Palavra palavra = new Palavra();
                palavra.setIdentificador(identificador);
                palavra.setUrlPalavra(urlPalavra);

                PalavraView palavraView = new PalavraView(palavra);
                //String que recebe a palavra do servidor

                Log.i("Log # ","idPalavraJaDigitada: "+idPalavraJaDigitada );
                boolean palavraJaUsada = false;
                while(!palavraJaUsada){
                    Palavra p = palavraView.getDadosPalavraFromServidor();
                    plvServidor = p.getPalavra();
                    
                    Jogo jogo = new Jogo();
                    jogo.setPalavra(palavra);

                    JogoView jogoview = new JogoView();
                    plvTela = jogoview.criaPalavraTela(plvServidor);
                    txtPalavra.setText( plvTela );

                    Log.i("Log # ","inserindo idPalavraJaDigitada: "+idPalavraJaDigitada );
                    if(!idPalavraJaDigitada.contains(p.getIdPalavra())){
                        idPalavraJaDigitada.add(p.getIdPalavra());
                        
                        idRodadas.setText("Rodada "+( contRodadas+1) );

                        palavraJaUsada = true;
                    }else{
                        Log.i("Log # ","idPalavraJaDigitada já usada, procurando de novo: "+idPalavraJaDigitada );
                    
                    }
                    
                }    

                
                jogando = true;
            }

        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public void onClick(View v) {
        if(contRodadas == qtdeRodadas){
                
            Log.i("Log # ", "indo para o relatório");
            Relatorio relatorio = new Relatorio(acertaPalavra, erroPalavra, tentativas, qtdeRodadas);
            Intent intent = new Intent(getApplicationContext(), RelatorioActivity.class);
            intent.putExtra("relatorio", relatorio);
            startActivity(intent);

        }else{
            txtIdNovaPalavra.setTextColor(getResources().getColor(R.color.black));
            txtIdNovaPalavra.setClickable(false); 
            txtIdMensagem.setText("Boa sorte!");
            mudaImagem(forca, 6);
            buscaPalavraServidor();    
        }  
        
    }

    public void touchLetter(View v) throws InterruptedException {

        try{
        
            if(jogando){
                Random random = new Random();

                int errorMessage = random.nextInt(arrayErrorMessage.length);

                int acertaMessage = random.nextInt(arrayAcertaMessage.length);

                String letraDigitada = ((Button) v).getText().toString();
                
                // Jogo jogo = new Jogo();
            
                plvTela = txtPalavra.getText().toString();
                JogoView jogoview = new JogoView(plvServidor, plvTela, letraDigitada);
                if(contJogadas == 0){
                  txtPalavra.setText( jogoview.criaPalavraTela( plvTela ) );  
                } 

                Jogo jogo = jogoview.jogar();            


                if(!jogo.isAcertaLetra()){

                    txtIdMensagem.setText(arrayErrorMessage[errorMessage]);

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
                        
                        txtIdNovaPalavra.setTextColor(getResources().getColor(R.color.botaoNovoJogo));
                        txtIdNovaPalavra.setClickable(true);
                        // mudaImagem(forca, erroLetra);
                        contRodadas++;                    
                        erroLetra = 0;
                        acertaLetra = 0;
                        contJogadas = -1;
                        erroPalavra++;
                        txtIdMensagem.setText("Essa não deu, clique em Próxima para procurar outra palavra!");
                        jogando = false;
                        
                    }           
                    

                }else{
                    
                    acertaLetra++;
                    txtPalavra.setText( jogo.getLetraTl() );   
                    int contaLetras = txtPalavra.getText().toString().replace("_","").length();
                    txtIdMensagem.setText(arrayAcertaMessage[acertaMessage]);
                    
                    if(contaLetras == jogo.getQtdeLetras()){
                        
                        txtIdNovaPalavra.setTextColor(getResources().getColor(R.color.botaoNovoJogo));                    
                        txtIdNovaPalavra.setClickable(true);             
                        contRodadas++;
                        // mudaImagem(forca, 6);            
                        erroLetra = 0;
                        acertaLetra = 0;
                        acertaPalavra++;
                        txtIdMensagem.setText("Você acertou, clique em Próxima para procurar outra palavra!");
                        jogando = false;
                        
                    }     
                        
                }

                contJogadas++; 
                tentativas++;    
                Log.i("Log # ", "contRodadas "+contRodadas);
            
                txtIdTentativas.setText(String.valueOf( tentativas )+" tentativas ");    
            }

            

            
        }catch(Exception e){
            e.printStackTrace();
        }     
                    
        
        
    }


    public static void mudaImagem(ImageView image, int img){
        try{
            
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