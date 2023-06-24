package me.golf.consumer.domain.coupoun.application

import me.golf.consumer.domain.coupoun.persist.CouponRepository
import me.golf.consumer.domain.coupoun.persist.Coupon
import me.golf.consumer.domain.failevent.application.FailEventHistoryService
import me.golf.consumer.domain.failevent.persist.EventStatus
import me.golf.consumer.domain.failevent.persist.FailEventHistory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class CouponCreatedConsumer(
    private val couponRepository: CouponRepository,
    private val failEventHistoryService: FailEventHistoryService
) {

    @KafkaListener(topics = ["coupon_create"], groupId = "group_1")
    fun couponCreateListener(userId: Long) {
        val result = kotlin.runCatching { couponRepository.save(Coupon(userId)) }

        val isFailure = result.exceptionOrNull()

        if (isFailure != null) {
            val errorMessage = isFailure.message?: "ProvideCouponFail"
            val failEventHistory = FailEventHistory(userId, errorMessage, EventStatus.FAIL)

            failEventHistoryService.save(failEventHistory)
        }
    }
}