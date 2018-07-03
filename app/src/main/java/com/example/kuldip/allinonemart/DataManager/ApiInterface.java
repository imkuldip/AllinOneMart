package com.example.kuldip.allinonemart.DataManager;

import com.example.kuldip.allinonemart.DataModel.AdImageList;
import com.example.kuldip.allinonemart.DataModel.GetCategory;
import com.example.kuldip.allinonemart.DataModel.HomeImageList;
import com.example.kuldip.allinonemart.DataModel.ImageList;
import com.example.kuldip.allinonemart.DataModel.InsertUserDetail;
import com.example.kuldip.allinonemart.DataModel.NumberOfView;
import com.example.kuldip.allinonemart.DataModel.UpdateStatus;
import com.example.kuldip.allinonemart.DataModel.ViewImageSelected;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("display_view.php")
    @FormUrlEncoded
    public Call<List<NumberOfView>>getView(@Field("category_id") String c_id);


    @POST("select_category.php")
    public Call<List<GetCategory>>getCategory();

    @POST("select_image.php")
    @FormUrlEncoded
    public Call<ImageList>getImageList(@Field("pasal_name") String pasalName);

    @POST("select_homeimage.php")
    public Call<HomeImageList>getHomeImageList();

    @POST("select_adimage.php")
    public Call<AdImageList>getAdImageList();

    @POST("insert_user_detail.php")
    @FormUrlEncoded
    public Call<InsertUserDetail>insertUserDetail(@Field("pasal_name") String pasalName,@Field("category_id") String category_id,
                                                  @Field("user_name") String user_name,@Field("user_phone") String user_phone,
                                                  @Field("user_address") String user_address, @Field ("user_purpose") String user_purpose
                                                    );

    @POST("update_status.php")
    @FormUrlEncoded
    public Call<UpdateStatus>updateStatus(@Field("pasal_name") String pasal_name, @Field("category_id") String category_id);
}
