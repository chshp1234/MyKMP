package com.kmm.shared

//包装一层,方便调用
object CommonApple {
    fun greetingApple() = iGreetingApple()
}

//iOS中调用时,得看具体实现的文件的文件名(不能,不一定是CommonApple)
expect fun iGreetingApple(): String
