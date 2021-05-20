package com.houlin.learnkotlin.class_object_interface

import java.io.Serializable


/**
 * 4.4 object关键字：将声明一个类与创建一个实例结合起来
 * @author  Harry
 * @date  2021/5/19
 */

// 对象声明与类的唯一区别：不能有构造方法
// 对象声明在Java中就是单例：用static字段INSTANCE来持有实例
// 所以Java访问实例时，直接访问INSTANCE字段即可
data class Person(val name: String) {

    // object对象声明也可以在类或对象声明中嵌套使用，但无论如何都是单例
    object NameComparator : Comparator<Person> {
        override fun compare(o1: Person, o2: Person): Int = o1.name.compareTo(o2.name)
    }
}

fun test() {
    // 直接访问对象声明的类名就是访问该类的单例对象
    val nameComparator = Person.NameComparator

    // Java中companion object是外部类的一个static字段（默认名为Companion），直接访问即可
    val user1 = User3.Factory.newSubscribingUser("22@163.com")
    // Kotlin中可以省略伴生对象的名称，直接用外部类的类名访问
    val user2 = User3.newFacebookUser(111)

}

// 主构造方法设为private，用伴生对象实现工厂模式
class User3 private constructor(val nickname: String) {
    private val age: Int = 10

    // 伴生对象companion object：类内部的对象声明可以用companion修饰，可以让外部访问类内的private成员
    // 伴生对象的名称可以指定，也可以省略（默认为Companion）
    // 伴生对象因为是个对象，所以还可以实现接口
    companion object Factory : Serializable {
        // 访问private方法
        fun newSubscribingUser(email: String) =
            User3(email.substringBefore('@'))

        fun newFacebookUser(accountId: Int) =
            User3("facebook$accountId")

        // 访问private字段
        fun getAge(user: User3): Int = user.age
    }
}

// ### Kotlin中没有静态成员，可以用object和companion object替代

