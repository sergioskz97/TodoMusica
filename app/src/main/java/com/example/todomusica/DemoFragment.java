package com.example.todomusica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.todomusica.Class.SessionManager;


public class DemoFragment extends Fragment {

    public static final String ARG_OBJECT = "object";
    TextView test;
    Button logoutBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_demo, container, false);

        test = view.findViewById(R.id.testView);
        logoutBtn = view.findViewById(R.id.logoutBtn);
        final SessionManager session = new SessionManager(getActivity().getApplicationContext());

        if (session.isLogged()) {
            test.setVisibility(View.VISIBLE);
            test.setText(session.getName());
        }

        else {
            test.setVisibility(View.INVISIBLE);
        }

        logoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                session.logout();
            }
        });

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Bundle args = getArguments();
        //((TextView) view.findViewById(android.R.id.text1)).setText(Integer.toString(args.getInt(ARG_OBJECT)));
    }
}
