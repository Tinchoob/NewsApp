package com.example.martinb.newsapp.data.remote;

import com.example.martinb.newsapp.BuildConfig;
import com.example.martinb.newsapp.model.Article;
import com.example.martinb.newsapp.model.Headline;
import com.example.martinb.newsapp.model.Source;
import com.example.martinb.newsapp.model.Status;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.concurrent.TimeUnit;

import io.reactivex.Single;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Query;

/**
 * Created by martinb on 2/26/2018.
 */

public class NewsApiClient {

    private NewsAPI newsAPI;

    private static OkHttpClient.Builder createOkHttpClientBuilder() {

        OkHttpClient.Builder builder = new OkHttpClient.Builder();

        if (BuildConfig.DEBUG) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS);
    }



    public NewsApiClient(){
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .registerTypeAdapter(Article.class, new ArticleDes())
                .registerTypeAdapter(Source.class, new SourceDes())
                .create();

        OkHttpClient okHttpClient = createOkHttpClientBuilder()
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(NewsAPI.url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
       newsAPI = retrofit.create(NewsAPI.class);
    }

    public Single<Status> getSources(String category,String language,String key){
        return newsAPI.getSources(category, language, key);
    }
    public  Single<Headline> getArticles(String source,String key){
        return newsAPI.getArticles(source, key);
    }
}
