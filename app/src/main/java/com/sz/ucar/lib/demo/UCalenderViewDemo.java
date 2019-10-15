package com.sz.ucar.lib.demo;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.calendar.DateInfo;
import com.sz.ucar.bwued.lib.calendar.UCalenderView;

public class UCalenderViewDemo extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_calender_view_layout);
        UCalenderView view = findViewById(R.id.calendar_demo);
        UCalenderView.Attribute attribute = new UCalenderView.Attribute(this);
        attribute.dayViewTextColor = ColorStateList.valueOf(Color.BLUE);
        view.setAttr(attribute);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
//        UCalenderView.Attribute attribute = new UCalenderView.Attribute(this);
//        attribute.minDateInfo = new DateInfo(2019, 2, 1);
//        attribute.maxDateInfo = new DateInfo(2019, 12, 31);
//        attribute.defaultDateInfo = new DateInfo(2019, 2, 15);
//        attribute.defaultDateInfo2 = new DateInfo(2019, 2, 27);
//        attribute.pickSingleDate = false;

        //setContentView(R.layout.u_calender_view_layout);

//        TextView textView = new TextView(this);
//        textView.setText("Heloooooooo");
//        setContentView(textView);

//        UCalenderView view = findViewById(R.id.calendar_demo);
//        view.setAttr(attribute);


    }


}
