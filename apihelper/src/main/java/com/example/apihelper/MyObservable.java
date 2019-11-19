package com.example.apihelper;

import io.reactivex.Maybe;
import io.reactivex.MaybeObserver;
import io.reactivex.Observable;
import io.reactivex.Observer;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
class MyObservable<T> extends Observable<Response<T>> {

    private Call<T> originalCall;

    @Override
    protected void subscribeActual(final Observer<? super Response<T>> observer) {
        Call<T> call = originalCall.clone();
        call.enqueue(new Callback<T>() {
            @Override
            public void onResponse(Call<T> call, Response<T> response) {
                observer.onNext(response);
                observer.onComplete();
            }

            @Override
            public void onFailure(Call<T> call, Throwable t) {
                observer.onError(t);
            }
        });
    }

    public MyObservable(Call<T> call) {
        originalCall = call;
    }
}
