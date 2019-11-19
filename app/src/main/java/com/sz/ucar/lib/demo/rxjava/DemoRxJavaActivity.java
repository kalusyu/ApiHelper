package com.sz.ucar.lib.demo.rxjava;

import android.content.Context;
import android.util.Log;
import android.view.View;

import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.lib.demo.R;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class DemoRxJavaActivity extends BaseActivity {

    public static final String TAG = "TAG";

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {

    }

    @Override
    public void doBusiness(Context context) {

        MyModel model = new MyModel();
        model.load();

        Observable observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();
            }
        });

        Observable.interval(104, TimeUnit.SECONDS)
                  .subscribe(new Consumer<Long>() {
                      @Override
                      public void accept(Long aLong) throws Exception {
                          Log.e(TAG, "accept interval aLong = " + aLong);
                      }
                  });

        Observable.just(1, 2, 3)
                  .map(new Function<Integer, String>() {
                      @Override
                      public String apply(Integer integer) throws Exception {
                          return integer + " my str";
                      }
                  })
                  .subscribe(new Consumer<String>() {
                      @Override
                      public void accept(String s) throws Exception {
                          Log.e(TAG, "map integer to String : " + s);
                      }
                  });

        Observable.just("1", "hello", "jame")
                  .subscribe(new Observer<String>() {
                      @Override
                      public void onSubscribe(Disposable d) {
                          Log.e(TAG, "onSubscribe");
                      }

                      @Override
                      public void onNext(String o) {
                          Log.e(TAG, "onNext o = " + o);
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.e(TAG, "onError");
                      }

                      @Override
                      public void onComplete() {
                          Log.e(TAG, "onComplete");
                      }
                  });

        List<Person> personList = new ArrayList<>();

        Observable.fromIterable(personList)
                  .flatMap(new Function<Person, ObservableSource<Plan>>() {
                      @Override
                      public ObservableSource<Plan> apply(Person person) throws Exception {
                          return Observable.fromIterable(person.getPlans());
                      }
                  })
                  .flatMap(new Function<Plan, ObservableSource<String>>() {
                      @Override
                      public ObservableSource<String> apply(Plan plan) throws Exception {
                          return Observable.fromIterable(plan.getActions());
                      }
                  })
                  .subscribe(new Consumer<String>() {
                      @Override
                      public void accept(String s) throws Exception {
                          Log.e(TAG, "action s" + s);
                      }
                  });

        Observable.just(1, 2, 3, 4, 5)
                  .window(2)
                  .subscribe(new Observer<Observable<Integer>>() {
                      @Override
                      public void onSubscribe(Disposable d) {
                          Log.e(TAG, "onSubscribe");
                      }

                      @Override
                      public void onNext(Observable<Integer> integerObservable) {
                          Log.e(TAG, "onNext o = ");
                          integerObservable.subscribe(new Observer<Integer>() {
                              @Override
                              public void onSubscribe(Disposable d) {
                                  Log.e(TAG, "integerObservable onSubscribe");
                              }

                              @Override
                              public void onNext(Integer integer) {
                                  Log.e(TAG, "integerObservable onNext integer = " + integer);
                              }

                              @Override
                              public void onError(Throwable e) {

                              }

                              @Override
                              public void onComplete() {
                                  Log.e(TAG, "integerObservable onComplete");
                              }
                          });
                      }

                      @Override
                      public void onError(Throwable e) {
                          Log.e(TAG, "onError");
                      }

                      @Override
                      public void onComplete() {
                          Log.e(TAG, "onComplete");
                      }
                  });
        Observable.just(1, 2, 3, 4, 5)
                  .subscribe(new Observer<Integer>() {
                      @Override
                      public void onSubscribe(Disposable d) {

                      }

                      @Override
                      public void onNext(Integer integer) {

                      }

                      @Override
                      public void onError(Throwable e) {

                      }

                      @Override
                      public void onComplete() {

                      }
                  });


        Observable.just(1,2,3,"h","3")
                  .ofType(String.class)
                  .subscribe(new Consumer<String>() {
                      @Override
                      public void accept(String s) throws Exception {
                          Log.e(TAG, "ofType " + s);
                      }
                  });
        Observable.just(1,2,3,"h","3")
                  .ofType(Integer.class)
                  .subscribe(new Consumer<Integer>() {
                      @Override
                      public void accept(Integer integer) throws Exception {

                      }
                  });
    }

    class Person {

        private String name;
        private List<Plan> plans;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<Plan> getPlans() {
            return plans;
        }

        public void setPlans(List<Plan> plans) {
            this.plans = plans;
        }
    }

    class Plan {

        private String name;
        private List<String> actions;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<String> getActions() {
            return actions;
        }

        public void setActions(List<String> actions) {
            this.actions = actions;
        }
    }

    @Override
    public int contentLayout() {
        return R.layout.demo_retrofit_layout;
    }
}
