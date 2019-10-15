package com.sz.ucar.bwued.lib.demo;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author android SZZC plugin template
 */
public interface ServerApi {

    @GET("data/{type}")
    Observable<JSONObject> getData(@Path("type") String type);

    @GET("datas/{type}/repos")
    Call<List>  getDataList(@Path("type") String type);

}
