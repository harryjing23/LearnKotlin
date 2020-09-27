package com.houlin.learnkotlin.basic

// import的"*"代表类中的所有属性和方法
import com.houlin.learnkotlin.basic.Color.*

/**
 * @author Harry Jing
 * @date 2020/9/22
 */

// 枚举并不是值的列表，可以给枚举类声明属性和方法
enum class Color(val r: Int, val g: Int, val b: Int) {
    // 因为定义了构造函数，所以枚举值都要提供属性
    RED(255, 0, 0),
    ORANGE(255, 165, 0),
    YELLOW(255, 255, 0),
    GREEN(0, 255, 0),
    BLUE(0, 0, 255),
    INDIGO(75, 0, 130),
    VIOLET(238, 130, 238);// 枚举类中要用";"将枚举列表与方法隔开

    fun rgb() = (r * 256 + g) * 256 + b
}

// when语句。无需break，多个条件用","分隔，每个分支若有多行代码用{}括成代码块
fun getWarmth(color: Color) =
        when (color) {
            Color.RED, Color.ORANGE, Color.YELLOW -> "warm"
            Color.GREEN -> "neutral"
            Color.BLUE, Color.INDIGO, Color.VIOLET -> "cold"
        }// 表达式体函数

fun mix(c1: Color, c2: Color) =
        // when可以用任意表达式作条件（Java的switch只能用枚举常量、字符串、数字做条件）
        when (setOf(c1, c2)) {
            // setOf(param...)是将所有实参都放进一个Set中（Set集合是无序不重复的），两个Set的条目相同即相等
            setOf(RED, YELLOW) -> Color.ORANGE
            setOf(YELLOW, BLUE) -> GREEN
            setOf(BLUE, Color.VIOLET) -> Color.INDIGO
            else -> throw Exception("Dirty Color")// 用else，而不是default
        }

// 与mix函数相比，不需要创建多个Set
fun mixOptimized(c1: Color, c2: Color) =
        // when可以无参数，直接用布尔表达式作条件
        when {
            (c1 == RED && c2 == YELLOW) || (c1 == YELLOW && c2 == RED) ->
                Color.ORANGE
            (c1 == YELLOW && c2 == BLUE) || (c1 == BLUE && c2 == YELLOW) ->
                GREEN
            (c1 == BLUE && c2 == Color.VIOLET) || (c1 == Color.VIOLET && c2 == BLUE) ->
                Color.INDIGO
            else -> throw Exception("Dirty Color")
        }

// 对两个数求和的树状结构，Sum节点会有两个叶子节点Num
// 接口的实现。类的声明后面加": 接口"
interface Expr
class Num(val value: Int) : Expr
class Sum(val left: Expr, val right: Expr) : Expr

fun eval(e: Expr): Int {
    // 检查对象是否是某种类型（类似Java的instanceof）。is
    if (e is Num) {
        // 显式/强制类型转换。as
        val n = e as Num
        return n.value
    }
    if (e is Sum) {
        return eval(e.left) + eval(e.right)
    }
    throw IllegalArgumentException("Unknown expression")
}

// 智能转换。检查过一个变量是某种类型，就可以把它当作该类型使用，无需再对其强制类型转换
fun evalOptimized(e: Expr): Int =
        if (e is Num)
            e.value
        else if (e is Sum)
            evalOptimized(e.left) + evalOptimized(e.right)
        else
            throw IllegalArgumentException("Unknown expression")

// 用when代替连串的if。因为when和if都是有结果值的表达式
// 若if和when的分支体是代码块，"代码块中最后的表达式会被作为结果返回"（通用规则）
// when的条件不仅限于检查值是否相等，还可以检查实参的类型
fun evalWhen(e: Expr): Int =
        when (e) {
            is Num -> {
                println("num: ${e.value}")
                e.value
            }
            is Sum -> {
                val left = evalWhen(e.left)
                val right = evalWhen(e.right)
                println("sum: $left + $right")
                left + right
            }
            else -> throw IllegalArgumentException("Unknown expression")
        }
