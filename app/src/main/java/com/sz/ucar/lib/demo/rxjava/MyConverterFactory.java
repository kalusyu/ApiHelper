package com.sz.ucar.lib.demo.rxjava;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class MyConverterFactory extends Converter.Factory {

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return super.responseBodyConverter(type, annotations, retrofit);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations,
            Annotation[] methodAnnotations, Retrofit retrofit) {
        return super.requestBodyConverter(type, parameterAnnotations, methodAnnotations, retrofit);
    }

    public static class MyReSponseConverter<T> implements Converter<ResponseBody, T> {

        @Override
        public T convert(ResponseBody value) throws IOException {
            return null;
        }
    }

    public static class MyRequestConverter<T> implements Converter<T, RequestBody> {

        @Override
        public RequestBody convert(T value) throws IOException {
            return null;
        }
    }
}
