package com.example.databindingdemo;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

public class User extends BaseObservable {
    private String name;
    private int password;
    private String pwd;

    public User(){}
    public User(String name, int password) {
        this.name = name;
        this.password = password;
    }

    @Bindable
    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    @Bindable
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bindable
    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }
}
