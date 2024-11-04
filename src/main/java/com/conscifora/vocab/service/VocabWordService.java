package com.conscifora.vocab.service;

import com.conscifora.vocab.dto.request.VocabRequestDto;
import com.conscifora.vocab.dto.response.VocabResponseDto;

import java.util.*;

public interface VocabWordService {

    Optional<VocabResponseDto> findTranslations(VocabRequestDto vocabRequestDto);

}
