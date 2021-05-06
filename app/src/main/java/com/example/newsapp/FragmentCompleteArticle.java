package com.example.newsapp;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;

import model.Articles;

public class FragmentCompleteArticle extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_complete_article, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView Title = view.findViewById(R.id.frag_comp_news_title);
        ImageView image = view.findViewById(R.id.frag_com_news_image);
        TextView author = view.findViewById(R.id.frag_com_news_author_name);
        TextView content = view.findViewById(R.id.frag_com_news_content);

        Bundle data = getArguments();

        if(data != null){
            Articles article = (Articles) data.getSerializable("article");

            Title.setText(article.title);
            Glide.with(getActivity()).load(article.urlToImg).into(image);
            author.setText(article.author);
            content.setText(article.content);
        }
    }
}
