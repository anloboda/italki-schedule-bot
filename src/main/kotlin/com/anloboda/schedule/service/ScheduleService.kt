package com.anloboda.schedule.service

import com.anloboda.schedule.repository.ScheduleRepository
import com.anloboda.schedule.service.model.Schedule
import org.springframework.stereotype.Service


@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository
) {
    //2023-03-11T00:00:00Z
    fun get(id: Int, startDate: String, endDate: String): Schedule {
        val response = scheduleRepository.get(id, startDate, endDate)
        return Schedule.from(response.schedule.lessons)
    }
}
