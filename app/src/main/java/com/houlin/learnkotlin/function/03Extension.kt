package com.houlin.learnkotlin.function

import com.houlin.learnkotlin.function.lastChar2 as lastChar3

/**
 * @author HarryJ
 * @date 2/21/21
 */

// 扩展函数就是一个类的成员函数，但定义在类的外面->顶层函数（下面是lastChar()）
// 接收者类型是要扩展的类或接口（下面接收者类型为String），接收者对象是调用扩展函数的对象（下面是this）
fun String.lastChar(): Char = this.get(this.length - 1)

fun extendFunction() {
    // 调用扩展函数与调用成员函数一样，直接用实例调用
    "Kotlin".lastChar()
}

// 在扩展函数中可以不用使用this，可以像在成员函数中一样省略this
fun String.lastChar2(): Char = get(length - 1)

// 扩展函数中可以直接访问接收者类型的其他方法和属性，但不能访问private和protected的成员

// 扩展函数像类或函数一样，在不同的包使用时需要import
// 为了避免同名。import还可以使用as来修改导入的类名或函数名，也可以使用类或函数的全名来避免
fun test() {
    "Kotlin".lastChar3()
}

// 扩展函数实质上是静态函数（因为是顶层函数），将接受者对象作为它第一个参数。故Java也可以调用

// 当然集合类也可以作为扩展函数的接受者类型
fun <T> Collection<T>.joinToString(separator: String = ", ",
                                   prefix: String = "(",
                                   postfix: String = ")"): String {
    val result = StringBuilder(prefix)
    for ((index, element) in this.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 扩展函数因为是静态函数，所以不能重写
// ###当给父类和子类分别定义了同名的扩展函数，再用子类对象创建父类的数据类型时。
// 则扩展函数会调用父类的，因为对象的数据类型是父类
// 当一个类的成员函数和扩展函数的签名相同时，会优先调用成员函数


// 扩展属性并没有真正为该类添加属性，只是通过getter和setter计算出属性。故没有初始值，只能通过getter或setter访问
// 扩展属性可以访问接收者对象的其他属性（像扩展函数一样）
val String.lastChar: Char
    get() = this.get(this.length - 1)

// 扩展属性：val要提供getter，var要提供getter和setter
var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value: Char) {
        setCharAt(length - 1, value)
    }

fun test1() {
    println("Kotlin".lastChar)

    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
}