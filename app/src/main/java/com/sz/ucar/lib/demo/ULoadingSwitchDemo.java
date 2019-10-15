package com.sz.ucar.lib.demo;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.ULoadingSwitch;

public class ULoadingSwitchDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_loading_switch_layout);
        final ULoadingSwitch uLoadingSwitch = findViewById(R.id.loading_switch);
        uLoadingSwitch.setOnCheckedChangedListener(new ULoadingSwitch.OnCheckedChangedListener() {
            @Override
            public void onCheckedChanged(ULoadingSwitch checkBox, boolean checked) {
                if (checked){
                    uLoadingSwitch.startLoading();//内存泄漏？
                }
            }
        });
    }
}
