package com.example.demo.memo

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.data.history.RevisionMetadata
import java.time.LocalDateTime

data class MemoHistoryResponse(
    val revId: Long,
    val revType: String,

    val id: Long,
    val title: String,
    val content: String,

    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: LocalDateTime,

    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val updatedAt: LocalDateTime,
)

fun MemoEntity.toHistoryResponse(metadata: RevisionMetadata<Long>): MemoHistoryResponse {
    return MemoHistoryResponse(
        revId = metadata.requiredRevisionNumber,
        revType = metadata.revisionType.name,
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
