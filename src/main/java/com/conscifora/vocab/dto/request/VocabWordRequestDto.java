package com.conscifora.vocab.dto.request;

import com.conscifora.vocab.domain.LanguageCode;
import com.conscifora.vocab.domain.TranslationType;
import lombok.Builder;

import java.util.List;

@Builder
public record VocabWordRequestDto(
        String word,
        TranslationType translationType,
        LanguageCode langSourceCode,
        List<LanguageCode> langTargetCode
) {
}
