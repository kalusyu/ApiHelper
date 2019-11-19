package com.example.apihelper;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import static android.content.ContentValues.TAG;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
class MyInterceptor implements Interceptor {

    private MyInterceptor() {
        
    }

    public static Interceptor create() {
        return new MyInterceptor();
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        Log.e(TAG, "response = " + response);
        return response;
    }
}
