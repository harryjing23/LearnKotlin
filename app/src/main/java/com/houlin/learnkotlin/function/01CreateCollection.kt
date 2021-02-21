package com.houlin.learnkotlin.function

/**
 * @author HarryJ
 * @date 2/21/21
 */

// 可以利用...Of来直接创建对应的集合
fun create() {
    val set = hashSetOf(1, 7, 53)
    val list = arrayListOf(1, 7, 53)
    val map = hashMapOf(1 to "one", 7 to "seven", 53 to "fifty-three")// to是普通函数
    println(map.javaClass)// 打印map的对象类型
}

// 虽然Kotlin使用的是Java集合类，但集合的操作上有优化
fun optimized() {
    val strings = listOf("first", "second", "fourteenth")
    // 获取list的最后一个元素
    println(strings.last())

    val numbers = setOf(1, 14, 2)
    // 获取set中最大的一个元素
    println(numbers.max())
}