package com.example.martinb.newsapp.ui;

import com.example.martinb.newsapp.data.DataManager;
import com.example.martinb.newsapp.model.Headline;
import com.example.martinb.newsapp.model.Status;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by martinb on 2/28/2018.
 */

public class MainPresenter extends BasePresenter<PresenterContract.View> implements PresenterContract.PresenterMain {

    @Inject
    public MainPresenter(DataManager dataManager) {
        super(dataManager);
    }

    @Override
    public String restoreSource() {
        return getDataManager().restoreSource();
    }


    @Override
    public Single<Headline> getArticles(String newSource) {
        return getDataManager().getArticles(newSource);
    }


    public void updateArticles(String source){
        getCompositeDisposable().add(this.getArticles(source)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getArticlesObserver()));
    }

    private DisposableSingleObserver<Headline> getArticlesObserver() {
        return new DisposableSingleObserver<Headline>() {
            @Override
            public void onSuccess(Headline value) {
               getMvpView().updateArticles(value.getArticles());
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().onArticleError();
            }
        };
    }


}
