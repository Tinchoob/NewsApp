package com.example.martinb.newsapp.data.remote;

import com.example.martinb.newsapp.model.Headline;
import com.example.martinb.newsapp.model.Status;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by martinb on 2/20/2018.
 */

public interface NewsAPI {


    String url= "https://newsapi.org/v2/";


    @GET ("top-headlines")
    Single<Headline> getArticles(@Query("sources") String source, @Query("apiKey") String key);


    @GET("sources?")
    Single<Status>  getSources(@Query("category") String category, @Query("language")String language, @Query("apiKey") String key);

}
