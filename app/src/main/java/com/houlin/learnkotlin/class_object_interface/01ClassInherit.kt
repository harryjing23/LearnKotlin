package com.houlin.learnkotlin.class_object_interface

/**
 * @author  Harry
 * @date  2021/3/4
 */
// Kotlin中对类、接口和函数的声明默认是final和public，嵌套的类默认不是内部类
// Kotlin用data声明一个数据类，会生成若干方法（以后再研究），方便数据类的操作
interface Clickable {
    fun click()

    // 与Java8的默认方法类似，可以提供方法的默认实现。但Java8需要default修饰，Kotlin则不需要
    fun showOff() = println("I'm clickable")
}

// Kotlin用":"代替Java的extends和implements
class Button : Clickable {
    // override与Java的@override相同，但Kotlin强制标注在被重写的函数和属性
    override fun click() = println("I was clicked")
    // 接口中有默认实现的函数，可以不实现
    // 但一个类实现的多个接口中，都有相同签名的函数且都有默认实现，则必须实现该函数
}
// 注意：在Java中实现有方法体的Kotlin接口时，必须要实现所有方法，包括有方法体的方法

// 使用open修饰符来允许类可以被继承
open class RichButton : Clickable {
    val a = 10

    // 使用open修饰符来允许函数可以被重写
    open fun animate() {}

    // override了基类或接口的成员，则该成员也是open的。可以用final阻止被重写
    final override fun click() {}
}

// 与Java相同，抽象类不能被实例化
// 抽象类中的抽象成员始终是open的，不需要显式使用open。普通成员默认仍然是final
abstract class Animated {
    abstract fun animate()
}

// Kotlin可见性修饰符有public、internal、protected、private。没有Java默认的package-private
// public是默认的，internal是只在模块内部可见
// 禁止用高可见性引用低可见性。eg. public的扩展函数引用了internal的对象

// Java中protected成员可以在同一个包中访问或子类中访问，而Kotlin只允许在子类中访问
// Kotlin的private类在Java中会变成package-private（默认可见性）类，因为Java类（内部类除外）不能是private
// Kotlin的internal修饰符在Java中变成public，但internal成员名会被破坏，从而避免以外使用
// Kotlin的外部类不能访问其内部类的private成员，而Java可以

// Java中内部类会持有外部类的实例，可访问外部类的成员（static内部类不会）
// ### Kotlin的内部类与Java的static内部类类似，要持有外部类的引用需要用inner修饰符（相当于Java的内部类）

// 引用外部类实例的语法this@Outer。Java中用外部类类名.this，eg. User.this
class Outer {
    inner class Inner {
        fun getOuterReference(): Outer = this@Outer
    }
}

// 密封类。用sealed修饰，不可实例化（只有一个private构造函数）
// 子类必须嵌套在父类中，子类不能是data类。Kotlin 1.1可以将子类放在父类的同一文件中
// when结构用作表达式（有返回值）时，必须要有else分支。sealed类作为条件时除外，但when条件必须遍历所有子类
sealed class Expr {
    class Num(val value: Expr) : Expr()
    class Sum(val left: Expr, val right: Expr) : Expr()
}