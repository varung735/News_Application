package com.example.newsapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

import model.Articles;

public class FavoritesDbHelper extends SQLiteOpenHelper {

    String TABLE_NAME = "favorite_articles";
    String COL_ID = "id";
    String COL_AUTHOR = "author";
    String COL_TITLE = "title";
    String COL_DES = "description";
    String COL_URL = "url";
    String COL_URL_IMG = "url_to_img";
    String COL_PUB_AT = "published_at";
    String COL_CONTENT = "content";

    String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + COL_AUTHOR + " TEXT,"
            + COL_TITLE + " TEXT," + COL_DES + " TEXT," + COL_URL + " TEXT," + COL_URL_IMG + " TEXT," + COL_PUB_AT + " TEXT,"
            + COL_CONTENT + " TEXT)";

    public FavoritesDbHelper(@Nullable Context context) { super(context, "favorites.db", null, 1); }

    @Override
    public void onCreate(SQLiteDatabase db) { db.execSQL(CREATE_TABLE); }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertArticles(SQLiteDatabase database, Articles article){
        ContentValues cv = new ContentValues();
        cv.put(COL_AUTHOR, article.author);
        cv.put(COL_TITLE, article.title);
        cv.put(COL_DES, article.description);
        cv.put(COL_URL, article.url);
        cv.put(COL_URL_IMG, article.urlToImg);
        cv.put(COL_PUB_AT, article.publishedAt);
        cv.put(COL_CONTENT, article.content);
        database.insert(TABLE_NAME, null, cv);
    }

    public ArrayList<Articles> getArticles(SQLiteDatabase database){
        ArrayList<Articles> articles = new ArrayList<>();
        Cursor cursor = database.rawQuery("SELECT * FROM " + TABLE_NAME, null);

        if(cursor.moveToFirst()){
            do{
                Articles articles1 = new Articles();
                articles1.id = cursor.getInt(cursor.getColumnIndex(COL_ID));
                articles1.author = cursor.getString(cursor.getColumnIndex(COL_AUTHOR));
                articles1.title = cursor.getString(cursor.getColumnIndex(COL_TITLE));
                articles1.description = cursor.getString(cursor.getColumnIndex(COL_DES));
                articles1.url = cursor.getString(cursor.getColumnIndex(COL_URL));
                articles1.urlToImg = cursor.getString(cursor.getColumnIndex(COL_URL_IMG));
                articles1.publishedAt = cursor.getString(cursor.getColumnIndex(COL_PUB_AT));
                articles1.content = cursor.getString(cursor.getColumnIndex(COL_CONTENT));

                articles.add(articles1);
            }while(cursor.moveToNext());
        }
        cursor.close();
        return articles;
    }

    public void deleteArticle(SQLiteDatabase database, Articles article){
        database.delete(TABLE_NAME, COL_ID + " = " + article.id, null);
    }
}
