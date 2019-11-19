package com.example.apihelper;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
public class MyCall<T> {

    private Observable<Response<MyResponse<T>>> mObservable;

    public MyCall(Call<MyResponse<T>> call) {
        mObservable = RxJavaPlugins.onAssembly(new MyObservable(call));
    }

    public void enqueue(final OnCallBackListener<T> listener) {
        mObservable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<Response<MyResponse<T>>>() {
                       @Override
                       public void accept(Response<MyResponse<T>> myResponseResponse) throws Exception {

                           listener.onSuccess(myResponseResponse.body());
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                           listener.onError(throwable);
                       }
                   });
    }
}
