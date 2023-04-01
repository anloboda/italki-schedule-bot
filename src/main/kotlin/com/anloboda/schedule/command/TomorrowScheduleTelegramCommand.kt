package com.anloboda.schedule.command

import com.anloboda.schedule.atEndOfDay
import com.anloboda.schedule.formatForItalkiRequest
import com.anloboda.schedule.service.ScheduleService
import java.time.LocalDate

class TomorrowScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {

    override fun execute(): String {
        val tomorrow = LocalDate.now().plusDays(1)
        return scheduleService.get(
            startDate = tomorrow.atStartOfDay().formatForItalkiRequest(),
            endDate = tomorrow.atEndOfDay().formatForItalkiRequest()
        ).toTelegramString()
    }
}
