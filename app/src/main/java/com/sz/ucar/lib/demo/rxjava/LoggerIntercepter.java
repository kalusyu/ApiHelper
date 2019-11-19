package com.sz.ucar.lib.demo.rxjava;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Response;

import static com.kernal.smartvisionocr.RecogService.TAG;

public class LoggerIntercepter implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        Log.e(TAG, "chain " + chain.request().url());
        return chain.proceed(chain.request());
    }
}
