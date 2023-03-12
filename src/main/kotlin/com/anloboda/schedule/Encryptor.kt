package com.anloboda.schedule

import org.jasypt.encryption.pbe.PooledPBEStringEncryptor


fun encrypt(value: String): String {
    val encryptor = PooledPBEStringEncryptor()
    encryptor.setAlgorithm("PBEWithMD5AndDES")
    encryptor.setPassword("253661Cher")
    encryptor.setPoolSize(1)
    return encryptor.encrypt(value)
}

fun decrypt(value: String): String {
    val encryptor = PooledPBEStringEncryptor()
    encryptor.setAlgorithm("PBEWithMD5AndDES")
    encryptor.setPassword("253661Cher")
    encryptor.setPoolSize(1)
    return encryptor.decrypt(value)
}
fun main() {
    println(encrypt("6081032414:AAHvtny8bfKOGEsHwwGA09yAsF3qtKfcrMI"))
    println(decrypt("bVxho21vD5brzGVmFu3wz66Wo5huAX83FL+bfeKA4he8D9QEQREpv7pD7R9ebAjuMU1f8dYZMPM="))
}