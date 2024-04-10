package com.example.demo.memo

import com.fasterxml.jackson.annotation.JsonFormat
import java.time.LocalDateTime

data class MemoEntityResponse(
    val id: Long,
    val title: String,
    val content: String,

    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val createdAt: LocalDateTime,

    @field:JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    val updatedAt: LocalDateTime,
)

fun MemoEntity.toResponse(): MemoEntityResponse {
    return MemoEntityResponse(
        id = id,
        title = title,
        content = content,
        createdAt = createdAt,
        updatedAt = updatedAt,
    )
}
