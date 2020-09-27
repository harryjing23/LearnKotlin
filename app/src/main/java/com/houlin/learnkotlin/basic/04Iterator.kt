package com.houlin.learnkotlin.basic

import java.util.*

/**
 * @author Harry Jing
 * @date 2020/9/24
 */

// 循环的条件中，不需要定义循环变量的类型

// while和do while循环的语法与Java相同

// for循环
fun forPrint() {
    // "for in"组合。in后接区间或集合
    // ".."是区间运算符，递增闭区间。Kotlin默认为闭区间
    for (i in 1..100) {
        println(i)
    }

    // downTo 递减闭区间
    // step 步长
    for (i in 100 downTo 1 step 2) {
        println(i)
    }

    // util是半闭半开区间。[start, end)
    for (i in 1 until 100) {
        println(i)
    }
}

fun forCollection() {
    val intMap = TreeMap<Char, Int>()

    // ".."既可以创建数字区间，也可以创建字符区间
    for (c in 'A'..'F') {
        val intData = c.toInt()
        // Map不需要put和get。用map[key]=value赋值，用map[key]读取值
        intMap[c] = intData
    }

    // 用"for in"迭代集合。直接把集合的元素赋给in前面的括号中的变量
    for ((letter, numb) in intMap) {
        println("$letter -> $numb")
    }

    // arrayListOf()直接生成一个ArrayList
    // 所有集合貌似都可以用xxxOf直接生成
    val list = arrayListOf("10", "11", "12")
    //
    for ((index, element) in list.withIndex()) {
        println("$index : $element")
    }
}

// in运算符还可以检查一个值是否在区间内
fun isNotDigit(c: Char) = c !in '0'..'9'

// in也适用于when表达式
// 函数的返回值类型因类型推导而省略
fun recognize(c: Char) = when (c) {
    in '0'..'9' -> "It's a digit"
    in 'a'..'z', in 'A'..'Z' -> "It's a letter"
    else -> "I don't know"
}

// ### in可用于所有支持比较操作的类（实现了Comparable接口），因为底层会变换成(x>=start && x<=end)
// 虽然可能无法列举某个区间的所有对象，但是可以用来检查一个对象是否属于某个区间