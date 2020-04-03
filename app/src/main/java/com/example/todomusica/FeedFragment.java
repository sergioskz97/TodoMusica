package com.example.todomusica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomusica.Class.NewsAdapter;
import com.example.todomusica.Class.NewsItem;
import com.example.todomusica.Class.SessionManager;

import java.util.ArrayList;
import java.util.List;

public class FeedFragment extends Fragment {

    List<NewsItem> mData = new ArrayList<>();
    RecyclerView recyclerView;
    NewsAdapter newsAdapter;

    public FeedFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feed, container, false);
        final SessionManager session = new SessionManager(getActivity().getApplicationContext());

        if (session.isLogged()) {

        }

        else {

        }

        return view;
    }
}
