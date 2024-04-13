package com.example.demo.envers;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@RevisionEntity
@Table(name = "revinfo")
@Getter
@Setter
public class EnversLongRevisionEntity implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @RevisionNumber
    @Column(name = "rev")
    private Long id;

    @RevisionTimestamp
    @Column(name = "revised_at", nullable = false)
    private LocalDateTime revisedAt;
}
