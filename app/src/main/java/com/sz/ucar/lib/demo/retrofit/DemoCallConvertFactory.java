package com.sz.ucar.lib.demo.retrofit;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class DemoCallConvertFactory extends Converter.Factory {

    private DemoCallConvertFactory(){

    }

    public static Converter.Factory create() {
        return new DemoCallConvertFactory();
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        return new DemoConvert();
    }

    public static class DemoConvert implements Converter<ResponseBody, String> {

        @Override
        public String convert(ResponseBody value) throws IOException {
            return value.string();
        }
    }
}
