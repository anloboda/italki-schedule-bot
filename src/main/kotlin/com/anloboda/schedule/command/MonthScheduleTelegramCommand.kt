package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService

class MonthScheduleTelegramCommand(private val scheduleService: ScheduleService) : TelegramCommand {
    override fun execute(): String = scheduleService.get(
        5191769, startDate ="", endDate = ""
    ).toTelegramString()
}