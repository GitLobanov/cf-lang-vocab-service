package com.conscifora.vocab.domain.dto.response;

import com.conscifora.vocab.domain.dto.VocabAntonymDto;
import com.conscifora.vocab.domain.dto.VocabDefinitionDto;
import com.conscifora.vocab.domain.dto.VocabExampleDto;
import com.conscifora.vocab.domain.dto.VocabSlangDto;
import com.conscifora.vocab.domain.dto.VocabSynonymDto;
import com.conscifora.vocab.domain.dto.VocabWordDto;
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
