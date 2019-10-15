package com.example.databindingdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBinderMapperImpl;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.Observable;

import android.os.Bundle;
import android.util.Log;

import com.example.databindingdemo.BR;
import com.example.databindingdemo.databinding.MydataBinding;

import java.util.Random;

public class MainActivity extends AppCompatActivity {


    private Goods goods;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        activityMainBinding = DataBindingUtil.
//                setContentView(this, R.layout.activity_main);
//        User userInfo = new User("ybw", 100);
//        activityMainBinding.setUserInfo(userInfo);
        // ActivityMainBinding 是根据布局文件的名字生成的
        // MydataBinding 是通过 data class="MydataBinding" 自定义得到的
        MydataBinding activityMainBinding = DataBindingUtil.setContentView(
                this,
                R.layout.activity_main
        );

//        User user = new User();
//        user.setName("ybw");
//        user.setPassword(1234);
//        user.setPwd("12345");
//        activityMainBinding.setUserInfo(user);

        goods = new Goods();
        goods.name = "code";
        goods.setDetails("hi");
        goods.setPrice(10.0F);
        goods.addOnPropertyChangedCallback(new Observable.OnPropertyChangedCallback() {
            @Override
            public void onPropertyChanged(Observable sender, int propertyId) {
                String TAG = "ybw";
                if (propertyId == com.example.databindingdemo.BR.name) {
                    Log.e(TAG, "BR.name");
                } else if (propertyId == com.example.databindingdemo.BR.details) {
                    Log.e(TAG, "BR.details");
                } else if (propertyId == com.example.databindingdemo.BR._all) {
                    Log.e(TAG, "BR._all");
                } else {
                    Log.e(TAG, "未知");
                }
            }
        });

        activityMainBinding.setGoods(goods);
        activityMainBinding.setGoodsHandler(new GoodsHandler());


    }


    public class GoodsHandler{
        public void changeGoodsName(){
            goods.setName("code" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }

        public void changeGoodsDetails() {
            goods.setDetails("hi" + new Random().nextInt(100));
            goods.setPrice(new Random().nextInt(100));
        }
    }


}
