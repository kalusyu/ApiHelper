package com.sz.ucar.lib.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.USlideItemLayout;
import com.sz.ucar.bwued.lib.UTips;

public class USlideItemLayoutDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_slide_item_layout);
        USlideItemLayout uSlideItemLayout = findViewById(R.id.slide_item);

        uSlideItemLayout.setCanSlide(true);

        View view = findViewById(R.id.content_text);
        /*view.setOnClickListener((view1) -> {
            UTips.toast(this, "content clicked!");
        });

        view = findViewById(R.id.delete_text);
        view.setOnClickListener((view2) ->{
            UTips.toast(this, "delete clicked!");
            uSlideItemLayout.close(true);
        });*/
    }
}
