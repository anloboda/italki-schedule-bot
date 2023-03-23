package com.anloboda.schedule.command.executor

import com.anloboda.schedule.command.MonthScheduleTelegramCommand
import com.anloboda.schedule.command.TodayScheduleTelegramCommand
import com.anloboda.schedule.command.TomorrowScheduleTelegramCommand
import com.anloboda.schedule.command.WeekScheduleTelegramCommand
import com.anloboda.schedule.command.exception.NoSuchCommandException
import com.anloboda.schedule.service.ScheduleService
import org.springframework.stereotype.Component


@Component
class TelegramCommandExecutor(scheduleService: ScheduleService) {

    private val requesters = mapOf(
        "/today" to TodayScheduleTelegramCommand(scheduleService),
        "/tomorrow" to TomorrowScheduleTelegramCommand(scheduleService),
        "/week" to WeekScheduleTelegramCommand(scheduleService),
        "/month" to MonthScheduleTelegramCommand(scheduleService)
    )

    fun execute(parameter: String): String = requesters[parameter]?.execute() ?: throw NoSuchCommandException()

}

