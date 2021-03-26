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