package com.example.martinb.newsapp.ui;

import com.example.martinb.newsapp.data.DataManager;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;

/**
 * Created by martinb on 2/28/2018.
 */

public class BasePresenter<T extends MvpView> implements Presenter<T> {

    private T mvpView;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private DataManager dataManager;

    @Inject
    public BasePresenter(DataManager dataManager){
        this.dataManager = dataManager;
    }

    public DataManager getDataManager(){
        return dataManager;
    }

    @Override
    public void attachView(T mvpView) {
        this.mvpView = mvpView;
    }

    public T getMvpView(){
        return this.mvpView;
    }

    @Override
    public void detachView() {
        this.mvpView = null;
        if (compositeDisposable != null && !compositeDisposable.isDisposed()) {
            compositeDisposable.clear();
        }
    }

    public CompositeDisposable getCompositeDisposable(){
        return this.compositeDisposable;
    }
}
