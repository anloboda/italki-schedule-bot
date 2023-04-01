package com.anloboda.schedule.api

import com.anloboda.schedule.api.response.ItalkiScheduleResponse
import com.anloboda.schedule.config.OKHttpClientConfig
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import org.springframework.cache.annotation.Cacheable
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(
    name = "italkiClient",
    url = "\${feign.client.config.italki.url}",
    configuration = [OKHttpClientConfig::class]
)
interface ScheduleApi {

    @Cacheable("get-schedule")
    @CircuitBreaker(name = "get-schedule")
    @GetMapping("/api/v2/teacher/{id}/schedule", produces = ["application/json"])
    fun get(
        @PathVariable(name = "id") id: Int,
        @RequestParam(value = "start_time") startTime: String,
        @RequestParam(value = "end_time") endTime: String
    ): ItalkiScheduleResponse
}