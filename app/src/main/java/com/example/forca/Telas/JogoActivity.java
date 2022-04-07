package com.example.forca.Telas;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

    String urlPalavra;
    String str;
    int nivelJogo;
    int qtdeRodadas;
    TextView txtPalavra;
    EditText edtLetra;
    Button btnLetra;
    static int erraPalavra = 0;
    static int acertaPalavra = 0;
    static int controle = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jogo);

        txtPalavra = (TextView) findViewById(R.id.txtPalavra);
        edtLetra = (EditText) findViewById(R.id.edtLetra);
        btnLetra = (Button) findViewById(R.id.btnLetra);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            nivelJogo = bundle.getInt("nivelJogo");
            qtdeRodadas = bundle.getInt("qtdeRodadas");

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
            String plv = palavraView.getDadosPalavraFromServidor().getPalavra();
            int qtdeLetras = palavraView.getDadosPalavraFromServidor().getLetras();
            geraPalavra(palavraView);
            Log.i("Log # ", "QtdeLetras: "+qtdeLetras);

            geraPalavra(palavraView);

            txtPalavra.setText( str );      

            boolean finish = false;


            // while(!finish){
            //     Toast.makeText(getApplicationContext(), acertaPalavra, Toast.LENGTH_LONG).show();
                btnLetra.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view) {

                        // if( !edtLetra.getText().toString().substring(0,qtdeLetras).matches("[A-Z]*") ){
                        // // Toast.makeText(getApplicationContext(), acertaPalavra, Toast.LENGTH_LONG).show();
                        //    Snackbar.make(view, "Por favor, digite apenas letras!", Snackbar.LENGTH_LONG)
                        //        .setAction("Action", null).show();
                        //     edtLetra.setText("");                          
                            
                        // }else{
                            if(controle > 0){
                                str = txtPalavra.getText().toString();
                            }
                            
                            Jogo jogo = new Jogo();
                            jogo.setPalavra(palavra);
                            jogo.setPalavraTl(str);
                            jogo.setLetraTl(edtLetra.getText().toString());
            //                jogo.setTentativa(n);

                            edtLetra.setText("");
                            
                            JogoView jogoView = new JogoView(jogo);
                            // String rt = jogoView.jogar().getPalavra().getIdentificador().getUrlIdentificador();

                            if(jogoView.jogar().isAcertaLetra() == false){
                                erraPalavra++;                                
                                txtPalavra.setText( jogoView.jogar().getLetraTl() );     
                                Log.i("Log # ", "Entrou no if do false");
                            }else{
                                txtPalavra.setText( jogoView.jogar().getLetraTl() ); 
                            }

                            Log.i("Log # ", "Passando Letra digitada: "+ edtLetra.getText().toString()+" "+erraPalavra);
                        // }
            
                        controle++;
                    }
                });

                if((erraPalavra == 5) || (acertaPalavra == (qtdeLetras-1))){
                    finish = true;
                }

                Log.i("Log # ", "Finish: "+ finish);
                acertaPalavra++;
            // }


//            //Usado para pegar o id de um identificar e após recuperar a palavra
//            Identificador identificador = new Identificador();
//            identificador.setNivelJogo(nivelJogo);
//            identificador.setUrlIdentificador(urlIdentificador);
//
//            IdentificadorView identificadorView = new IdentificadorView(identificador);
//            urlPalavra = identificadorView.getUrlPalavra();
//
//            Palavra palavra = new Palavra();
//            palavra.setIdentificador(identificador);
//            palavra.setUrlPalavra(urlPalavra);
//
//            PalavraView palavraView = new PalavraView(palavra);
//            String plv = palavraView.getDadosPalavraFromServidor().getPalavra();
//            geraPalavra(palavraView);
//            txtPalavra.setText( str );
        }
    }

    protected String geraPalavra(PalavraView palavraView){
        str = "";
        try{
            for(int i = 0; i < palavraView.getDadosPalavraFromServidor().getLetras(); i++){
                str+="_ ";
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return str;
    }

}