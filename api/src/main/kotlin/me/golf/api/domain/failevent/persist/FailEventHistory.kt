package me.golf.api.domain.failevent.persist

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import java.time.LocalDateTime

@Entity
class FailEventHistory(
    var userId: Long,
    var errorMessage: String,
    var eventStatus: EventStatus,
) {

    @Id @GeneratedValue
    private var id: Long = 0L

    private var createAt: LocalDateTime = LocalDateTime.now()
}
