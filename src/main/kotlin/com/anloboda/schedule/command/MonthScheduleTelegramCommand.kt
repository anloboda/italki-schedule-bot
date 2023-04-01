package com.anloboda.schedule.command

import com.anloboda.schedule.atEndOfDay
import com.anloboda.schedule.formatForItalkiRequest
import com.anloboda.schedule.service.ScheduleService
import java.time.LocalDate
import java.time.YearMonth

class MonthScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        val today = LocalDate.now()
        val month = YearMonth.now()
        return scheduleService.get(
            startDate = today.atStartOfDay().formatForItalkiRequest(),
            endDate = month.atEndOfMonth().atEndOfDay().formatForItalkiRequest()
        ).toTelegramString()
    }
}