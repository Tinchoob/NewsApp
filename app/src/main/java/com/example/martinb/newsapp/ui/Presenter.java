package com.example.martinb.newsapp.ui;

/**
 * Created by martinb on 2/28/2018.
 */

public interface Presenter<V extends MvpView> {

    void attachView(V mvpView);
    void detachView();
}
