package com.example.daviderondana.myapplication;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class Connection {
    String utente, password, action, booksString;
    ArrayList<JSONObject> books;
    ProgressBar progressBar;
    String indirizzoIp = "192.168.0.124";

    public Connection() {
    }

    public void setAction(String action) {
        this.action = action;
    }

    public void setUtente(String utente) {
        this.utente = utente;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public class ManageRequest extends AsyncTask<String, Void, String> {

        private final String LOG_TAG = ManageRequest.class.getSimpleName();

        @Override
        protected String doInBackground(String... params) {
            // If there's no zip code, there's nothing to look up.  Verify size of params.
            if (params.length == 0) {
                return null;
            }
            // These two need to be declared outside the try/catch
            // so that they can be closed in the finally block.
            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            try {
                URL url = new URL("http://" + indirizzoIp + ":8080/WebApplication1/service");
                DataOutputStream dataOutputStream;
                InputStream inputStream;
                String parameter;

                // Create the request to Servets, and open the connection

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("POST");
                urlConnection.setRequestProperty("action", action);
                urlConnection.setRequestProperty("utente", utente);
                urlConnection.setRequestProperty("password", password);
                urlConnection.setConnectTimeout(7000);

                dataOutputStream = new DataOutputStream(urlConnection.getOutputStream());
                parameter = "action=" + URLEncoder.encode(action, "UTF-8") +
                        "&utente=" + URLEncoder.encode(utente, "UTF-8") +
                        "&password=" + URLEncoder.encode(password, "UTF-8");

                dataOutputStream.writeBytes(parameter);

                dataOutputStream.flush();
                dataOutputStream.close();

                urlConnection.connect();

                // Read the input stream into a String
                inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    buffer.append(line + "\n");
                }
                if (buffer.length() == 0) {
                    return null;
                }
                booksString = buffer.toString();

            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
                System.out.println(e.getMessage());
                return null;
            } finally {
                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            return booksString;
        }
    }

    public String start() throws ExecutionException, InterruptedException {
        ManageRequest manage = new ManageRequest();
        return manage.execute("").get();
    }
}

