package com.houlin.learnkotlin.function

/**
 * @author HarryJ
 * @date 2/27/21
 */
// Kotlin中，集合与Java相同，但做了API扩展（使用的就是扩展函数）

// 可变参数的本质就是数组，故可以用数组代替可变参数，访问时也与数组相同

// Java中的可变参数用三个点声明（"String... s"），每个函数只能有一个，且必须是最后一个参数
// Kotlin中的可变参数用vararg声明（"vararg a: Int"），也只能有一个，但不要求是最后一个参数

fun varargTest(args: Array<String>) {
    // Kotlin中需要解包数组（在数组前加*）才能给可变参数传入数组，且可以再继续添加参数
    val list = listOf("args: ", *args)
    // Java中可以直接给可变参数传入一个数组类型，但传入了数组就不能再有其他参数
    mapOf(1 to "one")
}

// 中缀调用。函数名直接放在目标对象和参数之间，类似于用运算符的形式调用函数。eg. 1 to "one"，则调用了to函数
// 适用于只有一个参数的函数，用infix修饰函数即可开启中缀调用
fun middle() {
    val map = mapOf(1 to "one", 2 to "two")
    // 1 to "one" 相当于普通调用 1.to("one")
}

// 解构声明。将一个对象解构成若干变量
// 可用在Pair、Map、循环中、遍历Map时、数据类（data class）
fun <T> dec(collection: Collection<T>) {
    // 解构出来的变量要用()括起来
    for ((index, element) in collection.withIndex()) {
        println("$index: $element")
    }
}