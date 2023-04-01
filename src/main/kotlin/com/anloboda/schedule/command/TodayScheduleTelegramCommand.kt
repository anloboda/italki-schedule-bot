package com.anloboda.schedule.command

import com.anloboda.schedule.atEndOfDay
import com.anloboda.schedule.formatForItalkiRequest
import com.anloboda.schedule.service.ScheduleService
import java.time.LocalDate

class TodayScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        val today = LocalDate.now()
        return scheduleService.get(
            startDate = today.atStartOfDay().formatForItalkiRequest(),
            endDate = today.atEndOfDay().formatForItalkiRequest()
        ).toTelegramString()
    }
}
