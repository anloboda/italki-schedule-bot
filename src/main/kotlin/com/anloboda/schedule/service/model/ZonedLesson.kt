package com.anloboda.schedule.service.model

import java.time.ZonedDateTime

data class ZonedLesson(
    val startTime: ZonedDateTime,
    val endTime: ZonedDateTime
)