package com.example.practiceJavaKotlin.taskFirst.middle.UniqueAddressUseCase

import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.autoconfigure.ImportAutoConfiguration
import org.springframework.boot.autoconfigure.cache.CacheAutoConfiguration
import org.springframework.boot.autoconfigure.data.redis.RedisAutoConfiguration
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.cache.annotation.EnableCaching
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.StringRedisTemplate
import org.springframework.stereotype.Component
import org.springframework.stereotype.Indexed
import java.io.File
import java.util.*
import kotlin.collections.HashSet

@EnableCaching
@Component
class UniqueAddressImpl(
        val redisTemplate: StringRedisTemplate
) {


    fun handle() {
        readFileLineByLineUsingForEachLine()
    }

    private fun readFileLineByLineUsingForEachLine() {
        var counter: Int = 0

        File("src/main/resources/addressFile.txt").forEachLine {



            counter++
        }
    }
}
