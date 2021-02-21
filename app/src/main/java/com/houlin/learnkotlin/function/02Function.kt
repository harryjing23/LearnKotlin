@file:JvmName("FunctionKt2")

package com.houlin.learnkotlin.function

/**
 * @author HarryJ
 * @date 2/21/21
 */

// 自定义集合的输出显示
fun <T> joinToString(collection: Collection<T>,
                     separator: String,
                     prefix: String,
                     postfix: String): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

// 命名参数。调用Kotlin定义的函数时，可以指明一些参数的名称
fun nameParams() {
    val set = setOf(1, 2, 3)
    joinToString(set, separator = ", ", prefix = "(", postfix = ")")
}

// 默认参数值。在声明函数时，可以指定参数的默认值，调用时就可以省略部分参数。故避免了创建多个重载函数
@JvmOverloads
fun <T> joinToString2(collection: Collection<T>,
                      separator: String = ", ",
                      prefix: String = "(",
                      postfix: String = ")"): String {
    val result = StringBuilder(prefix)
    for ((index, element) in collection.withIndex()) {
        if (index > 0) result.append(separator)
        result.append(element)
    }
    result.append(postfix)
    return result.toString()
}

fun defaultParams() {
    val set = setOf(1, 2, 3)
    // 调用有默认函数值的函数时，要按照定义的参数顺序来给定参数
    joinToString2(set, "; ")
    // 若使用命名参数，则可以忽略顺序
    joinToString2(separator = "; ", collection = set)
    // Java默认函数值的概念，所以Java调用Kotlin函数时必须指定所有参数
    // 或在Kotlin函数上注解@JvmOverloads，会生成Java重载函数。但默认参数值会被省略
}

// Kotlin文件在Java中会编译成"文件名Kt"类，修改该类的名称要用在文件第一行添加@file:JvmName("xxx")
// （字母开头的Kotlin文件名，会编译成"_文件名Kt"类）
// 顶层函数：文件最外层的函数会编译成该类的静态函数
// eg. 上面的defaultParams函数会编译成FunctionKt.defaultPrams()

// 顶层属性：与其他属性一样，通过getter和setter暴露给Java使用
// const val则不通过访问器，对应Java的public static final，而val对应Java的private static final + getter