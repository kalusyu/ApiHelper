package com.example.apihelper;

import java.io.Serializable;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
public class UResponse<T> implements Serializable {

    private String msg;
    private String code;

    private int time;

    private T content;

    private T data;

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }
}
