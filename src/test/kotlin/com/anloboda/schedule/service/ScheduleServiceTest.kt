package com.anloboda.schedule.service

import com.anloboda.schedule.api.response.ItalkiLesson
import com.anloboda.schedule.api.response.ItalkiScheduleData
import com.anloboda.schedule.api.response.ItalkiScheduleResponse
import com.anloboda.schedule.repository.ScheduleRepository
import com.anloboda.schedule.service.model.Schedule
import com.anloboda.schedule.service.model.ZonedLesson
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.ZonedDateTime


class ScheduleServiceTest {

    private val scheduleRepository = mockk<ScheduleRepository>()
    private val scheduleService = ScheduleService(scheduleRepository)

    @Test
    fun testGet() {
        //given
        val italkiScheduleResponse = ItalkiScheduleResponse(
            schedule = ItalkiScheduleData(
                lessons = listOf(
                    ItalkiLesson(
                        startTime = "2023-02-12T09:30:00+00:00", endTime = "2023-02-12T10:30:00+00:00"
                    )
                )
            )
        )

        every { scheduleRepository.get(any(), any()) } returns italkiScheduleResponse

        val expectedSchedule = Schedule(
            zonedLessons = listOf(
                ZonedLesson(
                    startTime = ZonedDateTime.parse("2023-02-12T11:30+02:00[Europe/Kiev]"),
                    endTime = ZonedDateTime.parse("2023-02-12T12:30+02:00[Europe/Kiev]")
                )
            )
        )

        //when
        val schedule = scheduleService.get("2023-02-12T00:00:00Z", "2023-02-12T23:59:59Z")

        //then
        verify(exactly = 1) { scheduleRepository.get("2023-02-12T00:00:00Z", "2023-02-12T23:59:59Z") }
        assertEquals(schedule.zonedLessons, expectedSchedule.zonedLessons)
    }
}