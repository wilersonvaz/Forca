package com.example.forca.ModelView;

import android.util.Log;

import com.example.forca.Model.Identificador;
import com.example.forca.Telas.JogoActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class IdentificadorView {
	Identificador identificador;
    String urlPalavra;

    public IdentificadorView(Identificador identificador) {
        this.identificador = identificador;
    }


    public String getUrlPalavra() {
        StringBuffer buffer = new StringBuffer();
        try {

            String urlIdentificador = identificador.getUrlIdentificador();

            Log.i("Log # ", "urlIdentificador: "+urlIdentificador);

            URL url = new URL(urlIdentificador);

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

            String s = buffer.toString();

            s = s.replace("[","");
            s = s.replace("]","");
            ArrayList<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));

            Random random = new Random();
            int palavra = Integer.parseInt(myList.get(random.nextInt(myList.size())).trim());

            // urlPalavra = "http://nobile.pro.br/forcaws/palavra/"+palavra;
            urlPalavra = JogoActivity.urlPalavraFinal+palavra;

            Log.i("Log # ", "urlPalavra: "+urlPalavra);

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
        }
        // try {

        //     String urlIdentificador = identificador.getUrlIdentificador();

        //     Log.i("Log # ", "urlIdentificador: "+urlIdentificador);

        //     URL url = new URL(urlIdentificador);

        //     HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        //     urlConnection.setRequestMethod("GET");
        //     urlConnection.connect();

        //     InputStream inputStream = urlConnection.getInputStream();
        //     if (inputStream == null) {
        //        return null;
        //     }

        //     BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        //     String linha;
        //     while ((linha = reader.readLine()) != null) {
        //         try {
        //             buffer.append(linha);
        //         } catch (Exception e) {
        //             e.printStackTrace();
        //         }
        //     }

        //     String s = buffer.toString();

        //     s = s.replace("[","");
        //     s = s.replace("]","");
        //     ArrayList<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));

        //     Random random = new Random();
        //     int palavra = Integer.parseInt(myList.get(random.nextInt(myList.size())).trim());

        //     urlPalavra = "http://nobile.pro.br/forcaws/palavra/"+palavra;

        //     Log.i("Log # ", "urlPalavra: "+urlPalavra);

        //     reader.close();

        //     if (buffer.length() == 0) {
        //        return null;
        //     }

        //     if (urlConnection != null) {
        //         urlConnection.disconnect();
        //     }

        // } catch (ProtocolException e) {
        //     Log.i("Log #", "ProtocolException "+String.valueOf(e));
        //     e.printStackTrace();
        // } catch (MalformedURLException e) {
        //     Log.i("Log #", "MalformedURLException "+String.valueOf(e));
        //     e.printStackTrace();
        // } catch (IOException e) {
        //     Log.i("Log #", "IOException "+String.valueOf(e));
        //     e.printStackTrace();
        // }catch (Exception e){
        //     Log.i("Log #", "Exception "+String.valueOf(e));
        //     e.printStackTrace();
        // }
        //     Log.i("Log #", "IOException "+String.valueOf(e));
        //     e.printStackTrace();
        // }catch (Exception e){
        //     Log.i("Log #", "Exception "+String.valueOf(e));
        //     e.printStackTrace();
        // }
        // String urlPalavra = "";

        return urlPalavra;

    }
}




// package com.example.forca.ModelView;

// import android.app.ProgressDialog;
// import android.os.AsyncTask;
// import android.util.Log;

// import com.example.forca.Model.Identificador;
// import com.example.forca.Telas.JogoActivity;
// import com.example.forca.Telas.MainActivity;

// import java.io.BufferedReader;
// import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
// import java.net.HttpURLConnection;
// import java.net.MalformedURLException;
// import java.net.ProtocolException;
// import java.net.URL;
// import java.util.ArrayList;
// import java.util.Arrays;
// import java.util.Random;

// public class IdentificadorView_ extends AsyncTask<String, String, String> {
//     Identificador identificador;
//     String urlPalavra;

//     public IdentificadorView(Identificador identificador) {
//         this.identificador = identificador;
//     }

//     public String getUrlPalavra() {
//         return urlPalavra;
//     }

//     public void setUrlPalavra(String urlPalavra) {
//         Log.i("Log # ", "Recebendo: "+urlPalavra+" no setUrl");
//         this.urlPalavra = urlPalavra;
//     }

//     @Override
//     protected void onPreExecute() {
//         super.onPreExecute();
// //            To
// //            pDialog = new ProgressDialog(NewProductActivity.this);
// //            pDialog.setMessage("Obtendo informações do servidor..");
// //            pDialog.setIndeterminate(false);
// //            pDialog.setCancelable(true);
// //            pDialog.show();
//     }

//     @Override
//     protected String doInBackground(String... strings) {
//         StringBuffer buffer = new StringBuffer();
//         try {

//             String urlIdentificador = identificador.getUrlIdentificador();

//             URL url = new URL(urlIdentificador);

//             HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//             urlConnection.setRequestMethod("GET");
//             urlConnection.connect();

//             InputStream inputStream = urlConnection.getInputStream();
//             if (inputStream == null) {
//                return null;
//             }

//             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
//             String linha;
//             while ((linha = reader.readLine()) != null) {
//                 try {
//                     buffer.append(linha);
//                 } catch (Exception e) {
//                     e.printStackTrace();
//                 }
//             }

//             String s = buffer.toString();

//             s = s.replace("[","");
//             s = s.replace("]","");
//             ArrayList<String> myList = new ArrayList<String>(Arrays.asList(s.split(",")));

//             Random random = new Random();
//             int palavra = Integer.parseInt(myList.get(random.nextInt(myList.size())).trim());

//             urlPalavra = "https://nobile.pro.br/forcaws/palavra/"+palavra;

//             reader.close();

//             if (buffer.length() == 0) {
//                return null;
//             }

//             if (urlConnection != null) {
//                 urlConnection.disconnect();
//             }

//         } catch (ProtocolException e) {
//             Log.i("Log #", "ProtocolException "+String.valueOf(e));
//             e.printStackTrace();
//         } catch (MalformedURLException e) {
//             Log.i("Log #", "MalformedURLException "+String.valueOf(e));
//             e.printStackTrace();
//         } catch (IOException e) {
//             Log.i("Log #", "IOException "+String.valueOf(e));
//             e.printStackTrace();
//         }catch (Exception e){
//             Log.i("Log #", "Exception "+String.valueOf(e));
//             e.printStackTrace();
//         }

//         return urlPalavra;
    
//     }

//     @Override
//     protected void onPostExecute(String s) {
//         super.onPostExecute(s);

//         Log.i("Log # ", "Passando: "+s);
//         this.setUrlPalavra(s);
//     }

    
// }

