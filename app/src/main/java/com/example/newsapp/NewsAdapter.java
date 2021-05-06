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
import java.util.Arrays;

import model.Articles;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {

    private Context context;
    private clickListeners listeners;
    private ArrayList<Articles> NewsList;

    public NewsAdapter(Context context, ArrayList<Articles> NewsList){
        this.context = context;
        this.NewsList = NewsList;
    }

    public void setListeners(clickListeners listeners) {
        this.listeners = listeners;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new NewsHolder(LayoutInflater.from(context).inflate(R.layout.cell_news, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
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

        holder.rc_favorites_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listeners.onFavoritesClicked(news);
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

    class NewsHolder extends RecyclerView.ViewHolder {

        LinearLayout rc_news_cell;
        TextView rc_news_headline;
        ImageView rc_news_image;
        TextView rc_news_published_at;
        TextView rc_news_desc;
        ImageView rc_favorites_icon;
        ImageView rc_share_icon;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            rc_news_cell = itemView.findViewById(R.id.ll_rc_news_cell);
            rc_news_headline = itemView.findViewById(R.id.rc_news_headline);
            rc_news_image = itemView.findViewById(R.id.rc_news_image);
            rc_news_published_at = itemView.findViewById(R.id.rc_news_published_at);
            rc_news_desc = itemView.findViewById(R.id.rc_news_desc);
            rc_share_icon = itemView.findViewById(R.id.rc_share_icon);
            rc_favorites_icon = itemView.findViewById(R.id.rc_favorite_icon);

        }
    }

    public interface clickListeners{
        void onCellClicked(Articles article);
        void onShareClicked(Articles article);
        void onFavoritesClicked(Articles article);
    }

}
