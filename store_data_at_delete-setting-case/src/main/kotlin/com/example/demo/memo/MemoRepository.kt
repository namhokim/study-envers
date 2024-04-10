package com.example.demo.memo

import org.springframework.data.repository.CrudRepository
import org.springframework.data.repository.history.RevisionRepository

interface MemoRepository: CrudRepository<MemoEntity, Long>, RevisionRepository<MemoEntity, Long, Long>
