package com.sz.ucar.lib.demo.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

class DemoCallAdapterFactory extends CallAdapter.Factory {

    private DemoCallAdapterFactory() {}

    public static CallAdapter.Factory create() {
        return new DemoCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        return new DemoCallAdapter<>();
    }

    public class DemoCallAdapter<T> implements CallAdapter<String, String> {

        @Override
        public Type responseType() {
            return String.class;
        }

        @Override
        public String adapt(Call<String> call) {
            try {
                return call.execute()
                           .body();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}
