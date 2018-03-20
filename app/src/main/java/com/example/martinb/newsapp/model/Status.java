package com.example.martinb.newsapp.model;

import com.example.martinb.newsapp.model.Source;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by martinb on 2/21/2018.
 */

public class Status {


    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("sources")
    @Expose
    private ArrayList<Source> sources = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ArrayList<Source> getSources() {
        return sources;
    }

    public void setSources(ArrayList<Source> sources) {
        this.sources = sources;
    }

}
