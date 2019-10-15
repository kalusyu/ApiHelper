package com.sz.ucar.lib.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class UDividerLinearLayoutDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startCalender(View view) {
        Intent intent = new Intent(this, UEditTextViewDemo.class);
        startActivity(intent);
    }
}
