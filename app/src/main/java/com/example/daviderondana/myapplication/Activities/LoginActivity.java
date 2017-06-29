package com.example.daviderondana.myapplication.Activities;

import android.content.Context;
import android.content.Intent;
import android.os.StrictMode;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.daviderondana.myapplication.Model.Utente;
import com.example.daviderondana.myapplication.R;
import com.example.daviderondana.myapplication.StaticData;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    private StaticData staticData;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;

    private String username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        staticData = StaticData.getInstance();

        setTitle("Effettua il Login");

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);

        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);

        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == R.id.login || id == EditorInfo.IME_NULL) {
                    attemptLogin();
                    return true;
                }
                return false;
            }
        });

        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
    }

    private void attemptLogin() {
        // Store values at the time of the login attempt.
        mEmailView.setText("Rondana");
        mPasswordView.setText("Davide");

        username = mEmailView.getText().toString().trim();
        password = mPasswordView.getText().toString().trim();

        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());

        String url = "http://" + staticData.getIp() + ":8080/WebApplication1/biblioteca";

        StringRequest postRequest = new StringRequest(
                Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        ObjectMapper mapper = new ObjectMapper();
                        try {
                            Utente user = mapper.readValue(response, Utente.class);

                            // creo un bundle per passare dati tra 2 activity
                            Bundle bundle = new Bundle();
                            // metto l'oggetto che voglio passare dentro al bundle
                            bundle.putSerializable("utente", user);
                            // creo l'intent per cambiare activity
                            Intent intent = new Intent(getApplicationContext(), BenvenutoCliente.class);
                            // inserisco il bundle dentro all'intent
                            intent.putExtra("utente_loggato", bundle);

                            if (user.getRuolo().equals("cliente")) {
                                toast("Benvenuto Cliente!");
                            } else {
                                toast("Benvenuto Amministratore!");
                            }

                            staticData.setLogged(true);

                            // cambio activity
                            startActivity(intent);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        toast("Combinazione nome utente/password errata!");
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("action", "login");
                params.put("username", username);
                params.put("password", password);
                return params;
            }
        };

        queue.add(postRequest);
    }

    public void toast(String text) {
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}

