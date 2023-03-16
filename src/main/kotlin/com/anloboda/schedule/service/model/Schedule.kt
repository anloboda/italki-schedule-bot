package com.anloboda.schedule.service.model

import com.anloboda.schedule.api.response.ItalkiLesson
import com.anloboda.schedule.toLessonLines
import com.anloboda.schedule.toSortedZonedLessons

const val GREEN_HEART_EMOJI = "\uD83D\uDC9A\uFE0F"
const val EUROPE_KYIV_TIMEZONE = "Europe/Kiev"

var MESSAGE_TEMPLATE = """
    Today's lessons schedule $GREEN_HEART_EMOJI (Ukrainian time) 
    %s
""".trimIndent()


data class Schedule(
    val zonedLessons: List<ZonedLesson>,
) {
    companion object {
        fun from(italkiLessons: List<ItalkiLesson>) =
            Schedule(italkiLessons.toSortedZonedLessons(zone = EUROPE_KYIV_TIMEZONE))
    }

    fun toTelegramString() = MESSAGE_TEMPLATE.format(
        zonedLessons.toLessonLines().joinToString(System.lineSeparator())
    )

}
