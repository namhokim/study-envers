package com.example.demo.memo

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Size
import java.time.LocalDateTime

@Schema(description = "메모 생성(신규/수정) 요청")
data class MemoGenerationRequest(
    @field:Schema(required = true, description = "메모 제목", example = "Lorem Ipsum")
    @field:Size(min = 1, max = 100)
    val title: String,
    @field:Schema(required = true, description = "메모 내용", example = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
    @field:Size(min = 1, max = 16_777_215)  // ~ MEDIUMTEXT (16MB)
    val content: String,
) {
    fun toEntity(): MemoEntity {
        val now: LocalDateTime = LocalDateTime.now()
        return MemoEntity(
            title = title,
            content = content,
            createdAt = now,
            updatedAt = now,
        )
    }
}
