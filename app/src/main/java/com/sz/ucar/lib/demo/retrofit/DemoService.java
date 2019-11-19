package com.sz.ucar.lib.demo.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/11
 **/
public interface DemoService {

    @GET("/mock/363//api/user/getName")
    String getName(@Query("id") String id);
}
