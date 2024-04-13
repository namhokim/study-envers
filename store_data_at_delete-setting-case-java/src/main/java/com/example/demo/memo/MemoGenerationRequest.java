package com.example.demo.memo;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

import java.time.LocalDateTime;

public record MemoGenerationRequest(
        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "메모 제목", example = "Lorem Ipsum")
        @Size(min = 1, max = 100)
        String title,

        @Schema(requiredMode = Schema.RequiredMode.REQUIRED, description = "메모 내용", example = "Neque porro quisquam est qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit...")
        @Size(min = 1, max = 16_777_215)  // ~ MEDIUMTEXT (16MB)
        String content
) {
    public MemoEntity toEntity() {
        var now = LocalDateTime.now();
        return new MemoEntity(null, title, content, now, now);
    }
}
