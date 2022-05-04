package com.example.forca.Adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forca.Model.Palavra;
import com.example.forca.Model.Relatorio;
import com.example.forca.R;

import java.util.ArrayList;

public class RelatorioAdapter extends RecyclerView.Adapter<RelatorioAdapter.RelatorioItem>{
    ArrayList<Palavra> palavra;
    Relatorio relatorio;
    
    public RelatorioAdapter(ArrayList<Palavra> palavra) {
        this.palavra = palavra;
    }

    @NonNull
    @Override
    public RelatorioItem onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_relatorio_detalhado, null, false);
        // view.setOnClickListener(this);
        return new RelatorioItem(view);
        // return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RelatorioItem holder, int position) {
        holder.txtPalavra.setText(palavra.get(position).getPalavra());
        holder.txtIndicePalavra.setText(String.valueOf(position+1)+"º Palavra");
        
        if(!palavra.get(position).isAcertou()){
            holder.certoErrado.setImageResource(R.drawable.sad);
            holder.txtAcerto.setText("Essa você errou!");
            holder.txtFaixaDecorativa.setBackgroundResource(R.color.vermelho);            
            
        }else{
            holder.certoErrado.setImageResource(R.drawable.smile);
            holder.txtAcerto.setText("Essa você acertou!");
            holder.txtFaixaDecorativa.setBackgroundResource(R.color.azul);
        }
    }

    @Override
    public int getItemCount() {
        return palavra.size();
    }


    public class RelatorioItem extends RecyclerView.ViewHolder {
        TextView txtPalavra;
        TextView txtAcerto;
        TextView txtIndicePalavra;
        TextView txtFaixaDecorativa;
        ImageView certoErrado;
        // TextView txtQtdeTentativas;

        public RelatorioItem(@NonNull View itemView) {
            super(itemView);

            txtPalavra = itemView.findViewById(R.id.txtPalavra);
            txtAcerto = itemView.findViewById(R.id.txtAcerto);
            txtIndicePalavra = itemView.findViewById(R.id.txtIndicePalavra);
            txtFaixaDecorativa = itemView.findViewById(R.id.txtFaixaDecorativa);
            certoErrado = itemView.findViewById(R.id.certoErrado);
            // txtQtdeTentativas = itemView.findViewById(R.id.txtQtdeTentativas);
        }
    }
}
