package com.example.practiceJavaKotlin

import com.example.practiceJavaKotlin.taskFirst.middle.UniqueAddressUseCase.UniqueAddressImpl
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.cache.RedisCacheConfiguration
import org.springframework.data.redis.cache.RedisCacheManager.RedisCacheManagerBuilder
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer
import org.springframework.data.redis.serializer.RedisSerializationContext.SerializationPair
import java.time.Duration


@SpringBootApplication
class PracticeJavaKotlinApplication(
        val uniqueAddressImpl: UniqueAddressImpl
) {
    init {
        uniqueAddressImpl.handle()
    }
}

fun main(args: Array<String>) {
    runApplication<PracticeJavaKotlinApplication>(*args)
}

@Configuration
class Config {
    @Bean
    fun redisConnectionFactory(): JedisConnectionFactory {
        val jedisConnectionFactory = JedisConnectionFactory()
        jedisConnectionFactory.hostName = "your_host_name_or_ip"
        jedisConnectionFactory.port = 6379
        jedisConnectionFactory.afterPropertiesSet()
        return jedisConnectionFactory
    }
}
@Bean
fun cacheConfiguration(): RedisCacheConfiguration? {
    return RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60))
            .disableCachingNullValues()
            .serializeValuesWith(SerializationPair.fromSerializer(GenericJackson2JsonRedisSerializer()))
}

@Bean
fun redisCacheManagerBuilderCustomizer(): RedisCacheManagerBuilderCustomizer? {
    return RedisCacheManagerBuilderCustomizer { builder: RedisCacheManagerBuilder ->
        builder
                .withCacheConfiguration("itemCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(10)))
                .withCacheConfiguration("customerCache",
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(5)))
    }
}


