package com.example.apihelper;

import java.lang.annotation.Annotation;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Retrofit;

class MyCallAdaptorFactory extends CallAdapter.Factory {

    private MyCallAdaptorFactory() {}

    public static CallAdapter.Factory create() {
        return new MyCallAdaptorFactory();
    }

    @Override
    public CallAdapter<?, ?> get(Type returnType, Annotation[] annotations, Retrofit retrofit) {
        Type rawType = getRawType(returnType);
        if (rawType != UCall.class) {
            return null;
        }

        Type reponseType = getParameterUpperBound(0, (ParameterizedType) returnType);

        return new MyCallAdapter<>(reponseType);
    }

    private static class MyCallAdapter<T> implements CallAdapter<UResponse<T>, UCall<T>> {

        private Type responseType;

        public MyCallAdapter(Type reponseType) {
            this.responseType = reponseType;
        }

        @Override
        public Type responseType() {
            return new MyParameterizeImpl(new Type[]{responseType}, null, UResponse.class);
        }

        @Override
        public UCall<T> adapt(Call<UResponse<T>> call) {
            return new UCall<>(call);
        }
    }
}
