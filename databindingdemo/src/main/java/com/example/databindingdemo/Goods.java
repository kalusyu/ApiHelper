package com.example.databindingdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import com.example.databindingdemo.BR;

public class Goods extends BaseObservable{

    @Bindable
    public String name;

    private String details;

    private float price;

    public void setName(String name) {
        this.name = name;
        notifyPropertyChanged(com.example.databindingdemo.BR.name);// notify this property
    }

    @Bindable
    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;

        notifyChange();// notify all
    }

    @Bindable
    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
