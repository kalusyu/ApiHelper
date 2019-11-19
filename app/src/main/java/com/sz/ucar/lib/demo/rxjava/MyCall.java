package com.sz.ucar.lib.demo.rxjava;

import com.google.gson.Gson;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Call;
import retrofit2.Response;

public class MyCall<R> {

    private Observable<Response<MyResponse<R>>> mObservable;

    private Call<MyResponse<R>> mCall;

    public MyCall(Call<MyResponse<R>> call) {

        mCall = call;

        mObservable = RxJavaPlugins.onAssembly(new MyObservable<>(call));
    }

    public void enqueue(CallListener<R> listener) {
        mObservable.subscribeOn(Schedulers.io())
                   .observeOn(AndroidSchedulers.mainThread())
                   .subscribe(new Consumer<Response<MyResponse<R>>>() {
                       @Override
                       public void accept(Response<MyResponse<R>> myResponseResponse) throws Exception {
                           listener.onSuccess(myResponseResponse.body());
                       }
                   }, new Consumer<Throwable>() {
                       @Override
                       public void accept(Throwable throwable) throws Exception {
                            listener.onError(throwable);
                       }
                   });
    }

    public interface CallListener<T>{
        void onSuccess(MyResponse<T> t);

        void onError(Throwable t);
    }
}
