package com.anloboda.schedule

import java.util.concurrent.Semaphore

fun main() {

    val semaphore = Semaphore(1)
    val thread1 = Thread(MyRunnable("1", semaphore))
    val thread2 = Thread(MyRunnable("2", semaphore))

    thread1.start()
    thread2.start()
}

class MyRunnable(private val id: String, private val semaphore: Semaphore): Runnable{
    override fun run() {
        semaphore.acquire()

        println("Working $id")
        Thread.sleep(5000)

        semaphore.release()
    }
}