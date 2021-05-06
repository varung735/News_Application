package model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class News {

    public String status;
    public int totalResults;
    public ArrayList<Articles> articles;

    public static News parseNewsValue(String response){
        News news = new News();

        try {
            JSONObject jsonObject = new JSONObject(response);

            news.status = jsonObject.optString("status");
            news.totalResults = jsonObject.getInt("totalResults");

            news.articles = new ArrayList<>();

            JSONArray articleArray = jsonObject.optJSONArray("articles");
            if(articleArray.length() > 0){
                for(int i = 0; i < articleArray.length(); i++){
                    JSONObject articleObject = articleArray.optJSONObject(i);

                    Articles article = Articles.parseArticles(articleObject);
                    news.articles.add(article);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return news;
    }


}
