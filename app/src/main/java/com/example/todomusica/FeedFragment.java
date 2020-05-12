package com.example.todomusica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.Class.FeedRequest;
import com.example.todomusica.Class.NewsAdapter;
import com.example.todomusica.Class.NewsItem;
import com.example.todomusica.Class.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    List<NewsItem> mData = new ArrayList<>();
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;
    String aux = "ASD";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_feed, container, false);
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

                    if (ok){
                        JSONArray jsonArrayTittle = new JSONArray(jsonObject.getString("tittle"));
                        JSONArray jsonArraySnippet = new JSONArray(jsonObject.getString("snippet"));
                        JSONArray jsonArrayDate = new JSONArray(jsonObject.getString("date"));
                        JSONArray jsonArrayLink = new JSONArray(jsonObject.getString("link"));
                        JSONArray jsonArrayThumbnail = new JSONArray(jsonObject.getString("thumbnail"));
                        JSONArray jsonArrayDomain = new JSONArray(jsonObject.getString("domain"));

                        for (int i = 0 ; i < jsonArrayTittle.length(); i++) {
                            mData.add(new NewsItem(aux , jsonArrayTittle.getString(i), jsonArrayLink.getString(i), jsonArrayDate.getString(i), jsonArraySnippet.getString(i), jsonArrayThumbnail.getString(i), jsonArrayDomain.getString(i)));
                        }

                        recyclerView = view.findViewById(R.id.rvFeedNews);
                        newsAdapter = new NewsAdapter(getActivity(), mData, false);
                        recyclerView.setAdapter(newsAdapter);
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

        FeedRequest feedRequest = new FeedRequest(mode, userId, listener);
        RequestQueue queueNews = Volley.newRequestQueue(getActivity());
        queueNews.add(feedRequest);

        return view;
    }
}
