package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService
import com.anloboda.schedule.service.model.Schedule
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate

class WeekScheduleTelegramCommandTest {

    private val service = mockk<ScheduleService>()
    private val weekScheduleTelegramCommand = WeekScheduleTelegramCommand(service)

    @Test
    fun testExecute() {
        // given
        val fixedDate = LocalDate.of(2023, 2, 28)

        mockkStatic(LocalDate::class)
        every { LocalDate.now() } returns fixedDate

        val expected = Schedule(emptyList())
        every { service.getSchedule("2023-02-28T00:00:00Z", "2023-03-05T23:59:59Z") } returns expected

        // when
        val actual = weekScheduleTelegramCommand.execute()

        // then
        verify(exactly = 1) { service.getSchedule("2023-02-28T00:00:00Z", "2023-03-05T23:59:59Z") }
        Assertions.assertEquals("No lessons :(", actual)
    }
}