package com.example.martinb.newsapp.ui;

/**
 * Created by martinb on 2/28/2018.
 */
import android.support.annotation.StringRes;

/**
 * Base interface that any class that wants to act as a View in the MVP (Model View MainPresenter)
 * pattern must implement. Generally this interface will be extended by a more specific interface
 * that then usually will be implemented by an Activity or Fragment.
 */
public interface MvpView {

  //  void showLoading();

 //  void hideLoading();

    void openActivityOnTokenExpire();

    void onArticleError();

    void onSourceError();

  // void onError(@StringRes int resId);

    void onError(String message);

    void showMessage(String message);

  //  void showMessage(@StringRes int resId);

//    boolean isNetworkConnected();

 //   void hideKeyboard();

}
