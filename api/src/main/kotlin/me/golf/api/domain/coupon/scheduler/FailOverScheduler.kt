package me.golf.api.domain.coupon.scheduler

import me.golf.api.domain.failevent.application.FailEventHistoryService
import org.springframework.scheduling.annotation.Scheduled

class FailOverScheduler(
    private val failEventHistoryService: FailEventHistoryService
) {

    @Scheduled(cron = "0 */5 * * * ?")
    fun publishCouponFailOverScheduler() {
        failEventHistoryService.retry()
    }
}