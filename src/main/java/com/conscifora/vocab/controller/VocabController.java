package com.conscifora.vocab.controller;

import com.conscifora.vocab.dto.request.VocabRequestDto;
import com.conscifora.vocab.dto.response.VocabResponseDto;
import com.conscifora.vocab.service.VocabWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("vocab/")
@RequiredArgsConstructor
public class VocabController {

    private final VocabWordService vocabWordService;

    /**
     * EP-1 Получение полной информации по слову
     * <a href="https://wiki.yandex.ru/svapi/api/ep-1/.edit?force-data-ui=true">Doc</a>
     */
    @GetMapping("/word")
    public ResponseEntity<VocabResponseDto> getVocabInfo(
            @RequestBody VocabRequestDto vocabRequestDto) {

        VocabResponseDto response =
                vocabWordService.findTranslations(vocabRequestDto)
                        .orElseThrow(() -> new RuntimeException("Vocab not found"));

        return ResponseEntity.ok(response);
    }

}
