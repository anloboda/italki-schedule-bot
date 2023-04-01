package com.anloboda.schedule.api.response

import com.fasterxml.jackson.annotation.JsonProperty

data class ItalkiScheduleResponse(
    @JsonProperty("data")
    val schedule: ItalkiScheduleData
)

data class ItalkiScheduleData(
    @JsonProperty("teacher_lesson") val lessons: List<ItalkiLesson>
)

data class ItalkiLesson(
    @JsonProperty("start_time") val startTime: String,
    @JsonProperty("end_time") val endTime: String
)