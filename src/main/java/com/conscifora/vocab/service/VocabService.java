package com.conscifora.vocab.service;

import com.conscifora.vocab.domain.dto.request.DefinitionRequestDto;
import com.conscifora.vocab.domain.dto.request.VocabCreationRequestDto;
import com.conscifora.vocab.domain.dto.request.VocabRequestDto;
import com.conscifora.vocab.domain.dto.response.VocabResponseDto;

import java.util.*;

public interface VocabService {

    Optional<VocabResponseDto> findTranslations(VocabRequestDto vocabRequestDto);
    void createVocabTranslation (List<VocabCreationRequestDto> vocabCreationRequestDto);
    void createVocabDefinition (DefinitionRequestDto definitionRequestDto);

}
