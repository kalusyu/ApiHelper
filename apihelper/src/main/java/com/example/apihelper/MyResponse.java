package com.example.apihelper;

import java.io.Serializable;

/**
 * @author biaowen.yu
 * @date created at : 2019/11/19
 **/
public class MyResponse<T> implements Serializable {

    private String msg;
    private String code;

    private T content;

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
