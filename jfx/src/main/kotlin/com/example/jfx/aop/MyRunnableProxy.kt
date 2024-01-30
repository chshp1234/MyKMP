package com.example.jfx.aop

import java.lang.reflect.Field

class MyRunnableProxy(private val origin: Runnable) : Runnable {
    private val TAG = "MyRunnableProxy"

    private companion object {
        var arg1: Field? = null
    }

    override fun run() {
        val start = System.currentTimeMillis()
        origin.run()
        println("after run,cost=${System.currentTimeMillis() - start}ms")
    }

    private fun getOriginName() = try {
        (arg1 ?: origin.javaClass.getDeclaredField("arg$1").also {
            it.isAccessible = true
            arg1 = it
        }).get(origin).toString()
    } catch (_: Exception) {
        null
    }
}