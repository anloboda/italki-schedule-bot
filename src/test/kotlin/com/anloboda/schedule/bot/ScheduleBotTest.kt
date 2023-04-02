package com.anloboda.schedule.bot

import com.anloboda.schedule.api.ItalkiApiErrorException
import com.anloboda.schedule.command.exception.NoSuchCommandException
import com.anloboda.schedule.command.executor.TelegramCommandExecutor
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

class ScheduleBotTest {

    private val commandExecutor = mockk<TelegramCommandExecutor>()
    private val scheduleBot =
        spyk(
            ScheduleBot(
                botToken = "token",
                botUsername = "username",
                telegramCommandExecutor = commandExecutor
            )
        )

    @Test
    fun testOnUpdateReceived() {
        // given
        val expectedSendMessage = SendMessage.builder()
            .chatId("123456")
            .text("Schedule")
            .build()

        every { commandExecutor.execute(any()) } returns "Schedule"
        every { scheduleBot.execute(expectedSendMessage) } returns null

        // when
        scheduleBot.onUpdateReceived(buildUpdate())

        // then
        verify(exactly = 1) { commandExecutor.execute("/today") }
        verify(exactly = 1) { scheduleBot.execute(expectedSendMessage) }
    }

    @ParameterizedTest
    @MethodSource("parameters")
    fun testOnUpdateReceivedErrorCases(exception: Exception, message: String) {
        // given
        val expectedSendMessage = SendMessage.builder()
            .chatId("123456")
            .text(message)
            .build()

        every { commandExecutor.execute(any()) } throws exception
        every { scheduleBot.execute(expectedSendMessage) } returns null

        // when
        scheduleBot.onUpdateReceived(buildUpdate())

        // then
        verify(exactly = 1) { commandExecutor.execute("/today") }
        verify(exactly = 1) { scheduleBot.execute(expectedSendMessage) }
    }

    companion object {
        @JvmStatic
        fun parameters(): List<Arguments> = listOf<Arguments>(
            Arguments.of(ItalkiApiErrorException(), "Sorry, Italki is currently unavailable, please try again later"),
            Arguments.of(NoSuchCommandException(), "Sorry, this command doesn't exist, please select the command from the menu"),
            Arguments.of(Exception(), "Sorry, bot is currently unavailable, please try again later")
        )
    }

    private fun buildUpdate() =
        Update().apply {
            val message = mockk<Message>()
            every { message.chatId } returns 123456L
            every { message.text } returns "/today"
            every { message.hasText() } returns true
            this.message = message
        }
}