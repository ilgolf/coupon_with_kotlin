package me.golf.api.domain.application

import me.golf.api.domain.persist.Coupon
import me.golf.api.domain.persist.CouponCountRepository
import me.golf.api.domain.persist.CouponRepository
import me.golf.api.producer.CouponCreateProducer
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class CouponService(
    private val couponRepository: CouponRepository,
    private val couponCountRepository: CouponCountRepository,
    private val couponCreateProducer: CouponCreateProducer
) {

    @Transactional
    fun apply(userId: Long) {
        val count: Long = couponRepository.count()

        couponCountCheck(count)

        couponRepository.save(Coupon(userId))
    }

    @Transactional
    fun applyVer2(userId: Long) {
        val count: Long = couponCountRepository.increment(userId)
            ?: throw IllegalArgumentException("쿠폰 발급 수량 정보를 가져오는데 실패했습니다.")

        couponCountCheck(count)

        couponRepository.save(Coupon(userId))
    }

    @Transactional
    fun applyVer3(userId: Long) {
        val count: Long = couponCountRepository.increment(userId)
            ?: throw IllegalArgumentException("쿠폰 발급 수량 정보를 가져오는데 실패했습니다.")

        try {
            couponCountCheck(count)
        } catch (e: Exception) {
            return
        }

        couponCreateProducer.create(userId)
    }

    private fun couponCountCheck(count: Long) {
        if (count > 100) {
            throw IllegalArgumentException("이미 쿠폰 수량이 모두 소진 되었습니다.")
        }
    }
}