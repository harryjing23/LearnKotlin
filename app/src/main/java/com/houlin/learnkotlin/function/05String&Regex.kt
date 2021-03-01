package com.houlin.learnkotlin.function

/**
 * @author  Harry
 * @date  2021/3/1
 */
// Java中String的split函数不适用于"."，应使用"\\."。
// 因为split()将正则表达式作为参数，"."是表示任何字符的正则表达式
// Kotlin中的split函数可将字符串字面量作为参数，若使用正则表达式需要传入Regex类型
fun s1() {
    // 使用正则表达式分割字符串
    val split = "12.345-6.A".split("\\.|-".toRegex())
    // 使用字面量分割字符串，不需要转义
    val split1 = "12.345-6.A".split(".", "-")
}

// Kotlin对分割字符串的API扩展
fun parsePath() {
    val path = "/Users/yole/kotlin-book/chapter.adoc"
    val directory = path.substringBeforeLast("/")// "/Users/yole/kotlin-book"
    val fullName = path.substringAfterLast("/")// "chapter.adoc"

    val fileName = fullName.substringBeforeLast(".")// "chapter"
    val extension = fullName.substringAfterLast(".")// "adoc"
}

// 使用正则和三重引号的字符串
fun parsePath1() {
    val path = "/Users/yole/kotlin-book/chapter.adoc"
    // 正则表达式写在三重引号的字符串中，不需要对字符进行转义，包括反斜杠。即可以用"\."而不是"\\."来表示点
    val regex = """(.+)/(.+)\.(.+)""".toRegex()
    val matchResult = regex.matchEntire(path)
    if (matchResult != null) {// 匹配成功
        // 将MatchResult的属性解构声明为多个变量
        val (directory, fileName, extension) = matchResult.destructured
    }
}

fun triple() {
    // 三重引号的字符串（多行字符串）包含任何字符，包括换行符和格式化代码的缩进
    // 使用trimMargin删除每行指定的前缀和空格
    val kotlinLogo = """| //
                       .|//
                       .|/ \""".trimMargin(".")
    println(kotlinLogo)
    // 可以在三重引号字符串中使用字符串模板$，但是需要用嵌入式表达式表示字面量"$"，即${'$'}。因为不支持转义
    val price = """${'$'} 99.9"""
}