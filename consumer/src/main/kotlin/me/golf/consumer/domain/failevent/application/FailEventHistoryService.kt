package me.golf.consumer.domain.failevent.application

import me.golf.consumer.domain.failevent.persist.FailEventHistory
import me.golf.consumer.domain.failevent.persist.FailEventHistoryRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class FailEventHistoryService(
    private val failEventHistoryRepository: FailEventHistoryRepository
) {

    @Transactional
    fun save(failEventHistory: FailEventHistory) {
        failEventHistoryRepository.save(failEventHistory)
    }
}
