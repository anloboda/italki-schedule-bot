package com.anloboda.schedule

import com.anloboda.schedule.api.response.ItalkiLesson
import com.anloboda.schedule.service.model.EUROPE_KYIV_TIMEZONE
import com.anloboda.schedule.service.model.ZonedLesson
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime

class ExtensionsTest {

    @Test
    fun testToSortedZonedLessons() {
        //given
        val italkiLessons = listOf(
            ItalkiLesson(startTime = "2023-03-13T15:00:00+00:00", "2023-03-13T16:00:00+00:00"),
            ItalkiLesson(startTime = "2023-03-13T09:30:00+00:00", "2023-03-13T10:30:00+00:00"),
            ItalkiLesson(startTime = "2023-03-13T11:30:00+00:00", "2023-03-13T12:30:00+00:00"),
        )

        //when
        val sortedZonedLessons = italkiLessons.toSortedZonedLessons(EUROPE_KYIV_TIMEZONE)

        //then
        with(sortedZonedLessons) {
            assertEquals(3, size)
            with(get(0)) {
                assertEquals(11, startTime.hour)
                assertEquals(30, startTime.minute)
                assertEquals(12, endTime.hour)
                assertEquals(30, endTime.minute)
            }
            with(get(1)) {
                assertEquals(13, startTime.hour)
                assertEquals(30, startTime.minute)
                assertEquals(14, endTime.hour)
                assertEquals(30, endTime.minute)
            }
            with(get(2)) {
                assertEquals(17, startTime.hour)
                assertEquals(0, startTime.minute)
                assertEquals(18, endTime.hour)
                assertEquals(0, endTime.minute)
            }
        }
    }

    @Test
    fun testToLessonLines() {
        //given
        val zonedLessons = listOf(
            ZonedLesson(
                startTime = ZonedDateTime.parse("2023-02-12T11:30+02:00[Europe/Kiev]"),
                endTime = ZonedDateTime.parse("2023-02-12T12:30+02:00[Europe/Kiev]")
            ),
            ZonedLesson(
                startTime = ZonedDateTime.parse("2023-02-12T14:30+02:00[Europe/Kiev]"),
                endTime = ZonedDateTime.parse("2023-02-12T15:30+02:00[Europe/Kiev]")
            )
        )

        //when
        val lessonLines = zonedLessons.toLessonLines()

        //then

        with(lessonLines) {
            assertEquals(2, size)
            assertEquals("\uD83D\uDD39 11:30 - 12:30", get(0))
            assertEquals("\uD83D\uDD39 14:30 - 15:30", get(1))
        }
    }

}