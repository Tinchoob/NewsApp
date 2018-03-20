package com.example.martinb.newsapp.data.remote;

/**
 * Created by martinb on 2/20/2018.
 */

import com.example.martinb.newsapp.model.Article;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

public class ArticleDes implements JsonDeserializer<Article> {

    @Override
    public Article deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Article article = new Article();
        JsonObject repoJsonObject =  json.getAsJsonObject();
        article.setTitle(repoJsonObject.get("title").getAsString());
        article.setDescription(repoJsonObject.get("description").getAsString());
        article.setUrlToImage(repoJsonObject.get("urlToImage").getAsString());
        article.setUrl(repoJsonObject.get("url").getAsString());
        return article;
    }
}