package com.example.martinb.newsapp.data.local;

import android.content.Context;

import javax.inject.Inject;

import khangtran.preferenceshelper.PreferencesHelper;

/**
 * Created by martinb on 2/27/2018.
 */

public class MyPreferenceHelper {

    @Inject
    public MyPreferenceHelper(Context context){
        PreferencesHelper.initHelper(context);
    }

    public String restoreSource(){
        return PreferencesHelper.getInstance().getStringValue("source",null);
    }

    public void saveSource(String source){
        PreferencesHelper.getInstance().setValue("source",source);
    }
}
