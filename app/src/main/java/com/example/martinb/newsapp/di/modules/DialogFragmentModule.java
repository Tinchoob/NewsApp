package com.example.martinb.newsapp.di.modules;

import com.example.martinb.newsapp.data.DataManager;
import com.example.martinb.newsapp.ui.DialogPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by martinb on 3/2/2018.
 */

@Module
public class DialogFragmentModule {

    @Provides
    DialogPresenter providedDialogPresenter(DataManager dataManager){
        return new DialogPresenter(dataManager);
    }
}
