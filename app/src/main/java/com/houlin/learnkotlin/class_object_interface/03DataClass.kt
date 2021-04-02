package com.houlin.learnkotlin.class_object_interface

/**
 * 4.3 编译器生成的方法：数据类和类委托
 * @author  Harry
 * @date  2021/3/29
 */

class Client(val name: String) {
    override fun toString(): String {
        return "Client(name='$name')"
    }

    // Java中，"=="在基本数据类型上比较的是值，在引用类型上比较的是是否为同一个引用
    // Java中，经常重写equals用于比较对象的属性。Object的equals默认实现为"=="
    // Kotlin中，"=="调用的是equals，比较引用要用"==="

    // Any是Kotlin中所有类的父类，是Java中Object的模拟
    override fun equals(other: Any?): Boolean {
        // is是Java中instanceof的模拟。与"in"一样，前面加"!"为非运算
        if (other == null || other !is Client)
            return false
        return name == other.name// Kotlin中用"=="替代equals
    }

    // ### hashCode()通常与equals()一起重写，hashCode用于哈希容器的查找
    // 因为若两个对象相同则hashCode也相同，即equals为true时hashCode也应该相同

    override fun hashCode(): Int = name.hashCode()

    fun copy(name: String = this.name) {
        Client(name)
    }
}

// ### Kotlin中override是强制的
// Java中重写可以省略@Override，父类方法加@CallSuper则子类重写的方法中必须调用父类的方法

// Kotlin中根据data数据类的主构造方法中的所有属性自动生成toString、equals和hashCode
// ### 没有在主构造方法声明的属性不参与其中
data class Client01(val name: String)

// 数据类的属性一般为是val（不可变对象），所以Kotlin还生成了copy()，并在copy同时可修改指定属性的值
fun testCopy() {
    val client01 = Client01("Tom")
    // copy可以不修改属性
    val copy = client01.copy()
    // copy也可以使用命名参数修改属性
    val copy1 = client01.copy(name = "Tommy")
}

// 类委托by，将接口的实现委托到另一个对象。自动生成接口的所有方法去调用另一个对象的对应方法
// 类似于用装饰器模式来扩展某个类，但是减少了样板代码
class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {
    var objectsAdded = 0

    // 重写要修改的方法，外部调用时就不再走自动生成的方法了
    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }
}
