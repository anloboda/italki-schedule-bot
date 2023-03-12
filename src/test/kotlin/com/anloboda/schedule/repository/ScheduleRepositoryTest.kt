package com.anloboda.schedule.repository

import com.anloboda.schedule.api.ScheduleApi
import com.anloboda.schedule.api.response.ItalkiScheduleData
import com.anloboda.schedule.api.response.ItalkiScheduleResponse
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class ScheduleRepositoryTest {

    private val scheduleApi = mockk<ScheduleApi>()
    private val scheduleRepository = ScheduleRepository(scheduleApi)

    @Test
    fun testGet() {
        //given
        val id = 1
        val startTime = "startTime"
        val endTime = "endTime"

        val italkiScheduleResponse = ItalkiScheduleResponse(schedule = ItalkiScheduleData(lessons = emptyList()))
        every { scheduleApi.get(id, startTime, endTime) } returns italkiScheduleResponse

        //when
        val response = scheduleRepository.get(id, startTime, endTime)

        //then
        verify(exactly = 1) { scheduleApi.get(id, startTime, endTime) }
        assertEquals(response, italkiScheduleResponse)
    }
}