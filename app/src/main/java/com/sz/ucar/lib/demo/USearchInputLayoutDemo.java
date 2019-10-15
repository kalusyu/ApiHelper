package com.sz.ucar.lib.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.USearchInputLayout;

import org.jetbrains.annotations.NotNull;

public class USearchInputLayoutDemo extends AppCompatActivity {

    public static final String TAG = USearchInputLayoutDemo.class.getSimpleName();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_search_input_layout);
        USearchInputLayout uSearchInputLayout = findViewById(R.id.search_input_wrapper);
        uSearchInputLayout.setListener(new USearchInputLayout.SearchInputListener() {
            @Override
            public void onCancel() {
                Log.d(TAG, "onCancel");
            }

            @Override
            public void onTextChanged(@NotNull String s) {
                Log.d(TAG, "onTextChanged");
            }

            @Override
            public void openScan() {
                Log.d(TAG, "openScan");
            }

            @Override
            public void requestData(@NotNull String s) {
                Log.d(TAG, "requestData");
            }
        });
    }
}
