package com.example.demo.envers

import jakarta.persistence.*
import org.hibernate.envers.RevisionEntity
import org.hibernate.envers.RevisionNumber
import org.hibernate.envers.RevisionTimestamp
import java.io.Serializable
import java.time.LocalDateTime

@Entity
@RevisionEntity
@Table(name = "revinfo")
data class EnversLongRevisionEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "rev")
    val id: Long,

    @RevisionTimestamp
    @Column(name = "revised_at", nullable = false)
    val revisedAt: LocalDateTime,
) : Serializable
