package com.conscifora.vocab.dto;

import com.conscifora.vocab.domain.LanguageCode;
import com.conscifora.vocab.domain.Vocab;
import lombok.Builder;

/**
 * DTO for {@link Vocab}
 */
@Builder
public record VocabWordDto(
        LanguageCode languageCode,
        String word) {
}