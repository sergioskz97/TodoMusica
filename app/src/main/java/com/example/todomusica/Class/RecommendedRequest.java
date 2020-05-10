package com.example.todomusica.Class;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RecommendedRequest extends StringRequest {
    private static final String route = "https://todomusicatest.000webhostapp.com/Recommended.php";
    private Map<String, String> params;

    public RecommendedRequest(int mode, int userId , Response.Listener<String> listener) {
        super(Request.Method.POST, route, listener, null);

        params = new HashMap<>();
        params.put("mode", mode+"");
        params.put("userId", userId+"");
    }

    @Override
    protected Map<String, String> getParams(){
        return params;
    }
}
