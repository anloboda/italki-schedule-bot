package com.anloboda.schedule.config

import com.anloboda.schedule.command.MonthScheduleTelegramCommand
import com.anloboda.schedule.command.TelegramCommand
import com.anloboda.schedule.command.TodayScheduleTelegramCommand
import com.anloboda.schedule.command.TomorrowScheduleTelegramCommand
import com.anloboda.schedule.command.WeekScheduleTelegramCommand
import com.anloboda.schedule.service.ScheduleService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class ApplicationConfig {

    @Bean
    fun commands(service: ScheduleService): Map<String, TelegramCommand> =
        mapOf(
            "/today" to TodayScheduleTelegramCommand(service),
            "/tomorrow" to TomorrowScheduleTelegramCommand(service),
            "/week" to WeekScheduleTelegramCommand(service),
            "/month" to MonthScheduleTelegramCommand(service)
        )
}