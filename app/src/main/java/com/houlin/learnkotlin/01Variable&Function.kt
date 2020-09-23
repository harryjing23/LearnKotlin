package com.houlin.learnkotlin

/**
 * @author Harry Jing
 * @date 2020/9/22
 */

// 函数可以定义在文件的最外层，不需要放在类中
// 变量的声明。变量名: 数据类型
fun main(args: Array<String>) {
    println("hello world")
}

// 返回值类型放在参数列表的后面
fun max(a: Int, b: Int): Int {
    return if (a > b) a else b// if是有结果值的表达式，不是语句
}

// 表达式体函数。去掉了花括号、用"="代替return
// 类型推导。表达式体函数的返回类型可以省略，编译器会根据表达式体自动推导
fun max1(a: Int, b: Int) = if (a > b) a else b

// 类型推导。变量声明时若初始化，也可以省略数据类型
val answer = 42


// 不可变变量。对应Java的final变量，只能初始化一次。变量尽量都定义为val
val value = "value"

// 可变变量
var variable = "variable"

// 字符串模板。在字符串字面量中引用变量，在变量前加"$"
fun template(args: Array<String>) {
    println("I am $value!!!")
    if (args.size > 0) {
        // 还可以引用表达式。但表达式需要用{}括起来
        println("You are ${args[0]}!!!")
    }
    // 还可以在{}中直接嵌套双引号
    println("Hello, ${if (args.size > 0) args[0] else "someone"}!!!")
}

