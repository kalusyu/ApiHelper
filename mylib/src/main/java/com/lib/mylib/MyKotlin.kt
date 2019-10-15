package com.lib.mylib

import javafx.scene.Parent

class MyKotlin {


}

class EmptyClass{
    constructor(parent: EmptyClass) {

    }
}

class Person public constructor(name: String) {

    init {
        println("First initializer block that prints $name")
    }
    var children: MutableList<Person> = mutableListOf<Person>()

    constructor(parent: Person) : this("") {    // 次构造函数需要委托给主构造函数
        println("Secondary constructor")
    }
}

open class Shape{
    open fun draw(){}
    fun fill(){}
}

class Circle : Shape(){
    override fun draw() {
        super.draw()
    }

}

infix fun Int.add(a : Int) : Int {
    return this + a
}

interface Base{
    fun print()
}

class BaseImpl(val x: Int) :Base{
    override fun print() {
        println("BaseImpl $x")
    }
}

class Derived(b: Base) : Base by b {
    override fun print() {
        println("Derived abc") //会覆盖委托的方法
    }
}




val PI = 3.1415926

fun main() {

    val b = BaseImpl(10)
    Derived(b).print()

    val testInfix = 1 add 200
    println("testInfix = $testInfix")
    val person = Person("kalus")

    println("Helllo Kotlin")
    println("2 + 3  = " + sum(2, 3))

    var a: Int = 3
    val c = 3
    val d: Int
    d = 5
    val s1 = "a is $a"
    println(s1)
    a = 2
    val s2 = "${s1.replace("is", "was")}, but now is $a"
    println(s2)

    println("maxOf = " + maxOf(10, 200))

    val items = listOf<String>("java", "c++", "kotlin")
    for (item in items) {
        print(item)
    }

    println("")
    println("Another for expression")
    for (index in items.indices) {
        print(items[index])
    }

    println()
    println("======================")
    var index = 0
    while(index < items.size) {
        println("this is model ${items[index]}")
        index++
    }

    var x : Any
    x = "sss"
    when(x) {
        10 -> println("number")
        "sss" -> println("none")
        else -> println("UnKnown")
    }

    println()

    for (i in 1..10 step 2){
        print("$i ")
    }


    //=============== Array ==============
    println()
    val arr = arrayOf("SDK", "JDK", "VERSION")
    arr.forEach { println("array $it") }

    var arr2 = Array(5) { i -> (i * i).toString() }
    arr2.forEach { println("arr2 $it") }

    val arr3 :IntArray = intArrayOf(1, 2, 3)
    IntArray(5)

    //============== String ===================
    val str1 = "Hello World"
    str1.forEach { print(" $it ," ) }
    println()

    val str2 = """
        for (c in "foo")
            println(c)
    """.trimIndent()
    println("str2 $str2")
    val str3 = null
    str3 ?: println("str3 is null")


}

fun sum(a: Int, b: Int): Int {
    return a + b
}

fun maxOf(a: Int, b: Int) = if (a > b) a else b

//fun sum(a: Int, b:Int) = a + b;