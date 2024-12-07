package com.conscifora.vocab.domain.dto.request;

public record VocabCreationRequestDto(
        Integer id,
        String en,
        String ru,
        String tr
) {
}
