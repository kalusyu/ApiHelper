package com.sz.ucar.lib.demo.retrofit;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.sz.bw.cardock.business.base.activity.BaseActivity;
import com.sz.ucar.lib.demo.R;

public class DemoActivity extends BaseActivity {

    private TextView mTextView;

    @Override
    public void initParms() {

    }

    @Override
    public void initView(View view) {

        mTextView  = findViewById(R.id.tv_retrofit);
    }

    @Override
    public void doBusiness(Context context) {

        DemoModel model = new DemoModel();
        model.load("10", new DemoModel.DemoCallback<String>() {
            @Override
            public void onSunccess(String s) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mTextView.setText(s);
                    }
                });
            }

            @Override
            public void onFailed(Throwable t) {

            }
        });
    }

    @Override
    public int contentLayout() {
        return R.layout.demo_retrofit_layout;
    }
}
