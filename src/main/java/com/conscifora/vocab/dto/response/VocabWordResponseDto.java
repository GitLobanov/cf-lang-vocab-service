package com.conscifora.vocab.dto.response;

import com.conscifora.vocab.dto.VocabDTO;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Builder
public record VocabWordResponseDto(
        Set<VocabDTO> translation
) {
}
