package com.example.todomusica.Class;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class FeedRequest extends StringRequest {
    private static  final String route = "https://todomusicatest.000webhostapp.com/Feed.php";
    private Map<String, String> params;

    public FeedRequest(int mode, int userId, Response.Listener<String> listener) {
        super(Request.Method.POST, route, listener, null);

        params = new HashMap<>();
    }

    @Override
    public Map<String, String> getParams() { return params; }
}
