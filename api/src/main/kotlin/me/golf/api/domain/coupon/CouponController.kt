package me.golf.api.domain.coupon

import me.golf.api.domain.coupon.application.CouponService
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CouponController(
    private val couponService: CouponService
) {

    @PostMapping("/coupons/{userId}")
    fun applyCoupon(@PathVariable userId: Long) {
        couponService.applyVer3(userId)
    }
}