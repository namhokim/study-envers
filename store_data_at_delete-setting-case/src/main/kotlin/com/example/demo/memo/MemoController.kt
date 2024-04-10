package com.example.demo.memo

import jakarta.validation.Valid
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
class MemoController(
    private val memoRepository: MemoRepository,
) {
    @PostMapping("/memo")
    @ResponseStatus(code = HttpStatus.CREATED)
    fun createMemo(@Valid @RequestBody newRequest: MemoGenerationRequest): Long =
        newRequest.toEntity()
            .let { memoRepository.save(it) }
            .id

    @GetMapping("/memo/{memoId}")
    fun getMemo(@PathVariable memoId: Long): MemoEntityResponse =
        memoRepository.findById(memoId)
            .orElseThrow { RuntimeException("Not found") }
            .toResponse()

    @PutMapping("/memo/{memoId}")
    fun updateMemo(
        @PathVariable memoId: Long,
        @Valid @RequestBody editRequest: MemoGenerationRequest,
    ): Long = memoRepository.findById(memoId)
        .orElseThrow { RuntimeException("Not found") }
        .cloneForUpdate(editRequest.toEntity())
        .let { memoRepository.save(it) }
        .id

    @DeleteMapping("/memo/{memoId}")
    fun deleteMemo(@PathVariable memoId: Long) = memoRepository.deleteById(memoId)

    @GetMapping("/memo/{memoId}/history")
    fun getMemoHistory(
        @PathVariable memoId: Long,
        @RequestParam(required = false, defaultValue = "0") pageNumber: Int,
        @RequestParam(required = false, defaultValue = "10") pageSize: Int,
    ): List<MemoHistoryResponse> =
        memoRepository.findRevisions(memoId, PageRequest.of(pageNumber, pageSize))
            .map { it.entity.toHistoryResponse(it.metadata) }.content
}
