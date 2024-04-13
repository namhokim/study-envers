package com.example.demo.memo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.history.RevisionRepository;

public interface MemoRepository extends CrudRepository<MemoEntity, Long>, RevisionRepository<MemoEntity, Long, Long> {
}
