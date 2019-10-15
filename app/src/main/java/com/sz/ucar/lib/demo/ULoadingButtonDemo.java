package com.sz.ucar.lib.demo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.bw.cardock.business.base.view.widget.LoadingButton;
import com.sz.ucar.bwued.lib.ULoadingButton;

public class ULoadingButtonDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_loading_button_layout_demo);

        ULoadingButton loadingButton = findViewById(R.id.loading_button);
        loadingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingButton.startLoading("I’m loading");
            }
        });
    }
}
