package com.example.martinb.newsapp.di.modules;

import android.app.Application;
import android.content.Context;
import android.preference.PreferenceManager;

import com.example.martinb.newsapp.data.DataManager;
import com.example.martinb.newsapp.data.local.MyPreferenceHelper;
import com.example.martinb.newsapp.data.remote.NewsApiClient;

import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import khangtran.preferenceshelper.PreferencesHelper;

/**
 * Created by leandrom on 8/4/2017.
 */
@Module
public class AppModule {

    @Provides
    @Singleton
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    DataManager providesDataManager(NewsApiClient newsApiClient, MyPreferenceHelper preferencesHelper) {
        return new DataManager(newsApiClient,preferencesHelper);
    }

    @Provides
    @Singleton
    NewsApiClient providesNewsApiClient(){
        return new NewsApiClient();
    }

    @Provides
    @Singleton
    MyPreferenceHelper providesPreferenceHelper(Context context){
        return new MyPreferenceHelper(context);
    }

}
