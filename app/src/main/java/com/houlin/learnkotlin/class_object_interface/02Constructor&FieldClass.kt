package com.houlin.learnkotlin.class_object_interface

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
    val nickname: String = _nickname
}

// 在构造方法中用val/var直接声明了属性
open class User2(val nickname: String)

// User2、User1与User功能相同，只是构造方法的实现方式不同

// 有父类则需要初始化父类，主构造方法为其提供参数
class TwitterUser(nickname: String) : User(nickname)