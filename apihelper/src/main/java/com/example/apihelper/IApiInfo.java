package com.example.apihelper;

public interface IApiInfo<T> {

    Class<T> getClazz();

    String getBaseUrl();
}
