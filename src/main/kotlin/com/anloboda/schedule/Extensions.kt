package com.anloboda.schedule

import java.time.LocalDate
import java.time.LocalDateTime
import java.time.LocalTime
import java.time.format.DateTimeFormatter

private val formatter: DateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")

fun LocalDate.atEndOfDay(): LocalDateTime = this.atTime(LocalTime.MAX)

fun LocalDateTime.formatForItalkiRequest(): String = this.format(formatter)