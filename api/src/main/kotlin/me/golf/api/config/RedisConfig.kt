package me.golf.api.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.StringRedisSerializer

@Configuration
class RedisConfig(
    @Value("\${spring.data.redis.host}") private val host: String,
    @Value("\${spring.data.redis.port}") private val port: Int,
) {

    @Bean
    fun integerRedisTemplate(): RedisTemplate<String, Long> {
        val redisTemplate = RedisTemplate<String, Long>()

        redisTemplate.setConnectionFactory(redisConnectionFactory())

        redisTemplate.keySerializer = StringRedisSerializer()
        redisTemplate.valueSerializer = LongRedisSerializer()

        redisTemplate.hashKeySerializer = StringRedisSerializer()
        redisTemplate.hashValueSerializer = LongRedisSerializer()

        return redisTemplate
    }

    @Bean
    fun redisConnectionFactory(): RedisConnectionFactory = LettuceConnectionFactory(host, port)
}