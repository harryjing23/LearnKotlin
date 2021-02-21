package com.houlin.learnkotlin.function

/**
 * @author HarryJ
 * @date 2/21/21
 */

// 扩展函数就是一个类的成员函数，但定义在类的外面（下面是lastChar()）
// 接收者类型是要扩展的类或接口（下面接收者类型为String），接收者对象是调用扩展函数的对象（下面是this）
fun String.lastChar(): Char = this.get(this.length - 1)

fun extendFunction() {
    // 调用扩展函数与调用成员函数一样，直接用实例调用
    "Kotlin".lastChar()
}

// 在扩展函数中可以不用使用this，可以像在成员函数中一样省略this
fun String.lastChar2(): Char = get(length - 1)

// 扩展函数中可以直接访问接收者类型的其他方法和属性，但不能访问private和protected的成员