package com.conscifora.vocab.dto.response;

import com.conscifora.vocab.domain.*;
import com.conscifora.vocab.dto.*;
import lombok.Builder;

import java.util.Set;

@Builder
public record VocabResponseDto(
        Set<VocabWordDto> translations,
        Set<VocabDefinitionDto> definitions,
        Set<VocabExampleDto> examples,
        Set<VocabAntonymDto> antonyms,
        Set<VocabSynonymDto> synonyms,
        Set<VocabSlangDto> slangs
) {
}
