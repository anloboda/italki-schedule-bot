package com.anloboda.schedule.service.model

import okhttp3.internal.format
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

private const val DIAMOND_EMOJI = "\uD83D\uDD39"

data class ZonedLesson(
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime

){
    override fun toString(): String {
        val timePattern = DateTimeFormatter.ofPattern("HH:mm")
        return format("$DIAMOND_EMOJI ${startTime.format(timePattern)} - ${endTime.format(timePattern)}")
    }
}