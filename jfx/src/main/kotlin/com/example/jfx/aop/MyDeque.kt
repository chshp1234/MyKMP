package com.example.jfx.aop

import java.util.concurrent.LinkedBlockingDeque

//自定义队列，重写addLast方法，插入自定义的Runnable包装器，用于hook操作
class MyDeque : LinkedBlockingDeque<Runnable>() {
    override fun addLast(e: Runnable) {
        super.addLast(MyRunnableWrap(e))
    }
}