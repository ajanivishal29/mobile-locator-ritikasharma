package com.calleridname.calldetailcallhistory.Main_Ads.retrofit;

import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.AdListResponsenew;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.LocaladsResponce;
import com.calleridname.calldetailcallhistory.Main_Ads.RetrofitResponce.WeatherapiResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.QueryMap;

/**
 * Created by anupamchugh on 09/01/17.
 */

public interface RetrofitInterface {


    @POST("adservice/get_Ritika_Sharma.php")
    @FormUrlEncoded
    Call<AdListResponsenew> getadsdetail(@Field("packagename") String packagename);

    @POST("localadservice/updatedownloadcount.php")
    @FormUrlEncoded
    Call<Object> updatecounter(@Field("packagename") String packagename);

    @POST("localadservice/get_ShanTech_LocalAds.php")
    @FormUrlEncoded
    Call<LocaladsResponce> localads(@Field("packagename") String packagename);

    @GET("data/2.5/weather")
    Call<WeatherapiResponse> weatherdetails(@QueryMap Map<String, String> params);
}
