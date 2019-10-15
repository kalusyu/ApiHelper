package com.sz.ucar.lib.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.UStickyNestedScrollView;

public class UStickyNestedScrollViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_sticky_nested_scrollview_layout);
        UStickyNestedScrollView stickyNestedScrollView = findViewById(R.id.scroll_layout);
        View view = findViewById(R.id.u_tv3);
        View tv2 = findViewById(R.id.u_tv2);
        stickyNestedScrollView.setFloatingView(tv2);
        stickyNestedScrollView.setFloatingView(view);// 只能吸一个，前面一个会被覆盖
    }
}
