package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService
import com.anloboda.schedule.service.model.Schedule
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.time.LocalDate
import java.time.LocalDateTime

class TodayScheduleTelegramCommandTest {

    private val service = mockk<ScheduleService>()
    private val todayScheduleCommand = TodayScheduleTelegramCommand(service)

    @Test
    fun testExecute() {
        //given
        val fixedDate = LocalDate.of(2023, 2,1)

        mockkStatic(LocalDate::class)
        every { LocalDate.now() } returns fixedDate

        val expected = Schedule(emptyList())
        every { service.get("2023-02-01T00:00:00Z","2023-02-01T23:59:59Z") } returns expected

        //when
        val actual = todayScheduleCommand.execute()

        //then
        verify(exactly = 1) { service.get("2023-02-01T00:00:00Z","2023-02-01T23:59:59Z") }
        assertEquals("No lessons :(", actual)
    }
}