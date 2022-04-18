package com.example.forca.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forca.Model.Relatorio;
import com.example.forca.R;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RelatorioActivity extends AppCompatActivity {
    TextView txtHoraRelatorio;
    TextView txtRelatorioRelatorio;
    TextView txtDataRelatorio ;
    TextView txtQtdeAcertos;
    TextView txtQtdeErros;
    TextView txtTentativas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        txtHoraRelatorio = (TextView) findViewById(R.id.txtHoraRelatorio);
        txtRelatorioRelatorio = findViewById(R.id.txtRelatorioRelatorio);
        txtDataRelatorio = findViewById(R.id.txtDataRelatorio);
        txtQtdeAcertos = findViewById(R.id.txtQtdeAcertos);
        txtQtdeErros = findViewById(R.id.txtQtdeErros);
        txtTentativas = findViewById(R.id.txtTentativas);

        Date dataHoraAtual = new Date();

        String data = new SimpleDateFormat("dd/MM/yyyy").format(dataHoraAtual);
        String hora = new SimpleDateFormat("HH:mm").format(dataHoraAtual);

        txtHoraRelatorio.setText(hora);
        txtDataRelatorio.setText(data);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            Relatorio relatorio = (Relatorio) getIntent().getSerializableExtra("relatorio");
            Log.i("Log # ", "Total palavras certas: "+relatorio.getAcertos()+" erros: "+relatorio.getErros() );

            
            txtRelatorioRelatorio.setText(String.valueOf(relatorio.getTotalDeJogadas())+" Palavras");
            txtQtdeAcertos.setText(String.valueOf(relatorio.getAcertos())+" \n Acertos");
            txtQtdeErros.setText(String.valueOf(relatorio.getErros())+" \n Erros");
            txtTentativas.setText(String.valueOf(relatorio.getTentativas())+" \n Tentativas");
        }
        
    }

    public void onClick(View v){
        Intent intent;

        switch (v.getId()){
            case R.id.txtNovoJogo:
                intent = new Intent(getApplicationContext(), ConfiguracoesActivity.class);
                startActivity(intent);
                break;
            case R.id.txtFinalizar:
                intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}