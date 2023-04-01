package com.anloboda.schedule.bot

import com.anloboda.schedule.api.ItalkiApiErrorException
import com.anloboda.schedule.command.exception.NoSuchCommandException
import com.anloboda.schedule.command.executor.TelegramCommandExecutor
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Controller
import org.telegram.telegrambots.bots.TelegramLongPollingBot
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Update

@Controller
class ScheduleBot(
    @Value("\${telegram.bot.token}") private val botToken: String,
    @Value("\${telegram.bot.username}") private val botUsername: String,
    private val telegramCommandExecutor: TelegramCommandExecutor
) : TelegramLongPollingBot(botToken) {

    override fun getBotUsername() = botUsername

    override fun onUpdateReceived(update: Update) {
        with(update) {
            if (hasMessage() && message.hasText()) {
                val responseMessage = SendMessage.builder()
                    .chatId(message.chatId.toString())
                    .text(executeCommand(message.text))
                    .build()

                execute(responseMessage)
            }
        }
    }

    private fun executeCommand(command: String) = try {
        telegramCommandExecutor.execute(command)
    } catch (ex: ItalkiApiErrorException) {
        "Sorry, Italki is currently unavailable, please try again later"
    } catch (ex: NoSuchCommandException) {
        "Sorry, this command doesn't exist, please select the command from the menu"
    } catch (ex: Exception) {
        "Sorry, bot is currently unavailable, please try again later"
    }
}