package com.sz.ucar.lib.demo.okhttp3;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.lib.demo.R;

import java.io.File;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class DemoOkHttp3Activity extends BaseActivity {

    private TextView mTextView;

    public static final String TAG = "DemoOkHttp3Activity";

    class LoggerInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request request = chain.request();
            Log.e(TAG, "ybw request url : " + request.url());
            request.cacheControl();

            Response response = chain.proceed(request);
            if (response.body() != null) {
                Log.e(TAG, "ybw response :" + response.message());
            }

            return response;
        }
    }

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {
        mTextView = findViewById(R.id.tv_retrofit);
    }

    @Override
    public void doBusiness(Context context) {

        OkHttpClient client = new OkHttpClient()
                .newBuilder()
                .addInterceptor(new LoggerInterceptor())
                .build();
        Request request = new Request.Builder().url("http://yapitest.ucarinc.com/mock/363/api/user/getName?id=10")
                                               .build();
        /*new Thread(new Runnable() {
            @Override
            public void run() {

                try {
                    Response response = client.newCall(request)
                                              .execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();*/

        client.newCall(request)
              .enqueue(new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {

                  }
              });

        FormBody.Builder formBody = new FormBody.Builder();
        formBody.add("name", "1111");

        String jsonStr = "{\"username\":\"lisi\",\"nickname\":\"李四\"}";//json数据.
        RequestBody requestBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), jsonStr);

        request = new Request.Builder().url("http://yapitest.ucarinc.com/mock/363/api/getPerson")
                                       .post(formBody.build())
                                       .build();

        client.newCall(request)
              .enqueue(new Callback() {
                  @Override
                  public void onFailure(Call call, IOException e) {

                  }

                  @Override
                  public void onResponse(Call call, Response response) throws IOException {
                      showMessage(response);

                      response.body().byteStream();
                  }
              });

        File file = new File("");
        MultipartBody body = new MultipartBody.Builder().setType(MultipartBody.FORM)
                                                        .addFormDataPart("id", "10")
                                                        .addFormDataPart("file", "fileName",
                                                                         RequestBody.create(MediaType.parse("file/*"),
                                                                                            file))
                                                        .build();
    }

    public void showMessage(Response response) {
        try {
            if (response.isSuccessful()) {
                final String str = response.body()
                                           .string();
                Gson gson = new Gson();
                Person person = gson.fromJson(str, Person.class);

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText("姓名：" + person.getName() + ", 年龄：" + person.getAge());
                    }
                });
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public class Person {

        private String name;
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }

    @Override
    public int contentLayout() {
        return R.layout.demo_retrofit_layout;
    }
}
