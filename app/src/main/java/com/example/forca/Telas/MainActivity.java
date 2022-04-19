package com.example.forca.Telas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.forca.R;

public class MainActivity extends AppCompatActivity {
    private Button btnConfigurar;
    private TextView txtIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtIniciar = (TextView) findViewById(R.id.txtIniciar);
           

    }


    public void onClick(View v) {
        Intent intent = new Intent(getApplicationContext(), ConfiguracoesActivity.class);
        // intent.putExtras(bundle);
        startActivity(intent);
    }


}