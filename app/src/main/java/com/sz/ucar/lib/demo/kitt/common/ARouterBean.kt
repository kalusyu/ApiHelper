package com.sz.ucar.lib.demo.kitt.common

import java.io.Serializable

class ARouterBean : Serializable{
    var age = 0
    var name:String

    constructor(age: Int = 3, name: String = "kalusyu") {
        this.age = age
        this.name = name
    }
}