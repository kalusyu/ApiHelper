package com.sz.ucar.lib.demo.kitt.common;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.sz.ucar.lib.demo.R;

/**
 * @author biaowen.yu
 */
@Route(path = "/testyu/activity")
public class MyAnotherRouterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.kitt_activity_arouter_demo);
        TextView textView = findViewById(R.id.kitt_textview_demo);
        textView.setText("a router navigation to me");
    }
}
