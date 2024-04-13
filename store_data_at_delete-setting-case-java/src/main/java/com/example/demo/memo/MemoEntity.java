package com.example.demo.memo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.envers.Audited;

import java.time.LocalDateTime;

@Audited
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MemoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    private String content;

    @Column(updatable = false)
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public MemoEntity cloneForUpdate(MemoEntity entity) {
        return new MemoEntity(id, entity.getTitle(), entity.getContent(), createdAt, LocalDateTime.now());
    }
}
