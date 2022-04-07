package com.example.forca.ModelView;

import android.util.Log;

import com.example.forca.Model.Palavra;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;


public class PalavraView {
    Palavra palavra;
    public PalavraView(Palavra palavra) {
        this.palavra = palavra;
    }

    public Palavra getDadosPalavraFromServidor() {
        String s = null;
        String pl = null;
        StringBuffer buffer = new StringBuffer();
        try {

            String urlPalavra = palavra.getUrlPalavra();
            
            URL url = new URL(urlPalavra);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            if (inputStream == null) {
               return null;
            }

            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String linha;
            while ((linha = reader.readLine()) != null) {
                try {
                    buffer.append(linha);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            s = buffer.toString();

            s = s.replace("[","");
            s = s.replace("]","");

            JSONObject jsonObj = new JSONObject(s);

            palavra.setIdPalavra(Integer.parseInt( jsonObj.getString("Id") ));
            palavra.setPalavra( jsonObj.getString("Palavra") );
            palavra.setLetras(Integer.parseInt( jsonObj.getString("Letras") ));
            palavra.getIdentificador().setNivelJogo( Integer.parseInt( jsonObj.getString("Nivel") ));

            
            reader.close();


            if (buffer.length() == 0) {
               return null;
            }

            if (urlConnection != null) {
                urlConnection.disconnect();
            }

        } catch (ProtocolException e) {
            Log.i("Log #", "ProtocolException "+String.valueOf(e));
            e.printStackTrace();
        } catch (MalformedURLException e) {
            Log.i("Log #", "MalformedURLException "+String.valueOf(e));
            e.printStackTrace();
        } catch (IOException e) {
            Log.i("Log #", "IOException "+String.valueOf(e));
            e.printStackTrace();
        }catch (Exception e){
            Log.i("Log #", "Exception "+String.valueOf(e));
            e.printStackTrace();
        }


        return palavra;
    }
}
