package me.golf.consumer.domain.coupoun.persist

import org.springframework.data.jpa.repository.JpaRepository

interface CouponRepository: JpaRepository<Coupon, Long>