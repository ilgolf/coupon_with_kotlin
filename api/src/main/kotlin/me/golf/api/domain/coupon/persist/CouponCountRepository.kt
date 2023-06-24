package me.golf.api.domain.coupon.persist

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class CouponCountRepository(
    private val redisTemplate: RedisTemplate<String, String>
) {

    fun increment(userId: Long): Long? {
        return redisTemplate.opsForValue().increment("COUPON")
    }
}
