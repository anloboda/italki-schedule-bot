package com.anloboda.schedule.service

import com.anloboda.schedule.repository.ScheduleRepository
import com.anloboda.schedule.service.model.Schedule
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter


@Service
class ScheduleService(
    private val scheduleRepository: ScheduleRepository
) {
    fun get(id: Int): Schedule {
        val response = scheduleRepository.get(
            id = id,
            startDate = buildDateTimeString(0, 0, 0),
            endDate = buildDateTimeString(23, 59, 59)
        )
        return Schedule.from(response.schedule.lessons)
    }

    private fun buildDateTimeString(hour: Int, minute: Int, second: Int): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return currentDateTime
            .withHour(hour)
            .withMinute(minute)
            .withSecond(second)
            .format(formatter)
    }
}
