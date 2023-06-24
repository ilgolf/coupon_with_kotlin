package me.golf.api.domain.failevent.persist

import org.springframework.data.jpa.repository.JpaRepository

interface FailEventHistoryRepository: JpaRepository<FailEventHistory, Long> {

    fun findByEventStatus(eventStatus: EventStatus): List<FailEventHistory>
}
