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

// 扩展函数实质上是静态函数（因为是顶层函数），将接受者对象作为它第一个参数。故Java可以