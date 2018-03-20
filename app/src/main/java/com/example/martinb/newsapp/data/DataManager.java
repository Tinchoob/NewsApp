package com.example.martinb.newsapp.data;

import com.example.martinb.newsapp.data.local.MyPreferenceHelper;
import com.example.martinb.newsapp.data.remote.NewsApiClient;
import com.example.martinb.newsapp.model.Headline;
import com.example.martinb.newsapp.model.Status;

import javax.inject.Inject;

import io.reactivex.Single;


/**
 * Created by martinb on 2/26/2018.
 */

public class DataManager implements DataManagerClient{

    private final NewsApiClient newsApiClient;
    private final MyPreferenceHelper preferencesHelper;

    @Inject
    public DataManager (NewsApiClient newsApiClient, MyPreferenceHelper preferencesHelper){
        this.newsApiClient = newsApiClient;
        this.preferencesHelper = preferencesHelper;
    }

    @Override
    public String restoreSource() {
        return preferencesHelper.restoreSource();
    }

    @Override
    public void saveSources(String idSource) {
        preferencesHelper.saveSource(idSource);
    }

    @Override
    public Single<Status> getSources() {
        return newsApiClient.getSources("sports", "en", "d2f9d1ebf1494d8fa7f027680791ade8");
    }

    @Override
    public Single<Headline> getArticles(String newSource) {
        return newsApiClient.getArticles(newSource, "d2f9d1ebf1494d8fa7f027680791ade8");
    }


}
