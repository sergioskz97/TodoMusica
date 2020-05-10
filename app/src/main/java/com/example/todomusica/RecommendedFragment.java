package com.example.todomusica;

import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.Class.ArtistAdapter;
import com.example.todomusica.Class.ArtistItem;
import com.example.todomusica.Class.FeedRequest;
import com.example.todomusica.Class.RecommendedRequest;
import com.example.todomusica.Class.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class RecommendedFragment extends Fragment {

    List<ArtistItem> mData = new ArrayList<>();
    ArtistAdapter artistAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_recommended, container, false);

        final SessionManager session = new SessionManager(getActivity().getApplicationContext());
        int mode, userId = 0;

        if (session.isLogged()) {
            mode = 1;
            userId = session.getId();
        }

        else {
            mode = 0;
            userId = 0;
        }

        Response.Listener<String> listener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    boolean ok = jsonObject.getBoolean("success");

                    if (ok) {
                        mData.clear();
                        JSONArray jsonArray = new JSONArray(jsonObject.getString("name"));
                        JSONArray jsonArray1 = new JSONArray(jsonObject.getString("picture"));
                        JSONArray jsonArray2 = new JSONArray(jsonObject.getString("id"));
                        JSONArray jsonArray3 = new JSONArray(jsonObject.getString("followers"));
                        JSONArray jsonArray4 = new JSONArray(jsonObject.getString("genre"));

                        for (int i = 0; i<jsonArray.length(); i++ ){
                            mData.add(new ArtistItem(jsonArray2.getInt(i), jsonArray.getString(i), jsonArray1.getString(i), jsonArray3.getInt(i), jsonArray4.getString(i)));
                        }

                        recyclerView = view.findViewById(R.id.rvListRecommended);

                        artistAdapter = new ArtistAdapter(getActivity(), mData, false);
                        recyclerView.setAdapter(artistAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    }

                    else {
                        AlertDialog.Builder followError = new AlertDialog.Builder(view.getContext());
                        followError.setMessage("ERROR: ").setNegativeButton("Reintentar", null).create().show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        RecommendedRequest recommendedRequest = new RecommendedRequest(mode, userId, listener);
        RequestQueue queueNews = Volley.newRequestQueue(getActivity());
        queueNews.add(recommendedRequest);

        return view;
    }
}
