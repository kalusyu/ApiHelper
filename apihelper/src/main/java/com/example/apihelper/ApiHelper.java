package com.example.apihelper;

import android.util.ArrayMap;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
public class ApiHelper {

    /**
     * 1. UResponse 2. UCall 3. MyObservable 4. MyCallAdaptorFactory 5. MyParameterizeImpl 6. MyInterceptor 7.
     */

    private static class Holder {

        private static ApiHelper sInstance = new ApiHelper();
    }

    public static ApiHelper get() {
        return Holder.sInstance;
    }

    private ArrayMap<ApiInfo<?>, Object> mService = new ArrayMap<>();

    public <T> T getMyService(IApiInfo<T> info) {

        ApiInfo<T> apiInfo = new ApiInfo<>(info.getClazz(), info.getBaseUrl());
        Object service = mService.get(apiInfo);

        if (service == null) {
            service = makeRetrofit(info);
            mService.put(apiInfo, service);
        }

        return (T) service;
    }

    private Object makeRetrofit(IApiInfo info) {
        OkHttpClient client = new OkHttpClient().newBuilder()
                                                .addInterceptor(MyInterceptor.create())
                                                .build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(info.getBaseUrl())
                                                  .client(client)
                                                  .addConverterFactory(GsonConverterFactory.create())
                                                  .addCallAdapterFactory(MyCallAdaptorFactory.create())
                                                  .build();
        Object service = retrofit.create(info.getClazz());
        return service;
    }

    private static class ApiInfo<T> {

        public ApiInfo(Class clazz, String baseUrl) {

        }
    }
}
