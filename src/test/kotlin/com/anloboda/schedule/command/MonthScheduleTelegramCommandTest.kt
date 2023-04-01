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
import java.time.YearMonth

class MonthScheduleTelegramCommandTest {

    private val service = mockk<ScheduleService>()
    private val monthScheduleCommand = MonthScheduleTelegramCommand(service)

    @Test
    fun testExecute() {
        // given
        val fixedDate = LocalDate.of(2023, 3, 1)
        mockkStatic(LocalDate::class)
        every { LocalDate.now() } returns fixedDate

        val fixedMonth = YearMonth.of(2023, 3)
        mockkStatic(YearMonth::class)
        every { YearMonth.now() } returns fixedMonth

        val expected = Schedule(emptyList())
        every { service.get("2023-03-01T00:00:00Z", "2023-03-31T23:59:59Z") } returns expected

        // when
        val actual = monthScheduleCommand.execute()

        // then
        verify(exactly = 1) { service.get("2023-03-01T00:00:00Z", "2023-03-31T23:59:59Z") }
        Assertions.assertEquals("No lessons :(", actual)
    }
}
