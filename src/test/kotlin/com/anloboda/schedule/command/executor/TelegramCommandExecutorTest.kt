package com.anloboda.schedule.command.executor

import com.anloboda.schedule.command.TodayScheduleTelegramCommand
import com.anloboda.schedule.command.exception.NoSuchCommandException
import com.anloboda.schedule.service.ScheduleService
import com.anloboda.schedule.service.model.Schedule
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import kotlin.test.assertFailsWith

class TelegramCommandExecutorTest {

    private val service = mockk<ScheduleService>()
    private val commandExecutor = TelegramCommandExecutor(
        mapOf(
            "/today" to TodayScheduleTelegramCommand(service)
        )
    )

    @Test
    fun executeShouldReturnScheduleString() {
        // given
        every { service.getSchedule(any(), any()) } returns Schedule(emptyList())

        // when
        val actual = commandExecutor.execute("/today")

        // then
        assertEquals("No lessons :(", actual)
    }

    @Test
    fun executeShouldThrowNoSuchCommandException() {
        // when
        assertFailsWith<NoSuchCommandException> { commandExecutor.execute("/unknown") }
    }
}