package com.example.kuldip.allinonemart.DataManager;

import com.example.kuldip.allinonemart.DataModel.InsertUserDetail;
import com.example.kuldip.allinonemart.DataModel.NumberOfView;
import com.example.kuldip.allinonemart.DataModel.ViewImageSelected;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiInterface {

    @POST("display_view.php")
    @FormUrlEncoded
    public Call<List<NumberOfView>>getView(@Field("category_id") Integer c_id);



    @POST("select_image.php")
    @FormUrlEncoded
    public Call<List<ViewImageSelected>>getImage(@Field("pasal_name") String pasalName);

    @POST("insert_user_detail.php")
    @FormUrlEncoded
    public Call<InsertUserDetail>insertUserDetail(@Field("pasal_name") String pasalName,@Field("category_id") String category_id,
                                                  @Field("user_name") String user_name,@Field("user_phone") String user_phone,
                                                  @Field("user_address") String user_address
                                                    );
}
