package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService
import java.time.DayOfWeek
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class WeekScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        val endOfWeek: LocalDateTime = LocalDateTime.now().with(DayOfWeek.SUNDAY)
        return scheduleService.get(
            startDate = buildDateTimeString(LocalDateTime.now(),0, 0, 0),
            endDate = buildDateTimeString(endOfWeek, 23, 59, 59)
        ).toTelegramString()
    }

    private fun buildDateTimeString(day: LocalDateTime, hour: Int, minute: Int, second: Int): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return day.withHour(hour).withMinute(minute).withSecond(second).format(formatter)
    }
}