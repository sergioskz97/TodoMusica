package com.example.todomusica;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;


public class LoginFragment extends Fragment {

    EditText emailT, passwordT;
    Button btn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        emailT = (EditText) view.findViewById(R.id.loginEmail);
        passwordT = (EditText) view.findViewById(R.id.loginPass);
        btn = (Button) view.findViewById(R.id.loginBtn);
        btn = (Button) view.findViewById(R.id.loginBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email = emailT.getText().toString();
                String pass = passwordT.getText().toString();

                Response.Listener<String> loginResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean ok = jsonObject.getBoolean("success");
                            if (ok){
                                String name = jsonObject.getString("name");
                                String surname = jsonObject.getString("surname");
                                String username = jsonObject.getString("username");

                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                fm.beginTransaction().replace(R.id.scenary, new HomeFragment()).commit();
                            }
                            else {
                                AlertDialog.Builder loginError = new AlertDialog.Builder(view.getContext());
                                loginError.setMessage("Error: Fallo al iniciar sesión").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                Crypto crypto = new Crypto();
                String cryptedPass = new String(crypto.encrypt(pass.getBytes()));

                LoginRequest loginRequest = new LoginRequest(email, cryptedPass, loginResponse);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(loginRequest);
            }
        });

        return view;
    }
}
