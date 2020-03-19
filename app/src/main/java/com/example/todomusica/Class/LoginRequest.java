package com.example.todomusica.Class;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest {
    private static final String route = "https://todomusicatest.000webhostapp.com/Login.php";
    private Map<String, String> params;

    public LoginRequest(String email, String password, Response.Listener<String> listener) {
        super(Request.Method.POST, route, listener, null);

        params = new HashMap<>();
        params.put("email", email+"");
        params.put("password", password+"");
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
