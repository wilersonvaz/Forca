package com.example.forca.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forca.R;

public class ConfiguracoesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private Button btnJogar;
    private RadioButton idFacil, idMedio, idDificil;
    private EditText qtdeRodadas;
    private int nivelJogo = 1;
    private TextView txtJogar;
    private Spinner idQtdeRodadas;
    private int nroRodadas = 0;
    // int[] rodadas = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
    String[] rodadas = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuracoes);


        txtJogar = (TextView) findViewById(R.id.txtJogar);
        // btnJogar = (Button) findViewById(R.id.btnJogar);
        idFacil = (RadioButton) findViewById(R.id.idFacil);
        idMedio = (RadioButton) findViewById(R.id.idMedio);
        idDificil = (RadioButton) findViewById(R.id.idDificil);
        idQtdeRodadas = (Spinner) findViewById(R.id.idQtdeRodadas);

        idQtdeRodadas.setOnItemSelectedListener(this);

        ArrayAdapter aa = new ArrayAdapter(this, android.R.layout.simple_spinner_item, rodadas);

        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        idQtdeRodadas.setAdapter(aa);

        // qtdeRodadas = (EditText) findViewById(R.id.idQtdeRodadas);

        // btnJogar.setOnClickListener(new View.OnClickListener() {
        //     @Override
        //     public void onClick(View view) {
                
        //         if(idFacil.isChecked()){
        //             nivelJogo = 1;
        //         }

        //         if(idMedio.isChecked()){
        //             nivelJogo = 2;
        //         }

        //         if(idDificil.isChecked()){
        //             nivelJogo = 3;
        //         }

//                 Bundle bundle = new Bundle();
//                 bundle.putInt("nivelJogo", nivelJogo);
// //                bundle.putInt("qtdeRodadas", Integer.parseInt( qtdeRodadas.getText().toString() ));
//                 bundle.putInt("qtdeRodadas", 7);

//                 Intent intent = new Intent(getApplicationContext(), JogoActivity.class);
//                 intent.putExtras(bundle);
//                 startActivity(intent);
        //     }
        // });
           
    }

    public void onClick(View v) {
        try{
            if(idFacil.isChecked()){
                nivelJogo = 1;
            }

            if(idMedio.isChecked()){
                nivelJogo = 2;
            }

            if(idDificil.isChecked()){
                nivelJogo = 3;
            }

            Bundle bundle = new Bundle();
            bundle.putInt("nivelJogo", nivelJogo);
            bundle.putInt("qtdeRodadas", nroRodadas);

            Intent intent = new Intent(getApplicationContext(), JogoActivity.class);
            intent.putExtras(bundle);
            startActivity(intent);
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    //Performing action onItemSelected and onNothing selected  
    @Override  
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position, long id) {          
        nroRodadas = Integer.parseInt(rodadas[position]);
    }  
    @Override  
    public void onNothingSelected(AdapterView<?> arg0) {  
        // TODO Auto-generated method stub  
    }  
}