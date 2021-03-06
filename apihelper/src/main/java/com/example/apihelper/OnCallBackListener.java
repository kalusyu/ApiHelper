package com.example.apihelper;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
public interface OnCallBackListener<T> {
    void onSuccess(UResponse<T> response);

    void onError(Throwable throwable);
}
