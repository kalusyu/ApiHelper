package com.sz.ucar.lib.demo.kitt.common

import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.alibaba.android.arouter.facade.annotation.Route
import com.alibaba.android.arouter.launcher.ARouter
import com.sz.bw.cardock.business.base.activity.BaseActivity
import com.sz.bw.cardock.business.base.fragment.DataEmptyFragment
import com.sz.ucar.bwued.lib.UTips
import com.sz.ucar.lib.demo.R
import kotlinx.android.synthetic.main.kitt_activity_demo.*

@Route(path = "/testyu/MainActivity")
class KittActivityDemo : BaseActivity() {


    override fun initParms() {

    }

    override fun contentLayout(): Int {
        return R.layout.kitt_activity_demo
    }


    override fun doBusiness(p0: Context?) {

//        mEditTextView3.setText("I'm from kotlin")
//        headerView.setTitle("HeaderView Title")
//        val btn = Button(p0)
//        setCustomLeftView(btn)
//
//        btn.setOnClickListener { UTips.toast(p0, "")}// kl lambda
//        btn.setOnClickListener { doSomthing(it) }
//        showLoading(true)
//
////        showInputMethod()
//        kitt_btn_demo.setOnClickListener{
//            ARouter.getInstance().build("/testyu/activityFirst")
//                    .withObject("test", ARouterBean(10, "Jerry"))
//                    .withInt("age", 3)
//                    .withString("name","aroutername").navigation()
//
//        }

        showEmptyFragment("I'm Empty Fragment")


    }

    override fun getFragmentContainerId(): Int {
        return R.id.kitt_container_activity
    }

    fun doSomthing(obj : Any) {
        if (obj is View) {
            UTips.toast(obj.context, "I was clicked and toast")
        }
    }

    override fun initView(p0: View?) {

    }

    override fun widgetClick(v: View?) {
        super.widgetClick(v)
    }

    override fun hasHeader(): Boolean {
        // header 是可定制的 包括图标或者是自定义view都可以
        return true // weather has headerview or not ,false headerview is gone
    }
}