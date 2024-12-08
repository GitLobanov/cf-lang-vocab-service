package com.conscifora.vocab.domain.dto.request;

import java.util.List;

public record VocabCreationRequestDto(
//        List<VocabCreation> vocabs
        Integer id,
        String en,
        String ru,
        String tr
) {
    public record VocabCreation (
            Integer id,
            String en,
            String ru,
            String tr
    ) {
    }
}
