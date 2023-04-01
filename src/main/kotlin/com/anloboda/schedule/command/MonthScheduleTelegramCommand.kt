package com.anloboda.schedule.command

import com.anloboda.schedule.atEndOfDay
import com.anloboda.schedule.formatForItalkiRequest
import com.anloboda.schedule.service.ScheduleService
import java.time.LocalDate
import java.time.YearMonth

class MonthScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        return scheduleService.get(
            startDate = LocalDate.now().atStartOfDay().formatForItalkiRequest(),
            endDate = YearMonth.now().atEndOfMonth().atEndOfDay().formatForItalkiRequest()
        ).toTelegramString()
    }
}