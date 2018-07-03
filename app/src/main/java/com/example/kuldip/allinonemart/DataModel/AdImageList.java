package com.example.kuldip.allinonemart.DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AdImageList {
    @SerializedName("adimages")
    private ArrayList<AdImageUrl> adimages;

    public AdImageList(){

    }

    public ArrayList<AdImageUrl> getAdImageList(){
        return adimages;
    }
}
