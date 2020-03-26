package com.example.todomusica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.Volley;
import com.example.todomusica.Class.CheckFollowRequest;
import com.example.todomusica.Class.FollowRequest;
import com.example.todomusica.Class.SessionManager;
import com.example.todomusica.Class.UnfollowRequest;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ArtistprofileFragment extends Fragment {
    TextView aName;
    ImageView aPicture;
    Button followBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_artistprofile, container, false);
        final SessionManager session = new SessionManager(getActivity().getApplicationContext());
        final ArtistprofileFragmentArgs args = ArtistprofileFragmentArgs.fromBundle(getArguments());

        followBtn = (Button) view.findViewById(R.id.followBtn);

        Response.Listener<String> listenerA = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject jsonObjectA = new JSONObject(response);
                    boolean ok = jsonObjectA.getBoolean("success");

                    if (ok) {
                        followBtn.setText("Dejar de seguir");
                    }
                    else {
                        followBtn.setText("Seguir");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        };

        CheckFollowRequest check = new CheckFollowRequest(session.getId(), args.getId(), listenerA);
        RequestQueue queue = Volley.newRequestQueue(getActivity());
        queue.add(check);

        if (getArguments() != null){
            String name = args.getName();
            String picture = args.getPicture();

            aName = (TextView) view.findViewById(R.id.artistProfileName);
            aPicture = (ImageView) view.findViewById(R.id.artistProfilePicture);

            aName.setText(name);
            Picasso.with(getActivity()).load(picture).into(aPicture);

            followBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (session.isLogged()){
                        if (followBtn.getText().toString() == "Seguir"){
                            Response.Listener<String> listenerB = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObjectB = new JSONObject(response);
                                        boolean ok = jsonObjectB.getBoolean("success");

                                        if (!ok){
                                            AlertDialog.Builder followError = new AlertDialog.Builder(view.getContext());
                                            followError.setMessage("Error: No se ha podido seguir al usuario").setNegativeButton("Reintentar", null).create().show();
                                        }

                                        else {
                                            Navigation.findNavController(view).navigate(R.id.artist2home);
                                        }

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            FollowRequest followRequest = new FollowRequest(session.getId(), args.getId(), listenerB);
                            RequestQueue queueb = Volley.newRequestQueue(getActivity());
                            queueb.add(followRequest);
                        }
                        else {
                            Response.Listener<String> listenerC = new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    try {
                                        JSONObject jsonObjectC = new JSONObject(response);
                                        boolean ok = jsonObjectC.getBoolean("success");

                                        if (!ok) {
                                            AlertDialog.Builder unFollowError = new AlertDialog.Builder(view.getContext());
                                            unFollowError.setMessage("Error: No se ha podido dejar de seguir al usuario").setNegativeButton("Reintentar", null).create().show();
                                        }

                                        else {
                                            Navigation.findNavController(view).navigate(R.id.artist2home);
                                        }
                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };

                            UnfollowRequest unfollowRequest = new UnfollowRequest(session.getId(), args.getId(), listenerC);
                            RequestQueue queuec = Volley.newRequestQueue(getActivity());
                            queuec.add(unfollowRequest);
                        }
                    }
                    else {
                        AlertDialog.Builder loginError = new AlertDialog.Builder(view.getContext());
                        loginError.setMessage("Error: Debe iniciar sesión para seguir a alguien").setNegativeButton("Reintentar", null).create().show();
                    }
                }
            });
        }

        return view;
    }
}
