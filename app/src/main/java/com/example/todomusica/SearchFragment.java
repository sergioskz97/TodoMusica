package com.example.todomusica;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.Class.ArtistAdapter;
import com.example.todomusica.Class.ArtistItem;
import com.example.todomusica.Class.SearchRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {

    EditText search;
    List<ArtistItem> mData = new ArrayList<>();
    ArtistAdapter artistAdapter;
    RecyclerView recyclerView;
    CharSequence searchOutput;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_search, container, false);

        search = (EditText) view.findViewById(R.id.searchBar);
        recyclerView = view.findViewById(R.id.rvList);

        artistAdapter = new ArtistAdapter(getActivity(), mData, false);
        recyclerView.setAdapter(artistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                bindList(s.toString());
                artistAdapter.getFilter().filter(s);
                searchOutput = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }

    public void bindList(String s){
        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean ok = jsonObject.getBoolean("success");

                    if (ok){
                        mData.clear();
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("name"));
                        JSONArray jsonArray1 = new JSONArray(jsonObject.getString("picture"));

                       for (int i = 0; i<jsonArray.length(); i++ ){
                            mData.add(new ArtistItem(1, jsonArray.getString(i), jsonArray1.getString(i)));
                        }
                    }

                    else{
                        AlertDialog.Builder searchError = new AlertDialog.Builder(getActivity());
                        searchError.setMessage("Error: Fallo al conectar con la base de datos").setNegativeButton("Reintentar", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        SearchRequest searchRequest = new SearchRequest(s, listener);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(searchRequest);

    }
}
