package com.anloboda.schedule.bot

import com.anloboda.schedule.service.ScheduleService
import com.anloboda.schedule.service.model.Schedule
import io.mockk.every
import io.mockk.mockk
import io.mockk.spyk
import io.mockk.verify
import org.junit.jupiter.api.Test
import org.telegram.telegrambots.meta.api.methods.send.SendMessage
import org.telegram.telegrambots.meta.api.objects.Message
import org.telegram.telegrambots.meta.api.objects.Update

class ScheduleBotTest {

    private val scheduleService = mockk<ScheduleService>()
    private val scheduleBot =
        spyk(
            ScheduleBot(
                botToken = "token", botUsername = "username", scheduleService = scheduleService
            )
        )


    @Test
    fun testOnUpdateReceived() {
        // given
        val update = Update()
        val message = mockk<Message>()
        every { message.chatId } returns 123456L
        every { message.text } returns "/schedule"
        every { message.hasText() } returns true

        update.message = message

        val schedule = Schedule(emptyList())
        val sendMessage = SendMessage.builder()
            .chatId("123456")
            .text(schedule.toTelegramString())
            .build()

        every { scheduleService.get(any()) } returns schedule
        every { scheduleBot.execute(sendMessage) } returns null

        //when
        scheduleBot.onUpdateReceived(update)

        // then
        verify(exactly = 1) { scheduleService.get(any()) }
        verify(exactly = 1) { scheduleBot.execute(sendMessage) }
    }
}