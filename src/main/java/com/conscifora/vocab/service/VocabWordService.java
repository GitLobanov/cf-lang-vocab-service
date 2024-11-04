package com.conscifora.vocab.service;

import com.conscifora.vocab.dto.request.VocabWordRequestDto;
import com.conscifora.vocab.dto.response.VocabWordResponseDto;

public interface VocabWordService {

    VocabWordResponseDto findTranslations(VocabWordRequestDto vocabWordRequestDto);

}
