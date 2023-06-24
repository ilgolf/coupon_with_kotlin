package me.golf.consumer.domain.failevent.persist

import org.springframework.data.jpa.repository.JpaRepository

interface FailEventHistoryRepository: JpaRepository<FailEventHistory, Long> {

}
