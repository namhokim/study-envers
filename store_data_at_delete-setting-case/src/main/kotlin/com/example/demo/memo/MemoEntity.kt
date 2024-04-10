package com.example.demo.memo

import jakarta.persistence.*
import org.hibernate.envers.Audited
import java.time.LocalDateTime

@Audited
@Entity
data class MemoEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,

    @Column(nullable = false, length = 100)
    val title: String,
    @Column(nullable = false, columnDefinition = "MEDIUMTEXT")
    val content: String,

    @Column(updatable = false)
    val createdAt: LocalDateTime,
    val updatedAt: LocalDateTime,
) {
    fun cloneForUpdate(editEntity: MemoEntity): MemoEntity {
        return this.copy(title = editEntity.title, content = editEntity.content, updatedAt = LocalDateTime.now())
    }
}
