package com.sz.ucar.lib.demo;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.historyselect.UHistorySelectLayout;

public class UHistorySelectLayoutDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_history_select_layout);
        UHistorySelectLayout uHistorySelectLayout = findViewById(R.id.history_select_container);
        uHistorySelectLayout.setListener(new UHistorySelectLayout.HistorySelectListener() {
                @Override
            public void onClear() {

            }

            @Override
            public boolean onSelect(String text) {
                return false;
            }

            @Override
            public void saveContent(@NonNull String content) {

            }

            @Override
            public String getSaveContent() {
                return null;
            }
        });
        uHistorySelectLayout.addItem("Test");
        uHistorySelectLayout.addItem("你好");
        uHistorySelectLayout.addItem("苹果手机");
        uHistorySelectLayout.addItem("神州优车");
        uHistorySelectLayout.mayShow();
    }
}
