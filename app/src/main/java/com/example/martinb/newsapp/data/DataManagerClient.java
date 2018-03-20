package com.example.martinb.newsapp.data;

import android.content.Context;

import com.example.martinb.newsapp.model.Headline;
import com.example.martinb.newsapp.model.Status;

import io.reactivex.Single;

/**
 * Created by martinb on 2/26/2018.
 */

public interface DataManagerClient {


    String restoreSource();

    void saveSources(String source);

    Single<Headline> getArticles(String source);

    Single<Status> getSources();



}
