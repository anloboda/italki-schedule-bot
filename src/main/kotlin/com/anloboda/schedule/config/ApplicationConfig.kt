package com.anloboda.schedule.config

import com.anloboda.schedule.command.*
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
