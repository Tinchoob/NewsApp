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
 * Created by martinb on 3/2/2018.
 */

public class DialogPresenter extends BasePresenter<PresenterContract.View> implements PresenterContract.PresenterDialog {

    @Inject
    public DialogPresenter(DataManager dataManager) {
        super(dataManager);
    }


    @Override
    public void saveSource(String idSource) {
        getDataManager().saveSources(idSource);
    }


    @Override
    public Single<Status> getSources() {
        return getDataManager().getSources();
    }

    public void updateSources() {
        getCompositeDisposable().add(this.getSources()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(getSourcesObserver()));
    }

    private DisposableSingleObserver<Status> getSourcesObserver() {
        return new DisposableSingleObserver<Status>() {
            @Override
            public void onSuccess(Status value) {
                getMvpView().updateSources(value.getSources());
            }
            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                getMvpView().onSourceError();
            }
        };
    }
}
