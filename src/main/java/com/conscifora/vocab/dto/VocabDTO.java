package com.conscifora.vocab.dto;

import com.conscifora.vocab.domain.LanguageCode;
import com.conscifora.vocab.domain.Vocab;
import com.conscifora.vocab.domain.VocabExamples;
import com.conscifora.vocab.domain.VocabTranslation;
import lombok.Builder;

import java.util.Set;

/**
 * DTO for {@link Vocab}
 */
@Builder
public record VocabDTO(
        LanguageCode languageCode,
        String word) {
}