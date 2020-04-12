package com.example.todomusica.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomusica.R;
import com.example.todomusica.ui.home.HomeFragmentDirections;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistAdapter.ArtistViewHolder> implements Filterable {


    Context mContext;
    List<ArtistItem> mData ;
    List<ArtistItem> mDataFiltered ;
    Integer aId, aFollowers;
    String aName, aPicture, aGenre = "";
    boolean isDark = false;


    public ArtistAdapter(Context mContext, List<ArtistItem> mData, boolean isDark) {
        this.mContext = mContext;
        this.mData = mData;
        this.isDark = isDark;
        this.mDataFiltered = mData;
    }

    public ArtistAdapter(Context mContext, List<ArtistItem> mData) {
        this.mContext = mContext;
        this.mData = mData;
        this.mDataFiltered = mData;

    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_artist,viewGroup,false);
        return new ArtistViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder ArtistViewHolder, int position) {

        ArtistViewHolder.tv_title.setText(mDataFiltered.get(position).getName());
        ArtistViewHolder.tv_genre.setText(mDataFiltered.get(position).getGenre(0));
        Picasso.with(mContext).load(mDataFiltered.get(position).getPicture()).into(ArtistViewHolder.tv_picture);

    }

    @Override
    public int getItemCount() {
        return mDataFiltered.size();
    }

    @Override
    public Filter getFilter() {

        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {

                String Key = constraint.toString();
                if (Key.isEmpty()) {

                    mDataFiltered = mData ;

                }
                else {
                    List<ArtistItem> lstFiltered = new ArrayList<>();
                    for (ArtistItem row : mData) {

                        if (row.getName().toLowerCase().contains(Key.toLowerCase())){
                            lstFiltered.add(row);
                        }

                    }

                    mDataFiltered = lstFiltered;

                }

                FilterResults filterResults = new FilterResults();
                filterResults.values= mDataFiltered;
                return filterResults;

            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {

                mDataFiltered = (List<ArtistItem>) results.values;
                notifyDataSetChanged();

            }
        };
    }

    public class ArtistViewHolder extends RecyclerView.ViewHolder {

        TextView tv_title,tv_content,tv_date, tv_genre;
        ImageView tv_picture;
        RelativeLayout container;

        public ArtistViewHolder(@NonNull View itemView) {
            super(itemView);
            container = itemView.findViewById(R.id.cardContainer);
            tv_title = itemView.findViewById(R.id.cardArtistName);
            tv_picture = itemView.findViewById(R.id.cardArtistPic);
            tv_genre = itemView.findViewById(R.id.cardArtistGenre);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    aId = mDataFiltered.get(getLayoutPosition()).getId();
                    aName = mDataFiltered.get(getLayoutPosition()).getName();
                    aPicture  = mDataFiltered.get(getLayoutPosition()).getPicture();
                    aFollowers = mDataFiltered.get(getLayoutPosition()).getFollowers();
                    aGenre = mDataFiltered.get(getLayoutPosition()).getGenre(0);

                    HomeFragmentDirections.Home2artist action = HomeFragmentDirections.home2artist();
                    action.setId(aId);
                    action.setName(aName);
                    action.setPicture(aPicture);
                    action.setFollowers(aFollowers);
                    action.setGenre(aGenre);
                    Navigation.findNavController(v).navigate(action);
                }
            });

            if (isDark) {
                setDarkTheme();
            }
        }


        private void setDarkTheme() {

            //container.setBackgroundResource(R.drawable.card_bg_dark);

        }



    }
}
