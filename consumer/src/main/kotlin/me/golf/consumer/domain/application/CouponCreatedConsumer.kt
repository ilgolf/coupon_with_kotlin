package me.golf.consumer.domain.application

import me.golf.consumer.domain.persist.CouponRepository
import me.golf.consumer.domain.persist.Coupon
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CouponCreatedConsumer(
    private val couponRepository: CouponRepository
) {

    @KafkaListener(topics = ["coupon_create"], groupId = "group_1")
    fun couponCreateListener(userId: Long) {
        couponRepository.save(Coupon(userId))
    }
}