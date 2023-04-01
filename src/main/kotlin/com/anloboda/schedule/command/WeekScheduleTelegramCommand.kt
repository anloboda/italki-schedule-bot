package com.anloboda.schedule.command

import com.anloboda.schedule.atEndOfDay
import com.anloboda.schedule.formatForItalkiRequest
import com.anloboda.schedule.service.ScheduleService
import java.time.DayOfWeek.SUNDAY
import java.time.LocalDate

class WeekScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        val today = LocalDate.now()
        val sunday = today.with(SUNDAY)
        return scheduleService.get(
            startDate = today.atStartOfDay().formatForItalkiRequest(),
            endDate = sunday.atEndOfDay().formatForItalkiRequest()
        ).toTelegramString()
    }
}