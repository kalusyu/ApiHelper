package com.sz.ucar.lib.demo.butterknife;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.UTips;
import com.sz.ucar.lib.demo.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ButterKnifeDemo extends AppCompatActivity {



//    @BindView(R.id.butter_textview)
    TextView mTextView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.butter_knife_demo);
        ButterKnife.bind(this);
//        mTextView.setText("Butter Knife demo");


    }

//    @OnClick(R.id.butter_btn)
//    void clickBtn(){
//        UTips.toast(this, "clickButter Knife");
//    }
}
