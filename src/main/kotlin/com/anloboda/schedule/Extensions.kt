package com.anloboda.schedule

import com.anloboda.schedule.api.response.ItalkiLesson
import com.anloboda.schedule.service.model.ZonedLesson
import okhttp3.internal.format
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter


fun List<ItalkiLesson>.toSortedZonedLessons(zone: String): List<ZonedLesson> = this.map() {
    ZonedLesson(
        startTime = getZonedDateTime(it.startTime, zone), endTime = getZonedDateTime(it.endTime, zone)
    )
}.sortedBy { it.startTime }

private fun getZonedDateTime(dateTime: String, zone: String): ZonedDateTime {
    val originalDateTime = ZonedDateTime.parse(dateTime)
    val ukraineTimeZone = ZoneId.of(zone)
    return originalDateTime.withZoneSameInstant(ukraineTimeZone)
}
