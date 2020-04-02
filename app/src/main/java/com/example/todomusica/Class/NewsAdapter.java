package com.example.todomusica.Class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todomusica.R;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder>{
    Context mContext;
    List<NewsItem> mData ;
    boolean isDark = false;

    public NewsAdapter(Context mContext, List<NewsItem> mData, boolean isDark){
        this.mContext = mContext;
        this.mData = mData;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View layout;
        layout = LayoutInflater.from(mContext).inflate(R.layout.item_news,viewGroup,false);
        return new NewsViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder newsViewHolder, int position) {
        newsViewHolder.rvTittle.setText(mData.get(position).getTittle());
        newsViewHolder.rvSnippet.setText(mData.get(position).getSnippet());
        newsViewHolder.rvDomain.setText(mData.get(position).getDomain());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        TextView rvTittle, rvSnippet, rvDomain;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);

            rvTittle = itemView.findViewById(R.id.rvNewsTittle);
            rvSnippet = itemView.findViewById(R.id.rvNewsSnippet);
            rvDomain = itemView.findViewById(R.id.rvNewsDomain);
        }
    }
}
