package com.anloboda.schedule.bot

import com.anloboda.schedule.api.ItalkiApiErrorException
import com.anloboda.schedule.service.ScheduleService
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Controller
class ScheduleBot(
    @Value("\${telegram.bot.token}") private val botToken: String,
    @Value("\${telegram.bot.username}") private val botUsername: String,
    private val scheduleService: ScheduleService,
) : TelegramLongPollingBot(botToken) {

    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update) {
        with(update) {
            if (hasMessage() && message.hasText()) {
                val responseMessage = SendMessage.builder()
                    .chatId(message.chatId.toString())
                    .text(getResponseText(message.text))
                    .build()
                execute(responseMessage)
            }
        }
    }

    private fun getResponseText(text: String) = when {
        text.startsWith("/schedule") -> {
            getSchedule()
        }
        else -> {
            "No such command"
        }
    }

    private fun getSchedule() = try {
        val schedule = scheduleService.get(5191769)
        schedule.toTelegramString()
    } catch (ex: ItalkiApiErrorException) {
        "Sorry, Italki is currently unavailable, please try again later"
    } catch (ex: Exception) {
        "Sorry, bot is currently unavailable, please try again later"
    }
}