package com.anloboda.schedule.service

import com.anloboda.schedule.repository.ScheduleRepository
import com.anloboda.schedule.service.model.Schedule
import org.springframework.stereotype.Service

@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository
) {
    fun getSchedule(startDate: String, endDate: String): Schedule {
        val response = scheduleRepository.get(startDate, endDate)
        return Schedule.from(response.schedule.lessons)
    }
}