package com.anloboda.schedule

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
@EnableEncryptableProperties
class ScheduleApplication

fun main(args: Array<String>) {
	runApplication<ScheduleApplication>(*args)
}
