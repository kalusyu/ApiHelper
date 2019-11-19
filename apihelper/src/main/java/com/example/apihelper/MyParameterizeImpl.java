package com.example.apihelper;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
class MyParameterizeImpl implements ParameterizedType {

    private Type[] actualType;
    private Type rawType;
    private Type ownerType;

    public MyParameterizeImpl(Type[] types, Type ownerType, Type rawType) {
        this.actualType = types;
        this.rawType = rawType;
        this.ownerType = ownerType;
    }

    @NonNull
    @Override
    public Type[] getActualTypeArguments() {
        return actualType;
    }

    @NonNull
    @Override
    public Type getRawType() {
        return rawType;
    }

    @Nullable
    @Override
    public Type getOwnerType() {
        return ownerType;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(@Nullable Object obj) {
        return super.equals(obj);
    }
}
