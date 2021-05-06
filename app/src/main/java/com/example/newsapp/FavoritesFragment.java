package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import model.Articles;

public class FavoritesFragment extends Fragment implements FavoritesAdapter.clickListeners{

    RecyclerView frag_fav_rv;
    FavoritesDbHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_favorites, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        frag_fav_rv = view.findViewById(R.id.frag_fav_rv);
        frag_fav_rv.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        helper = new FavoritesDbHelper(getActivity());

        loadFavoriteArticles();
    }

    public void loadFavoriteArticles(){

        ArrayList<Articles> favoriteArticles = helper.getArticles(helper.getReadableDatabase());
        FavoritesAdapter adapter = new FavoritesAdapter(getActivity(), favoriteArticles);
        adapter.setListeners(this);
        frag_fav_rv.setAdapter(adapter);
    }

    @Override
    public void onCellClicked(Articles articles) {
        Bundle data = new Bundle();

        data.putSerializable("article", articles);

        FragmentCompleteArticle completeArticle = new FragmentCompleteArticle();
        completeArticle.setArguments(data);

        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.act_main_frame_layout, completeArticle).commit();
    }

    @Override
    public void onShareClicked(Articles articles) {
        String Url = articles.url;

        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "From News App");
        mailIntent.putExtra(Intent.EXTRA_TEXT, Url);
        mailIntent.setType("message/rfc822");
        startActivity(mailIntent);
    }

    @Override
    public void onDeleteClicked(Articles articles) {
        helper.deleteArticle(helper.getWritableDatabase(), articles);
        loadFavoriteArticles();
    }
}
