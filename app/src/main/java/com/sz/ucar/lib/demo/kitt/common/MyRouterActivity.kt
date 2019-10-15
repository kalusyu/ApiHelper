package com.sz.ucar.lib.demo.kitt.common

import android.content.Context
import android.view.View
//import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.sz.bw.cardock.business.base.activity.BaseActivity
import com.sz.ucar.lib.demo.R
import kotlinx.android.synthetic.main.kitt_activity_arouter_demo.*

@Route(path = "/testyu/activityFirst")
class MyRouterActivity : BaseActivity(){

    @JvmField
    var age = 0

//    @Autowired
    @JvmField
    var name = null

//    @Autowired(name = "test")
    @JvmField
    var arouter = null

    override fun initParms() {
    }

    override fun contentLayout(): Int {
        return R.layout.kitt_activity_arouter_demo
    }

    override fun doBusiness(p0: Context?) {

        if (arouter is ARouterBean){

        }
        kitt_textview_demo.text = "I'm from kotlin arouter $name , age is $age"
    }

    override fun initView(p0: View?) {
    }
}