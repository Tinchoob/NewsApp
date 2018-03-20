package com.example.martinb.newsapp.di.scope;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by martinb on 3/2/2018.
 */

    @Scope
    @Retention(RetentionPolicy.RUNTIME)
    public @interface PerActivity {
    }
