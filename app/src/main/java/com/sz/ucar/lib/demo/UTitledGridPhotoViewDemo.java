package com.sz.ucar.lib.demo;

import android.os.Bundle;
import android.util.Log;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.sz.ucar.bwued.lib.gridphoto.UGridPhotoData;
import com.sz.ucar.bwued.lib.gridphoto.UGridPhotoView;
import com.sz.ucar.bwued.lib.gridphoto.UTitledGridPhotoView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UTitledGridPhotoViewDemo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.u_titled_grid_photoview_layout);
        testTitledGridPhotoView();
    }


    private void testTitledGridPhotoView(){
        List<String> list =
                Arrays.asList("https://img3.doubanio.com/view/subject/l/public/s29807952.jpg",
                        "https://img3.doubanio.com/view/subject/l/public/s29867342.jpg",
                        "https://img1.doubanio.com/view/subject/l/public/s29951129.jpg");
        List<UGridPhotoData> dataList = new ArrayList<>(list.size());
        for (String s : list) {
            UGridPhotoData data = new UGridPhotoData();
            data.setUrl(s);
            data.setState(UGridPhotoView.ItemState.SHOW);
            dataList.add(data);
        }
        UTitledGridPhotoView gridPhotoView = findViewById(R.id.tgpv_2);
        gridPhotoView.setDataList(dataList);
        gridPhotoView.setOnUrlUpdateListener(new UGridPhotoView.OnUrlUpdateListener() {
            @Override
            public void onUrlUpdate(List<String> urls) {
                Log.d("xxx", "onPhotoUpdate: " + urls);
            }
        });
    }
}
