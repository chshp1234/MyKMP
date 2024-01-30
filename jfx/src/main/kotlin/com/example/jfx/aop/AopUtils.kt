package com.example.jfx.aop

import com.sun.glass.ui.Application

//必须在inject之后使用
lateinit var fxUserThread: Thread

fun inject() {
    //各个平台Application抽象类
    //其中Mac平台实现类为MacApplication；win平台实现类为WinApplication。此外还有iOS平台，SWT平台等。
    val appClass = Application::class.java
    //Application抽象类中有个application的静态实例（），代表当前的全局Application
    val macAppField = appClass.getDeclaredField("application")
    macAppField.isAccessible = true
    //获取静态application实例
    val application = macAppField.get(null)

    //InvokeLaterDispatcher是用于将延迟的可运行对象逐个提交到本机系统调度程序
    //在Application中有个invokeLaterDispatcher实例对象
    val invokeLaterDispatcherField = application.javaClass.getDeclaredField("invokeLaterDispatcher")
    invokeLaterDispatcherField.isAccessible = true
    val invokeLaterDispatcher = invokeLaterDispatcherField.get(application)

    //InvokeLaterDispatcher调度器中执行的消息队列
    //即所有将要在主线程（JavaFxThread）中执行的任务队列
    val dequeField = invokeLaterDispatcher.javaClass.getDeclaredField("deque")
    dequeField.isAccessible = true
//    val deque = dequeField.get(invokeLaterDispatcher) as LinkedBlockingDeque<Runnable>
    //自定义队列，用于hook操作。并替换系统中的消息队列实例。
    val myDeque = MyDeque()
    dequeField.set(invokeLaterDispatcher, myDeque)

    fxUserThread = appClass.getDeclaredField("eventThread").run {
        isAccessible = true
        get(null) as Thread
    }
}

fun <R> userFxThread(block: Thread.() -> R): R {
    return fxUserThread.run {
        block()
    }
}

fun getFxThreadStackString(vararg msg: String): String {
    return userFxThread {
        val stackTrace = stackTrace
        StringBuilder().run {
            for (m in msg) {
                append(m).append("\n")
            }

            append("thread state = ").append(state).append("\n")
            append("stack size = ${stackTrace.size}").append("\n")
            for (st in stackTrace) {
                append(st.className + ":" + st.methodName + "(${st.lineNumber})\n")
            }
            append("\n")

            toString()
        }
    }
}