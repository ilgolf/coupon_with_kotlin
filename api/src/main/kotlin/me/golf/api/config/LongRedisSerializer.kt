package me.golf.api.config

import org.springframework.data.redis.serializer.RedisSerializer
import java.nio.charset.StandardCharsets

class LongRedisSerializer: RedisSerializer<Long> {

    override fun serialize(value: Long?): ByteArray? {
        return value?.toString()?.toByteArray(StandardCharsets.UTF_8)
    }

    override fun deserialize(bytes: ByteArray?): Long? {
        return if (bytes == null) null else (String(bytes, StandardCharsets.UTF_8).toLong())

    }
}