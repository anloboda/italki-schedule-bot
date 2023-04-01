package com.anloboda.schedule.config

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.beans.factory.annotation.Value
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.EnableCaching
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@EnableCaching
@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(
        @Value("\${feign.client.config.italki.get-schedule.cache.maxSize}") scheduleCacheMaxSize: Long,
        @Value("\${feign.client.config.italki.get-schedule.cache.expireAfterWrite}") scheduleCacheExpireAfterWrite: Long
    ): CacheManager {
        val cacheManager = SimpleCacheManager()
        val getScheduleCache =
            caffeineCache("get-schedule", scheduleCacheMaxSize, scheduleCacheExpireAfterWrite, TimeUnit.MINUTES)
        cacheManager.setCaches(listOf(getScheduleCache))
        return cacheManager
    }

    private fun caffeineCache(
        name: String,
        maxSize: Long = 100,
        expireAfterWrite: Long = 10,
        timeUnit: TimeUnit = TimeUnit.MINUTES
    ) = CaffeineCache(
        name,
        Caffeine.newBuilder().maximumSize(maxSize).expireAfterWrite(expireAfterWrite, timeUnit).build()
    )
}
