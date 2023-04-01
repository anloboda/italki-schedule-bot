package com.anloboda.schedule.command.executor

import com.anloboda.schedule.command.TelegramCommand
import com.anloboda.schedule.command.exception.NoSuchCommandException
import org.springframework.stereotype.Component

@Component
class TelegramCommandExecutor(private val commands: Map<String, TelegramCommand>) {

    fun execute(parameter: String): String = commands[parameter]?.execute() ?: throw NoSuchCommandException()
}
