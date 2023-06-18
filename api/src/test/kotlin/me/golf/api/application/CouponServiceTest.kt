package me.golf.api.application

import me.golf.api.domain.application.CouponService
import me.golf.api.domain.persist.CouponRepository
import org.assertj.core.api.Assertions.*
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.util.concurrent.CountDownLatch
import java.util.concurrent.Executors

@SpringBootTest
internal class CouponServiceTest

@Autowired
constructor(
    private val couponService: CouponService,
    private val couponRepository: CouponRepository,
) {

    @Test
    @DisplayName("쿠폰을 사용자가 구매한다.")
    fun apply() {
        couponService.apply(1L)

        val count: Long = couponRepository.count()

        assertThat(count).isEqualTo(1L)
    }


    @Test
    @DisplayName("여러 사용자가 쿠폰을 구매한다.")
    fun applyManyPeople() {
        val threadCount = 1000
        val executorService = Executors.newFixedThreadPool(32)
        val latch = CountDownLatch(threadCount)

        for (i in 1 .. threadCount) {
            executorService.execute {
                try {
                    couponService.applyVer3(i.toLong())
                } finally {
                    latch.countDown()
                }
            }
        }

        latch.await()

        Thread.sleep(10000)

        val count: Long = couponRepository.count()
        assertThat(count).isEqualTo(100)
    }
}