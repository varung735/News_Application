package com.example.newsapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
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
import java.util.HashMap;

import model.Articles;
import model.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FragmentNewsDetails extends Fragment implements NewsAdapter.clickListeners{

    RecyclerView rv_frag_news_det;
    FavoritesDbHelper helper;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_news_details, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String category = "";
        String language = "";

        helper = new FavoritesDbHelper(getActivity());

        rv_frag_news_det = view.findViewById(R.id.frag_news_det_rv);
        rv_frag_news_det.setLayoutManager(new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false));

        Bundle data= getArguments();
        int screen_type = data.getInt("screen_type");
        if(data.getString("lang") != null){
             language = data.getString("lang");
        }

        switch(screen_type){
            case 0:
                category = "business";
                break;
            case 1:
                category = "entertainment";
                break;
            case 2:
                category = "general";
                break;
            case 3:
                category = "health";
                break;
            case 4:
                category = "science";
                break;
            case 5:
                category = "sports";
                break;
            case 6:
                category = "technology";
                break;
        }

        getHeadlines(category, language);
    }

    private void getHeadlines(String category, String language){

        apiInterface ApiInterface = apiClient.getClient().create(apiInterface.class);

        HashMap<String, Object> queries = new HashMap<>();
        queries.put("q", category);
        queries.put("apiKey", "7d0e2d7beee245949cbbe1d8ebd691eb");
        queries.put("language", language);

        Call<String> getEverything = ApiInterface.getNews(queries);

        getEverything.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                Log.i("API-RESPONSE", "Success");

                ArrayList<Articles> article = News.parseNewsValue(response.body()).articles;

                loadNewsData(article);
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {
                Log.i("API-RESPONSE", "Failure");
            }
        });
    }

    private void loadNewsData(ArrayList<Articles> articles){
        NewsAdapter adapter = new NewsAdapter(getActivity(), articles);
        adapter.setListeners(this);
        rv_frag_news_det.setAdapter(adapter);
    }

    @Override
    public void onCellClicked(Articles article) {
//       Bundle data = new Bundle();
//
//       data.putSerializable("article", article);
//
//       FragmentCompleteArticle completeArticle = new FragmentCompleteArticle();
//       completeArticle.setArguments(data);
//
//       getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.act_main_frame_layout, completeArticle).commit();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW);
        browserIntent.setData(Uri.parse(article.url));
        startActivity(browserIntent);
    }

    @Override
    public void onShareClicked(Articles article) {
       String Url = article.url;

        Intent mailIntent = new Intent(Intent.ACTION_SEND);
        mailIntent.putExtra(Intent.EXTRA_SUBJECT, "From News App");
        mailIntent.putExtra(Intent.EXTRA_TEXT, Url);
        mailIntent.setType("message/rfc822");
        startActivity(mailIntent);
    }

    @Override
    public void onFavoritesClicked(Articles article) {
        helper.insertArticles(helper.getWritableDatabase(), article);
        Toast.makeText(getActivity(), "Added to Favorites", Toast.LENGTH_SHORT).show();
    }
}
