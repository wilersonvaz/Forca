package com.example.forca.Telas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

import com.example.forca.R;

public class MainActivity extends AppCompatActivity {
    private Button btnJogar;
    private RadioButton idFacil, idMedio, idDificil;
    private EditText qtdeRodadas;
    private int nivelJogo = 1;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnJogar = (Button) findViewById(R.id.btnJogar);
        idFacil = (RadioButton) findViewById(R.id.idFacil);
        idMedio = (RadioButton) findViewById(R.id.idMedio);
        idDificil = (RadioButton) findViewById(R.id.idDificil);
        qtdeRodadas = (EditText) findViewById(R.id.idQtdeRodadas);

        btnJogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
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
//                bundle.putInt("qtdeRodadas", Integer.parseInt( qtdeRodadas.getText().toString() ));
                bundle.putInt("qtdeRodadas", 7);

                
                Intent intent = new Intent(getApplicationContext(), JogoActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
           

    }


//    public void onRadioButtonClicked(View view) {
//        // Is the button now checked?
//        boolean checked = ((RadioButton) view).isChecked();
//
//        // Check which radio button was clicked
//        switch(view.getId()) {
//            case R.id.idFacil:
//                if (checked)
//                    nivelJogo = 1;
//                    break;
//            case R.id.idMedio:
//                if (checked)
//                    nivelJogo = 2;
//                    break;
//            case R.id.idDificil:
//                if (checked)
//                    nivelJogo = 3;
//                    break;
//        }
//    }
//    }
}