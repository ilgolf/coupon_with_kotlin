package me.golf.api.domain.failevent.persist

enum class EventStatus(
    var description: String
) {

    FAIL("실패"),
    PROCESSING("실패 이벤트 처리 중"),
    SUCCESS("실피 이벤트 처리 완료")
}
