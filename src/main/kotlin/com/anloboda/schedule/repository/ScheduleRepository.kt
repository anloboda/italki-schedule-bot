package com.anloboda.schedule.repository

import com.anloboda.schedule.api.ScheduleApi
import com.anloboda.schedule.api.response.ItalkiScheduleResponse
import org.springframework.stereotype.Repository

@Repository
class ScheduleRepository(
    private val scheduleApi: ScheduleApi
) {
    fun get(id: Int, startDate: String, endDate: String): ItalkiScheduleResponse =
        scheduleApi.get(id, startDate, endDate)
}
