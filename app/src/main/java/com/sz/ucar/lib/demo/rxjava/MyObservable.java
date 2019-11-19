package com.sz.ucar.lib.demo.rxjava;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import retrofit2.Call;
import retrofit2.Response;

class MyObservable<R> extends Observable<Response<R>> {

    private Call<R> originalCall;

    public MyObservable(Call<R> call) {
        originalCall = call;
    }

    @Override
    protected void subscribeActual(Observer observer) {
        // TODO remote request
        Call<R> call = originalCall.clone();
        MyCallback<R> callback = new MyCallback<>(call, observer);
        observer.onSubscribe(callback);
        if (!callback.isDisposed()) {
            call.enqueue(callback);
        }
    }

    private static class MyCallback<R> implements Disposable, retrofit2.Callback<R> {

        private Observer<? super Response<R>> mObserver;
        private Call<R> mRCall;

        public MyCallback(Call<R> call, Observer observer) {
            this.mRCall = call;
            this.mObserver = observer;
        }

        @Override
        public void dispose() {

        }

        @Override
        public boolean isDisposed() {
            return false;
        }

        @Override
        public void onResponse(Call<R> call, Response<R> response) {
            mObserver.onNext(response);
        }

        @Override
        public void onFailure(Call<R> call, Throwable t) {

        }
    }
}
