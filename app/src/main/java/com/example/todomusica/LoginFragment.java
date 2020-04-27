package com.example.todomusica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.Class.LoginRequest;
import com.example.todomusica.Class.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginFragment extends Fragment {

    EditText emailT, passwordT;
    Button btn;
    TextView btnRegister;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        final Crypto crypto = new Crypto();

        emailT = (EditText) view.findViewById(R.id.loginEmail);
        passwordT = (EditText) view.findViewById(R.id.loginPass);
        btn = (Button) view.findViewById(R.id.loginBtn);
        btnRegister = (TextView) view.findViewById(R.id.switchRegisterBtn);

        final SessionManager sessionManager = new SessionManager(getActivity().getApplicationContext());

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailT.getText().toString();
                final String pass = passwordT.getText().toString();

                Response.Listener<String> loginResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean ok = jsonObject.getBoolean("success");
                            if (ok){
                                String passw = jsonObject.getString("password");
                                String dpass = crypto.decrypt(passw);

                                if ( dpass.equals(pass) ){
                                    Integer id = jsonObject.getInt("id");
                                    String name = jsonObject.getString("name");
                                    String surname = jsonObject.getString("surname");
                                    String username = jsonObject.getString("username");

                                    sessionManager.setLogin(true);
                                    sessionManager.setData(id, name, surname, username);

                                    Navigation.findNavController(view).navigate(R.id.login2home);
                                }

                                else {
                                    AlertDialog.Builder passError = new AlertDialog.Builder(view.getContext());
                                    passError.setMessage("Error: La contraseña es incorrecta").setNegativeButton("Reintentar", null).create().show();
                                }

                            }
                            else {
                                AlertDialog.Builder loginError = new AlertDialog.Builder(view.getContext());
                                loginError.setMessage("Error: Fallo al iniciar sesión").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                };

                String cryptedPass = null;
                try {
                    cryptedPass = crypto.encrypt(pass);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                LoginRequest loginRequest = new LoginRequest(email, cryptedPass, loginResponse);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(loginRequest);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.login2register);
            }
        });

        return view;
    }
}
