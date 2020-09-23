package com.houlin.learnkotlin

/**
 * @author Harry Jing
 * @date 2020/9/22
 */

// 相当于 字段声明 + 读、写访问器 + 构造函数
// val字段只会生成getter，var字段则生成getter和setter
class Person(val name: String)

fun access() {
    // 调用构造函数不需要new
    val person = Person("Bob")
    // 虽然可以直接访问属性，但实际上还是调用的访问器
    println("name is ${person.name}")
}

class Rectangle(val width: Int, val height: Int) {
    val isSquare: Boolean
        // 自定义访问器
        get() {
            return width == height
        }
}