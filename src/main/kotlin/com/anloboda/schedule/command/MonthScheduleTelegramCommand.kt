package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService
import com.anloboda.schedule.service.model.EUROPE_KYIV_TIMEZONE
import java.time.LocalDateTime
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter

class MonthScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        val endOfMonth: LocalDateTime = YearMonth.now().atEndOfMonth().atTime(23, 59, 59)
            .atZone(ZoneId.of(EUROPE_KYIV_TIMEZONE)).toLocalDateTime()

        return scheduleService.get(
            startDate = buildDateTimeString(LocalDateTime.now(),0, 0, 0),
            endDate = buildDateTimeString(endOfMonth, 23, 59, 59)
        ).toTelegramString()
    }

    private fun buildDateTimeString(day: LocalDateTime, hour: Int, minute: Int, second: Int): String {
        val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss'Z'")
        return day.withHour(hour).withMinute(minute).withSecond(second).format(formatter)
    }
}