package me.golf.api.producer

import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class CouponCreateProducer(
    private val kafkaTemplate: KafkaTemplate<String, String>
) {

    fun create(userId: Long) {
        kafkaTemplate.send("coupon_create", userId.toString())
    }
}