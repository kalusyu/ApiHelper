package com.sz.ucar.lib.demo.rxjava;

import android.util.Log;

import java.util.HashMap;
import java.util.Map;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MyModel {

    private Map<?, Object> mServices = new HashMap<>();
    MyInterface service;

    public MyModel() {
        OkHttpClient client = new OkHttpClient().newBuilder()
                                                .addInterceptor(new LoggerIntercepter())
                                                .build();
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://yapitest.ucarinc.com/")
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .addCallAdapterFactory(MyCallAdapterFactory.create())
                                                  .client(client)
                                                  .build();
        if (mServices.get(MyInterface.class) != null) {
            service = (MyInterface) mServices.get(MyInterface.class);
        }

        service = retrofit.create(MyInterface.class);
    }

    public void load() {
        MyCall<Person> call = service.getPerson();
        call.enqueue(new MyCall.CallListener<Person>() {
            @Override
            public void onSuccess(MyResponse<Person> t) {
                Person p = t.getContent();
                Log.e("ybw", "ybw p = " + p);
                System.out.println("ybw p = " + p);
            }

            @Override
            public void onError(Throwable t) {
                System.out.println("ybw t " + t);
            }
        });
    }

    public static void main(String[] args) {
        MyModel model = new MyModel();
        model.load();
        System.out.println("loadddddddd");
    }
}
