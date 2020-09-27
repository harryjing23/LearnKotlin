package com.houlin.learnkotlin.basic

import java.io.BufferedReader

/**
 * @author Harry Jing
 * @date 2020/9/25
 */

// 函数可以抛出异常。Java处理方式：1.可以捕获异常try{}catch{} 2.向上再次抛出异常throws
// Java必须处理受检异常，而Kotlin不分受检和不受检异常，所有异常都可以处理或不处理，且不需要向上抛出


// Kotlin中throw是表达式
fun checkPercentage(percentage: Int): Int =
        if (percentage !in 0..100)
        // 虽然返回类型是Int，但是throw异常也可以
            throw IllegalArgumentException("percentage must be between 0 and 100: $percentage")
        else
            percentage

// try也是表达式（有返回值）
fun inputParseInt(reader: BufferedReader) {
    val value =
            try {
                val line = reader.readLine()// readLine会抛出受检异常IOException
                Integer.parseInt(line)// parseInt会抛出未受检异常NumberFormatException
            } catch (e: NumberFormatException) {
                null
                // 也可以在catch中直接return整个函数
            } finally {
                reader.close()// close会抛出受检异常IOException
            }
    println(value)
}