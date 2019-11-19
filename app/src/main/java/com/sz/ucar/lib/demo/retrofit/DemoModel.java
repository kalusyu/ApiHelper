package com.sz.ucar.lib.demo.retrofit;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DemoModel {

    public static final String URL = "http://yapitest.ucarinc.com/";
    private DemoService mDemoService;

    public DemoModel() {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(URL)
                                                  /*.addConverterFactory(ScalarsConverterFactory.create())*/
                                                  /*.addConverterFactory(GsonConverterFactory.create())*/
                                                  .addCallAdapterFactory(DemoCallAdapterFactory.create())
                                                  .addConverterFactory(DemoCallConvertFactory.create())
                                                  .build();//默认没有调用client使用OkHttpClient
        mDemoService = retrofit.create(DemoService.class);
    }

    public void load(String id, DemoCallback<String> listener) {
        /*Call<RBody> call = mDemoService.getName(id);
        call.enqueue(new Callback<RBody>() {
            @Override
            public void onResponse(Call<RBody> call, Response<RBody> response) {


                String s = response.body().getName();
                listener.onSunccess(s);

            }

            @Override
            public void onFailure(Call<RBody> call, Throwable t) {
                listener.onFailed(t);
            }
        });*/
        new Thread(new Runnable() {
            @Override
            public void run() {
                String str = mDemoService.getName(id);
                listener.onSunccess(str);
            }
        }).start();
    }

    public interface DemoCallback<T> {

        void onSunccess(T t);

        void onFailed(Throwable t);
    }
}
