package com.sz.ucar.lib.demo.rxjava;

import com.sz.ucar.apihelper.calladapter.ParameterizedTypeImpl;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

public class MyCallAdapterFactory extends CallAdapter.Factory {

    private MyCallAdapterFactory() {}

    public static CallAdapter.Factory create() {
        return new MyCallAdapterFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Class<?> rawType = getRawType(returnType);
        if (rawType != MyCall.class) {
            return null;
        }
        Type responseType = getParameterUpperBound(0, (ParameterizedType) returnType);
        return new MyCallAdapter<>(responseType);
    }

    public static class MyCallAdapter<R> implements CallAdapter<MyResponse<R>, MyCall<R>> {

        private Type responseType;

        public MyCallAdapter(Type responseType) {
            this.responseType = responseType;
        }

        @Override
        public Type responseType() {
            // 学习点
            return new ParameterizedTypeImpl(new Type[]{responseType}, null, MyResponse.class);
        }

        @Override
        public MyCall<R> adapt(Call<MyResponse<R>> call) {
            return new MyCall<>(call);
        }
    }
}
