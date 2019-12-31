package com.example.apihelpertest;


import com.example.apihelper.UCall;

import retrofit2.http.POST;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
public interface IMyInterface {

    @POST("api/getPerson")
    UCall<Person> getPerson();
}
