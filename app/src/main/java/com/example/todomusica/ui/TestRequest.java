package com.example.todomusica.ui;

import androidx.annotation.Nullable;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

public class TestRequest extends StringRequest {

    private static final String route = "https://todomusicatest.000webhostapp.com/Update.php";

    public TestRequest(Response.Listener<String> listener){
        super(Request.Method.POST, route, listener, null);
    }

}
