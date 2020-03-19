package com.example.todomusica.Class;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String route = "https://todomusicatest.000webhostapp.com/Register.php";
    private Map<String, String> params;

    public RegisterRequest(String name, String surname, String username, String email, String password, Response.Listener<String> listener){
        super(Request.Method.POST, route, listener, null);

        params = new HashMap<>();
        params.put("name", name+"");
        params.put("surname", surname+"");
        params.put("username", username+"");
        params.put("email", email+"");
        params.put("password", password+"");
    }

    @Override
    protected Map<String, String> getParams(){
        return params;
    }
}
