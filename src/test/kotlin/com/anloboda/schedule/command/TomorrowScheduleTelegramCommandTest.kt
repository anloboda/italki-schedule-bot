package com.anloboda.schedule.command

import com.anloboda.schedule.service.ScheduleService
import com.anloboda.schedule.service.model.Schedule
import io.mockk.every
import io.mockk.mockk
import io.mockk.mockkStatic
import io.mockk.verify
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test
import java.time.LocalDate

class TomorrowScheduleTelegramCommandTest {

    private val service = mockk<ScheduleService>()
    private val tomorrowScheduleTelegramCommand = TomorrowScheduleTelegramCommand(service)

    @Test
    fun testExecute() {
        // given
        val fixedDate = LocalDate.of(2023, 2, 28)

        mockkStatic(LocalDate::class)
        every { LocalDate.now() } returns fixedDate

        val expected = Schedule(emptyList())
        every { service.get("2023-03-01T00:00:00Z", "2023-03-01T23:59:59Z") } returns expected

        // when
        val actual = tomorrowScheduleTelegramCommand.execute()

        // then
        verify(exactly = 1) { service.get("2023-03-01T00:00:00Z", "2023-03-01T23:59:59Z") }
        assertEquals("No lessons :(", actual)
    }
}