package com.anloboda.schedule.repository

import com.anloboda.schedule.api.ScheduleApi
import com.anloboda.schedule.api.response.ItalkiScheduleResponse
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository

@Repository
class ScheduleRepository(
    @Value("\${italki.teacher.id}") private val teacherId: Int,
    private val scheduleApi: ScheduleApi
) {
    fun get(startTime: String, endTime: String): ItalkiScheduleResponse =
        scheduleApi.get(teacherId, startTime, endTime)
}