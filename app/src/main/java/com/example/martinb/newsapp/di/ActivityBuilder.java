package com.example.martinb.newsapp.di;

import com.example.martinb.newsapp.di.modules.DialogFragmentModule;
import com.example.martinb.newsapp.di.modules.MainActivityModule;
import com.example.martinb.newsapp.ui.DialogFragment;
import com.example.martinb.newsapp.ui.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by leandrom on 8/4/2017.
 */

@Module
public abstract class ActivityBuilder {

    @ContributesAndroidInjector(modules = MainActivityModule.class)
    abstract MainActivity bindLoginActivity();

    @ContributesAndroidInjector(modules = DialogFragmentModule.class)
    abstract DialogFragment bindSourceActivity();

}
