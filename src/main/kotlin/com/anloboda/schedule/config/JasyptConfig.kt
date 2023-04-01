package com.anloboda.schedule.config

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class JasyptConfig {

    @Bean
    fun jasyptStringEncryptor(): PooledPBEStringEncryptor {
        val encryptor = PooledPBEStringEncryptor()
        encryptor.setAlgorithm("PBEWithMD5AndDES")
        encryptor.setPoolSize(10)
        encryptor.setPassword(System.getProperty("jasypt.encryptor.password"))
        return encryptor
    }
}