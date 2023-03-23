package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class TodayScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute() = scheduleService.get(
        id = 5191769,
        startDate = buildDateTimeString(0, 0, 0),
        endDate = buildDateTimeString(23, 59, 59)
    ).toTelegramString()

    private fun buildDateTimeString(hour: Int, minute: Int, second: Int): String {
        val currentDateTime = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return currentDateTime.withHour(hour).withMinute(minute).withSecond(second).format(formatter)
    }
}