package com.example.demo.memo;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.history.RevisionMetadata;

import java.time.LocalDateTime;

public record MemoHistoryResponse(
        Long revId,
        String revType,

        Long id,
        String title,
        String content,

        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime createdAt,
        @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss") LocalDateTime updatedAt
) {
    public static MemoHistoryResponse fromEntity(MemoEntity entity, RevisionMetadata<Long> metadata) {
        return new MemoHistoryResponse(
                metadata.getRequiredRevisionNumber(),
                metadata.getRevisionType().name(),
                entity.getId(),
                entity.getTitle(),
                entity.getContent(),
                entity.getCreatedAt(),
                entity.getUpdatedAt()
        );
    }
}
