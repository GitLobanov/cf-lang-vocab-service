package com.conscifora.vocab.domain.dto.request;

import com.conscifora.vocab.domain.constant.LanguageCode;
import com.conscifora.vocab.domain.constant.TranslationType;
import lombok.Builder;

import java.util.List;

@Builder
public record VocabRequestDto(
        String word,
        TranslationType translationType,
        LanguageCode langSourceCode,
        List<LanguageCode> langTargetCode
) {
}
