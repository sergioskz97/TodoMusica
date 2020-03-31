package com.example.todomusica.Class;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NewsRequest extends StringRequest {
    private static final String route = "https://todomusicatest.000webhostapp.com/News.php";
    private Map<String, String> params;

    public NewsRequest(Integer artistId, String artistName, Response.Listener<String> listener) {
        super(Method.POST, route, listener, null);

        params = new HashMap<>();
        params.put("artistId", artistId+"");
        params.put("artistName", artistName+"");

    }

    @Override
    public Map<String, String> getParams() { return params; }
}
