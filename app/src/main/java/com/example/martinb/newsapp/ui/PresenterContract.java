package com.example.martinb.newsapp.ui;

import com.example.martinb.newsapp.model.Article;
import com.example.martinb.newsapp.model.Headline;
import com.example.martinb.newsapp.model.Source;
import com.example.martinb.newsapp.model.Status;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by martinb on 2/28/2018.
 */

public interface PresenterContract {

    interface PresenterMain {
        String restoreSource();
        Single<Headline> getArticles(String newSource);
    }

    interface PresenterDialog{
        Single<Status> getSources();
        void saveSource(String idSource);
        }


    interface View extends MvpView {
        void updateArticles(List<Article> articles);
        void updateSources(List<Source> sources);
    }
}
