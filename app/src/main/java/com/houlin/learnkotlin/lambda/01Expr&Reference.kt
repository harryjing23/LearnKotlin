package com.houlin.learnkotlin.lambda

import android.widget.Button

/**
 * 5.1 Lambda表达式和成员引用
 *
 * @author  Harry
 * @date  2021/5/27
 */

fun click(button: Button) {
    // 直接将代码块作为参数
    button.setOnClickListener {
        // do something
    }

    // lambda表达式要在花括号中，实参列表和函数体被"->"隔开
    val sum = { x: Int, y: Int -> x + y }
    // 调用时与普通函数相同
    println(sum(1, 2))
}

class Person(val name: String, val age: Int)

fun maxAge() {
    val p = listOf(Person("Alice", 10), Person("Bob", 11))

    // maxBy函数可以在任何集合上调用，参数是个函数，指定比较哪个值来找最大元素。函数中用it引用集合元素
    println(p.maxBy({ p: Person -> p.age }))
    // 简写1。若lambda表达式时最后一个参数，它可以放到括号外面
    println(p.maxBy() { p: Person -> p.age })
    // 简写2。若lambda表达式时唯一的参数，可以去掉调用它的括号
    println(p.maxBy { p: Person -> p.age })
    // 简写3。若lambda参数的类型可以被推导，则不需要显示指定参数类型（tip：可先不声明类型，编译器报错再指定）
    println(p.maxBy { p -> p.age })
    // 简写4。若上下文期望的是只有一个参数的lambda且类型可推断，会生成默认参数名称it
    println(p.maxBy { it.age })
    // 若用变量存储lambda，则没有推断出参数类型的上下文

    // 若lambda是函数或属性的委托（只是访问了函数或属性），则可用成员引用替换
    println(p.maxBy(Person::age))
    // 可以用库函数run来直接执行传给它的lambda
    run { println("lambda") }
}

fun printMessagesWithPrefix(messages: Collection<String>, prefix: String) {
    messages.forEach {
        // 与匿名内部类相同，函数中的lambda可以访问函数的参数和局部变量
        println("$prefix $it")
        // Java的lambda只能捕捉不可变变量final，而在Kotlin可以捕捉可变变量（原理是用包装类来存储变量）
    }
}


// 成员引用。用于创建一个函数值，可以访问单个成员
val getAge = Person::age

// 成员引用与lambda可以互换使用
val getAge1 = { person: Person -> person.age }

fun test() {
    // 引用顶层函数时，::前面不加类名，因为顶层函数不是类的成员
    // 即使引用的是方法，成员引用的后面也要不加括号
    run(::maxAge)
}

// 构造方法引用。用于存储或延期执行创建类实例的动作
val createPerson = ::Person
val p = createPerson("Alice", 29)

// 扩展函数引用。尽管扩展函数不是类的成员，但可以通过引用访问它
fun Person.isAdult() = age >= 18
val predicate = Person::isAdult

// predicate这个成员引用需要一个Person参数，调用代码：predicate(p)
// Kotlin1.1可以使用"绑定成员引用"，而不需要参数。因为指定了实例
val predicate1 = p.isAdult()