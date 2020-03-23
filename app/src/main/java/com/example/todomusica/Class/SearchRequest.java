package com.example.todomusica.Class;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class SearchRequest extends StringRequest {
    private static final String route = "https://todomusicatest.000webhostapp.com/Search.php";
    private Map<String, String> params;

    public SearchRequest(String search, Response.Listener<String> listener) {
        super(Request.Method.POST, route, listener, null);

        params = new HashMap<>();
        params.put("search", search+"");
    }

    @Override
    public Map<String, String> getParams() { return params; }
}
