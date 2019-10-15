package com.sz.ucar.lib.demo;

import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.sz.ucar.bwued.lib.UTips;
import com.sz.ucar.bwued.lib.form.BaseForm;
import com.sz.ucar.bwued.lib.form.CalendarFormItem;
import com.sz.ucar.bwued.lib.form.EditFormItem;
import com.sz.ucar.bwued.lib.form.FormItem;
import com.sz.ucar.bwued.lib.form.GridPhotoFormItem;
import com.sz.ucar.bwued.lib.form.SingleSelectionFormItem;
import com.sz.ucar.bwued.lib.form.TimePickFormItem;
import com.sz.ucar.bwued.lib.form.input.text.EditInput;
import com.sz.ucar.bwued.lib.form.input.text.TimePickInput;
import com.sz.ucar.bwued.lib.form.validator.PlateNumberValidator;
import com.sz.ucar.bwued.lib.form.validator.StringLengthValidator;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class TestForm extends BaseForm {


    public TestForm(@NotNull Context context) {
        super(context);
    }

    @NotNull
    @Override
    protected ArrayList<FormItem> makeItemList() {

        boolean[] booleans = new boolean[]{true, false};
        ArrayList<FormItem> list = new ArrayList<>();
        ArrayList<String> a = new ArrayList<>();
        a.add("是");a.add("否");
        ArrayList<Integer> b = new ArrayList<>();
        b.add(0); b.add(1);
        list.add(getItem1());
        list.add(new CalendarFormItem("日历选择", "yyyy/MM/dd", "请选择", "calendar"));
        list.add(new SingleSelectionFormItem("单项选择", a, b, "singleChoice"));

        TimePickInput.TimeStyle timeStyle = TimePickInput.Companion.getYMDHM();

        list.add(new TimePickFormItem("日期选择(YMDHM)", timeStyle, "请选择", "ymdhm", null, null, null));
        list.add(new TimePickFormItem("日期选择(YMD)", TimePickInput.Companion.getYMD(), "请选择", "ymd", null, null, null));
        GridPhotoFormItem formItem = new GridPhotoFormItem("选择图片", 10, "photo");
        formItem.getInput().setNullable(true);
        list.add(formItem);
        return list;
    }

    @Override
    public void submit(@NotNull JSONObject jsonObject) {
        UTips.toast(getContext(), jsonObject.toJSONString());
    }


    private FormItem getItem1() {
        EditFormItem item = new EditFormItem("车牌号", "请输入", "oneKey");
        EditInput input1 = (EditInput)item.getInput();
        input1.setInitValue("闽A12345");
        input1.addValidator(new StringLengthValidator(getContext(), 7, 8, "字符串长度需要在7-8之间"));
        input1.addValidator(new PlateNumberValidator(getContext(), "请输入正确的车牌号"));
        return item;
    }

}
