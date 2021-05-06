package com.example.newsapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import model.Articles;

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.FavoritesHolder> {

    private Context context;
    private ArrayList<Articles> NewsList;
    private clickListeners listeners;

    public void setListeners(clickListeners listeners){
        this.listeners = listeners;
    }

    public FavoritesAdapter(Context context, ArrayList<Articles> NewsList){
        this.context = context;
        this.NewsList = NewsList;
    }

    @NonNull
    @Override
    public FavoritesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavoritesHolder(LayoutInflater.from(context).inflate(R.layout.cell_favorites, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavoritesHolder holder, int position) {
        Articles news = NewsList.get(position);

        holder.rc_news_headline.setText(news.title);
        holder.rc_news_published_at.setText(news.publishedAt);
        holder.rc_news_desc.setText(news.description);

        Glide.with(context).load(news.urlToImg).into(holder.rc_news_image);

        holder.rc_share_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onShareClicked(news);
            }
        });

        holder.rc_delete_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onDeleteClicked(news);
            }
        });

        holder.rc_news_cell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onCellClicked(news);
            }
        });
    }

    @Override
    public int getItemCount() {
        return NewsList.size();
    }

    public class FavoritesHolder extends RecyclerView.ViewHolder {

        LinearLayout rc_news_cell;
        TextView rc_news_headline;
        ImageView rc_news_image;
        TextView rc_news_published_at;
        TextView rc_news_desc;
        ImageView rc_delete_icon;
        ImageView rc_share_icon;

        public FavoritesHolder(@NonNull View itemView) {
              super(itemView);

            rc_news_cell = itemView.findViewById(R.id.ll_rc_news_cell);
            rc_news_headline = itemView.findViewById(R.id.rc_news_headline);
            rc_news_image = itemView.findViewById(R.id.rc_news_image);
            rc_news_published_at = itemView.findViewById(R.id.rc_news_published_at);
            rc_news_desc = itemView.findViewById(R.id.rc_news_desc);
            rc_share_icon = itemView.findViewById(R.id.rc_share_icon);
            rc_delete_icon = itemView.findViewById(R.id.rc_delete_icon);
        }
    }

    public interface clickListeners{
        void onCellClicked(Articles articles);
        void onShareClicked(Articles articles);
        void onDeleteClicked(Articles articles);
    }

}
