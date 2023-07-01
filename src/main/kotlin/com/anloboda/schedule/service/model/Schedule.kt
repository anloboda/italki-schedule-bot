package com.anloboda.schedule.service.model

import com.anloboda.schedule.api.response.ItalkiLesson
import java.time.ZoneId
import java.time.ZonedDateTime

const val EUROPE_KYIV_TIMEZONE = "Europe/Kiev"

data class Schedule(
    val zonedLessons: List<ZonedLesson>
) {
    companion object {
        fun from(italkiLessons: List<ItalkiLesson>) =
            Schedule(italkiLessons.toSortedZonedLessons(zone = EUROPE_KYIV_TIMEZONE))
    }

    fun toTelegramString() =
        when {
            zonedLessons.isEmpty() -> "No lessons :("
            else -> buildTelegramString()
        }

    private fun buildTelegramString(): String {
        val scheduleBuilder = StringBuilder()
        for ((dayHeader, lessons) in groupLessonsByDate()) {
            // append header like: MARCH 31 (FRIDAY)
            scheduleBuilder.appendLine(dayHeader)

            // build lessons block
            for (lesson in lessons) {
                scheduleBuilder.appendLine(lesson.toString())
            }

            scheduleBuilder.appendLine()
        }

        return scheduleBuilder.toString()
    }

    private fun groupLessonsByDate() = zonedLessons.groupBy { lesson ->
        val month = lesson.startTime.month
        val dayOfMonth = lesson.startTime.dayOfMonth
        val dayOfWeek = lesson.startTime.dayOfWeek
        "$month $dayOfMonth ($dayOfWeek)"
    }
}

fun List<ItalkiLesson>.toSortedZonedLessons(zone: String): List<ZonedLesson> = this.map() {
    ZonedLesson(
        startTime = getZonedDateTime(it.startTime, zone),
        endTime = getZonedDateTime(it.endTime, zone)
    )
}.sortedBy { it.startTime }

private fun getZonedDateTime(dateTime: String, zone: String): ZonedDateTime {
    val originalDateTime = ZonedDateTime.parse(dateTime)
    val ukraineTimeZone = ZoneId.of(zone)
    return originalDateTime.withZoneSameInstant(ukraineTimeZone)
}