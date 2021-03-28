package com.houlin.learnkotlin.class_object_interface

import android.content.Context
import android.util.AttributeSet
import com.houlin.learnkotlin.MainActivity

/**
 * @author  Harry
 * @date  2021/3/9
 */
// 在类外声明的是主构造方法，类内声明的是从构造方法
// 主构造方法的作用：表明构造方法的参数、定义对应的属性

// constructor用于声明一个构造方法
open class User constructor(_nickname: String) {
    val nickname: String

    // init是初始化语句块。创建该类实例时执行，并与主构造方法一起使用，可以有多个
    // 因为主构造方法不能包含初始化代码，所以要使用init初始化
    init {
        nickname = _nickname
    }
}

// 主构造方法没有注解或可见性修饰符时，可以省略constructor关键字
open class User1(_nickname: String) {
    // 若用构造方法的参数直接给属性赋值，则可以在声明属性时用"="代替init初始化语句块
    val nickname: String = _nickname
}

// 在构造方法中用val/var直接声明了属性
open class User2(val nickname: String)

// User2、User1与User功能相同，只是构造方法的实现方式不同

// 有父类则还需要初始化父类，主构造方法可以为其提供参数
class TwitterUser(nickname: String) : User(nickname)

open class Father

// 父类没有构造方法时，也需要调用空的默认构造方法。与实现接口的区别，接口没有构造方法则不需要加括号
class Son : Father()

// 若要类不能在外部实例化，需要把构造方法都设为private
class Secretive private constructor()

// 在类内用constructor声明从构造方法
open class View {
    constructor(ctx: Context) {
        // some code
    }

    constructor(ctx: Context, attr: AttributeSet) {
        // some code
    }
}

class MyButton : View {
    // 构造方法都需要调用父类的构造方法。从构造方法也不例外
    constructor(ctx: Context) : super(ctx) {
        // some code
    }

    constructor(ctx: Context, attr: AttributeSet) : super(ctx, attr) {
        // some code
    }

    // 也可以使用this调用本类的其他构造方法，但也是在":"后面调用
    constructor() : this(MainActivity.instance) {
        // some code
    }
}

// Kotlin中接口可以声明抽象属性，但不会存储它
interface User01 {
    // 接口中的属性是抽象属性，实现接口必须override抽象属性
    val nickname: String

    // 接口中还可以声明具有getter和setter的属性，只要没有引用支持字段即可，子类无需override
    // （支持字段：需要存储状态的字段）
    val lastchar: String
        get() = nickname.substring(nickname.length - 1)
}

// 可以在主构造方法中override接口的属性
class PrivateUser(override val nickname: String) : User01

// 可以在类内override接口的属性
class SubscribingUser(val email: String) : User01 {
    // 并没有存储该属性，每次访问自定义getter时都要计算
    override val nickname: String
        get() = email.substringBefore('@')
}

class FacebookUser(val email: String) : User01 {
    // 对属性进行赋值，存储了该属性
    override val nickname: String = email.substringBefore('@')
}

class User02(val name: String) {
    // 在setter中用"field"访问支持字段，而非直接使用属性名访问
    var address: String = "unspecified"
        set(value: String) {
            println("""
                Address was changed for $name:
                "$field" -> "$value".""".trimIndent())
            field = value
        }
}

class LengthCounter {
    // 访问器的可见性默认与属性的可见性相同，可以用可见性修饰符修改访问器的可见性
    var counter: Int = 0
        // 修改默认访问器的可见性
        private set
}