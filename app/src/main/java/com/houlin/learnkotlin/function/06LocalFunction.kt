package com.houlin.learnkotlin.function

import java.lang.IllegalArgumentException

/**
 * @author  Harry
 * @date  2021/3/1
 */
class User(val id: Int, val name: String, val address: String)

// 局部函数。Kotlin可以在函数中嵌套函数，来解决代码重复问题
fun saveUser(user: User) {

    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            // 局部函数可以访问所在函数中的所有参数和变量。user.id
            throw IllegalArgumentException("Can't save user ${user.id}: empty $fieldName")
        }
    }
    // 调用局部函数
    validate(user.name, "Name")
    validate(user.address, "Address")
    // 保存user到数据库
}

// 优化上面的操作
fun User.validateBeforeSave() {

    fun validate(value: String, fieldName: String) {
        if (value.isEmpty()) {
            // 作为扩展函数中的局部函数，一样可以访问对象的属性。id
            throw IllegalArgumentException("Can't save user $id: empty $fieldName")
        }
    }
    validate(name, "Name")
    validate(address, "Address")
}

fun saveUser1(user: User) {
    user.validateBeforeSave()
    // 保存user到数据库
}
// 扩展函数也可以被声明为局部函数，但影响可读性
