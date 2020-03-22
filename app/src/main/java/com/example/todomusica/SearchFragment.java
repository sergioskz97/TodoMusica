package com.example.todomusica;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.todomusica.Class.ArtistAdapter;
import com.example.todomusica.Class.ArtistItem;

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

        mData.add(new ArtistItem(1, "armando", "aaa"));
        mData.add(new ArtistItem(2, "anastasia", "bbb"));
        mData.add(new ArtistItem(3, "carlos", "ccc"));

        artistAdapter = new ArtistAdapter(getActivity(), mData, false);
        recyclerView.setAdapter(artistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                artistAdapter.getFilter().filter(s);
                searchOutput = s;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        return view;
    }
}
