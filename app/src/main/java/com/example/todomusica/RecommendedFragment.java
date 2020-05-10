package com.example.todomusica;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.todomusica.Class.ArtistAdapter;
import com.example.todomusica.Class.ArtistItem;
import com.example.todomusica.Class.SessionManager;

import java.util.ArrayList;
import java.util.List;


public class RecommendedFragment extends Fragment {

    List<ArtistItem> mData = new ArrayList<>();
    ArtistAdapter artistAdapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recommended, container, false);

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

        recyclerView = view.findViewById(R.id.rvListRecommended);

        artistAdapter = new ArtistAdapter(getActivity(), mData, false);
        recyclerView.setAdapter(artistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        return view;
    }
}
