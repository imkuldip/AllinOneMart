package com.example.kuldip.allinonemart.DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeImageList {
    @SerializedName("homeimages")
    private ArrayList<HomeImageUrl> homeimages;

    public HomeImageList(){

    }

    public ArrayList<HomeImageUrl> getHomeImageList(){
        return homeimages;
    }
}
