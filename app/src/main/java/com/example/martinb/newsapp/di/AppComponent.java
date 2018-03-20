package com.example.martinb.newsapp.di;

import android.app.Application;

import com.example.martinb.newsapp.ThisApp;
import com.example.martinb.newsapp.di.modules.AppModule;
import com.example.martinb.newsapp.di.modules.MainActivityModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;

/**
 * Created by martinb on 3/2/2018.
 */


    @Singleton
    @Component(modules = {
            AndroidInjectionModule.class,
            AppModule.class,
            ActivityBuilder.class,})

    public interface AppComponent {

        @Component.Builder
        interface Builder {
            @BindsInstance
            Builder application(Application application);
            AppComponent build();
        }

        void inject(ThisApp app);
    }

