package com.example.demo.memo;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemoController {
    private final MemoRepository memoRepository;

    @PostMapping("/memo")
    @ResponseStatus(code = HttpStatus.CREATED)
    public long createMemo(@Valid @RequestBody MemoGenerationRequest newRequest) {
        final MemoEntity entity = newRequest.toEntity();
        return memoRepository.save(entity).getId();
    }


    @GetMapping("/memo/{memoId}")
    public MemoEntityResponse getMemo(@PathVariable long memoId) {
        final MemoEntity memoEntity = memoRepository.findById(memoId)
                .orElseThrow(() -> new RuntimeException("Not found"));
        return MemoEntityResponse.fromEntity(memoEntity);
    }


    @PutMapping("/memo/{memoId}")
    public long updateMemo(
            @PathVariable long memoId,
            @Valid @RequestBody MemoGenerationRequest editRequest) {
        final MemoEntity memoEntity = memoRepository.findById(memoId).orElseThrow(() -> new RuntimeException("Not found"));
        var updateMemo = memoEntity.cloneForUpdate(editRequest.toEntity());
        return memoRepository.save(updateMemo).getId();
    }

    @DeleteMapping("/memo/{memoId}")
    public void deleteMemo(@PathVariable long memoId) {
        memoRepository.deleteById(memoId);
    }

    @GetMapping("/memo/{memoId}/history")
    public List<MemoHistoryResponse> getMemoHistory(
            @PathVariable long memoId,
            @RequestParam(required = false, defaultValue = "0") int pageNumber,
            @RequestParam(required = false, defaultValue = "10") int pageSize) {
        return memoRepository.findRevisions(memoId, PageRequest.of(pageNumber, pageSize))
                .map(it -> MemoHistoryResponse.fromEntity(it.getEntity(), it.getMetadata()))
                .getContent();
    }

}
