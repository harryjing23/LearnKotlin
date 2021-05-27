package com.houlin.learnkotlin.class_object_interface

import android.view.View
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
    // 伴生对象因为是个对象，所以还可以实现接口。且用外部类类名即可代表该接口
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

// ### Kotlin中没有静态成员，可以用伴生对象和顶层函数、属性来替代

// 伴生对象扩展：
// 利用空的伴生对象对类进行扩展，使类结构更干净
class Person1(val firstName: String, val lastName: String) {

    companion object {
    }
}

// 可以在类外为伴生对象编写扩展函数。伴生对象的名称不可省略
fun Person1.Companion.newUser(): Person1 {
    return Person1("new", "new")
}

// 调用扩展函数就像调用伴生对象的函数一样方便
val p = Person1.newUser()

// 对象表达式：代替Java中的匿名内部类。但可以实现多个接口或不实现接口，而Java匿名内部类只能扩展一个类或实现一个接口
fun testClick(view: View) {
    var clickCount = 0

    // 语法与对象声明一样，但去掉了对象的名字
    // 与对象声明不同，对象表达式不是单例
    view.setOnClickListener(object : View.OnClickListener {
        override fun onClick(v: View?) {
            // 也可以像Java一样访问当前函数中的变量，还不要求变量是final（Java要求是final）
            clickCount++
        }
    })
}