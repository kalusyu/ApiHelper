package com.example.apihelpertest;

import android.os.Bundle;
import android.widget.TextView;

import com.example.apihelper.ApiHelper;
import com.example.apihelper.IApiInfo;
import com.example.apihelper.MyCall;
import com.example.apihelper.MyResponse;
import com.example.apihelper.OnCallBackListener;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IMyInterface service = ApiHelper.get()
                                        .getMyService(new Service());
        MyCall<Person> call = service.getPerson();
        call.enqueue(new OnCallBackListener<Person>() {
            @Override
            public void onSuccess(MyResponse<Person> response) {
                TextView textView = findViewById(R.id.textView);
                textView.setText("name: " + response.getContent()
                                                    .getName() + ",age = " + response.getContent()
                                                                                     .getAge());
            }

            @Override
            public void onError(Throwable throwable) {
                TextView textView = findViewById(R.id.textView);
                textView.setText("error = " + throwable);
            }
        });
    }

    private class Service implements IApiInfo<IMyInterface> {

        @Override
        public Class<IMyInterface> getClazz() {
            return IMyInterface.class;
        }

        @Override
        public String getBaseUrl() {
            return "http://yapitest.ucarinc.com/mock/363/";
        }
    }
}
