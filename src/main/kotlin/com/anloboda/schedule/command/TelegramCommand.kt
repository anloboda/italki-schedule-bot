package com.anloboda.schedule.command

interface TelegramCommand {
    fun execute(): String
}