package com.anloboda.schedule

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class ScheduleApplication

fun main(args: Array<String>) {
	runApplication<ScheduleApplication>(*args)
}
