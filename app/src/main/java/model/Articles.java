package model;

import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

public class Articles implements Serializable {

    public int id;
    public String author;
    public String title;
    public String description;
    public String url;
    public String urlToImg;
    public String publishedAt;
    public String content;
    public Source source;

    public static Articles parseArticles(JSONObject jsonObject){
        Articles articles = new Articles();

        articles.author = jsonObject.optString("author");
        articles.title = jsonObject.optString("title");
        articles.description = jsonObject.optString("description");
        articles.url = jsonObject.optString("url");
        articles.urlToImg = jsonObject.optString("urlToImage");
        articles.publishedAt = jsonObject.optString("publishedAt");
        articles.content = jsonObject.optString("content");

        articles.source = Source.parseSource(jsonObject.optJSONObject("source"));

        return articles;
    }

}
