package com.sz.ucar.lib.demo.rxjava;

import retrofit2.http.GET;
import retrofit2.http.POST;

public interface MyInterface {

    @POST("/mock/363//api/getPerson")
    MyCall<Person> getPerson();
}
