package me.golf.api.domain.failevent.application

import me.golf.api.domain.coupon.application.CouponService
import me.golf.api.domain.failevent.persist.EventStatus
import me.golf.api.domain.failevent.persist.FailEventHistory
import me.golf.api.domain.failevent.persist.FailEventHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FailEventHistoryService(
    private val failEventHistoryRepository: FailEventHistoryRepository,
    private val couponService: CouponService
) {

    @Transactional
    fun retry() {
        val historyByStatus = failEventHistoryRepository.findByEventStatus(EventStatus.FAIL)

        for (status in historyByStatus) {
            status.eventStatus = EventStatus.PROCESSING

            val userId = status.userId
            couponService.failCouponRetry(userId)

            status.eventStatus = EventStatus.SUCCESS
        }
    }
}
