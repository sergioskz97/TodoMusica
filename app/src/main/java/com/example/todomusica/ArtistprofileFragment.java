package com.example.todomusica;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.squareup.picasso.Picasso;

public class ArtistprofileFragment extends Fragment {
    TextView aName;
    ImageView aPicture;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_artistprofile, container, false);

        if (getArguments() != null){
            ArtistprofileFragmentArgs args = ArtistprofileFragmentArgs.fromBundle(getArguments());
            String name = args.getName();
            String picture = args.getPicture();

            aName = (TextView) view.findViewById(R.id.artistProfileName);
            aPicture = (ImageView) view.findViewById(R.id.artistProfilePicture);

            aName.setText(name);
            Picasso.with(getActivity()).load(picture).into(aPicture);
        }

        return view;
    }
}
