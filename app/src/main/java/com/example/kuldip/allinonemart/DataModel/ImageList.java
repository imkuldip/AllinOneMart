package com.example.kuldip.allinonemart.DataModel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ImageList {
    @SerializedName("images")
    private ArrayList<ImageUrl> images;

    public ImageList(){

    }

    public ArrayList<ImageUrl> getImageList(){
        return images;
    }
}
