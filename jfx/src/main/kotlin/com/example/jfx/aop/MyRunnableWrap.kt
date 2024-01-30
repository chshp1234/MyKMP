package com.example.jfx.aop

class MyRunnableWrap(private val origin: Runnable) : Runnable {
    private val TAG = "MyRunnableWrap"

    override fun run() {
        val start = System.currentTimeMillis()
        origin.run()
        println("after run,cost=${System.currentTimeMillis() - start}ms")
    }
}