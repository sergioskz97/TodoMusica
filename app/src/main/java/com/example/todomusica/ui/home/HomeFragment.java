package com.example.todomusica.ui.home;

import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.R;
import com.example.todomusica.ui.TestRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;

public class HomeFragment extends Fragment {

    TextView response2;
    Button testBtn;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_home, container, false);

        response2 = (TextView) view.findViewById(R.id.apiResponse);
        testBtn = (Button) view.findViewById(R.id.testBtn);
        testBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Response.Listener<String> listener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            boolean ok = jsonObject.getBoolean("success");

                            if (ok){
                                AlertDialog.Builder loginError = new AlertDialog.Builder(view.getContext());
                                loginError.setMessage("Ok").setNegativeButton("Reintentar", null).create().show();
                            }
                            else{
                                AlertDialog.Builder loginError = new AlertDialog.Builder(view.getContext());
                                loginError.setMessage("ERROR").setNegativeButton("Reintentar", null).create().show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };

                TestRequest testRequest = new TestRequest(listener);
                RequestQueue queue = Volley.newRequestQueue(view.getContext());
                queue.add(testRequest);
            }
        });


        return view;
    }

    public void getData(){
        String apiUrl = "https://api.deezer.com/artist/";

        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        URL url = null;
        HttpURLConnection conn;
        try {
            String message = "";

            for (int i = 500; i <= 510 ; i++){
                String auxUrl = apiUrl+i;
                url = new URL(auxUrl);
                conn = (HttpURLConnection) url.openConnection();

                conn.setRequestMethod("GET");
                conn.connect();
                BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                String inputLine;
                StringBuffer response = new StringBuffer();
                String json;

                while ((inputLine = in.readLine()) != null){
                    response.append(inputLine);
                }

                json = response.toString();
                JSONObject jsonObject = new JSONObject(json);

                if(jsonObject.has("name")){
                    message += jsonObject.getString("name") + "\n";
                }
            }

            response2.setText(message);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
