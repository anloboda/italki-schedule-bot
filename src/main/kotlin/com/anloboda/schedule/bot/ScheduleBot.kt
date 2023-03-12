package com.anloboda.schedule.bot

import com.anloboda.schedule.service.ScheduleService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Component
class ScheduleBot(
    @Value("\${telegram.bot.token}")
    private val botToken: String,
    @Value("\${telegram.bot.username}")
    private val botUsername: String,
    private val scheduleService: ScheduleService
) : TelegramLongPollingBot(botToken) {

    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update) {
        if (update.hasMessage() && update.message.hasText()){
            val message = update.message
            val text = message.text
            if (text.startsWith("/schedule")){
                val schedule = scheduleService.get(11133053)
                execute(
                    SendMessage.builder()
                        .chatId(message.chatId.toString())
                        .text(schedule.toTelegramString())
                        .build()
                )
            }
        }
    }
}