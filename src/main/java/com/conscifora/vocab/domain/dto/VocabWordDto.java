package com.conscifora.vocab.domain.dto;

import com.conscifora.vocab.domain.constant.LanguageCode;
import com.conscifora.vocab.domain.entity.Vocab;
import lombok.Builder;

/**
 * DTO for {@link Vocab}
 */
@Builder
public record VocabWordDto(
        LanguageCode languageCode,
        String word) {
}