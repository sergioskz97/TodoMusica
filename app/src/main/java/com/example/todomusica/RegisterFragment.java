package com.example.todomusica;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.Class.RegisterRequest;
import com.example.todomusica.ui.home.HomeFragment;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class RegisterFragment extends Fragment {

    EditText nameT, surnameT, usernameT, emailT, passT, pass2T;
    Button btn;
    TextView btnLogin;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_register, container, false);

        nameT = (EditText) view.findViewById(R.id.registerName);
        surnameT = (EditText) view.findViewById(R.id.registerSurname);
        usernameT = (EditText) view.findViewById(R.id.registerUsername);
        emailT = (EditText) view.findViewById(R.id.registerEmail);
        passT = (EditText) view.findViewById(R.id.registerPass);
        pass2T = (EditText) view.findViewById(R.id.registerPass2);
        btn = (Button) view.findViewById(R.id.registerBtn);
        btnLogin = (TextView) view.findViewById(R.id.switchLoginBtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = nameT.getText().toString();
                String surname = surnameT.getText().toString();
                String username = usernameT.getText().toString();
                String email = emailT.getText().toString();
                final String pass = passT.getText().toString();
                final String pass2 = pass2T.getText().toString();

                Response.Listener<String> listenerResponse = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonResponse = new JSONObject(response);
                            boolean ok = jsonResponse.getBoolean("success");

                            if(ok){
                                FragmentManager fm = getActivity().getSupportFragmentManager();
                                FragmentTransaction ft = fm.beginTransaction();

                                LoginFragment loginFragment = new LoginFragment();
                                ft.replace(R.id.scenary, loginFragment);
                                ft.addToBackStack(null);
                                ft.commit();
                            }
                            else{
                                AlertDialog.Builder alert = new AlertDialog.Builder(view.getContext());
                                alert.setMessage("Fallo al registrar").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                String regex = "^[A-Za-z0-9+_.-]+@(.+)$";
                Pattern pattern = Pattern.compile(regex);

                Matcher matcher = pattern.matcher(email);

                Crypto crypto = new Crypto();

                if(matcher.matches()){
                    if(pass.equals(pass2)){
                        if(pass.length()>5){
                            String finalPass = null;
                            try {
                                finalPass = crypto.encrypt(pass);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            RegisterRequest r = new RegisterRequest(name, surname, username, email, finalPass, listenerResponse);
                            RequestQueue queue = Volley.newRequestQueue(view.getContext());
                            queue.add(r);
                        }
                        else{
                            AlertDialog.Builder passLength = new AlertDialog.Builder(view.getContext());
                            passLength.setMessage("Error: La contraseña es demasiado corta").setNegativeButton("Reintentar", null).create().show();
                        }
                    }
                    else{
                        AlertDialog.Builder passError = new AlertDialog.Builder(view.getContext());
                        passError.setMessage("Error: Las contraseñas no coinciden").setNegativeButton("Reintentar", null).create().show();
                    }
                }
                else{
                    AlertDialog.Builder emailError = new AlertDialog.Builder(view.getContext());
                    emailError.setMessage("Error: Email no valido").setNegativeButton("Reintentar", null).create().show();
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(v).navigate(R.id.register2login);
            }
        });

        return view;
    }
}
