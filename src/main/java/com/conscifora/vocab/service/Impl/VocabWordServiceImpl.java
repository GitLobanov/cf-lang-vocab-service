package com.conscifora.vocab.service.Impl;

import com.conscifora.vocab.domain.VocabTranslation;
import com.conscifora.vocab.dto.VocabWordDto;
import com.conscifora.vocab.dto.request.VocabRequestDto;
import com.conscifora.vocab.dto.response.VocabResponseDto;
import com.conscifora.vocab.mapper.VocabTranslationMapper;
import com.conscifora.vocab.repository.VocabTranslationRepository;
import com.conscifora.vocab.service.VocabWordService;
import com.conscifora.vocab.specification.VocabTranslationFilter;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class VocabWordServiceImpl implements VocabWordService {

    private final VocabTranslationRepository vocabTranslationRepository;
    private final VocabTranslationMapper vocabTranslationMapper;

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public Optional<VocabResponseDto> findTranslations(VocabRequestDto vocabRequestDto) {
        VocabTranslationFilter filter = VocabTranslationFilter.builder()
                .vocabSourceWord(vocabRequestDto.word())
                .translationType(vocabRequestDto.translationType())
                .vocabSourceLanguageCode(vocabRequestDto.langSourceCode())
                .vocabTargetLanguageCodeIn(vocabRequestDto.langTargetCode())
                .build();

        List<VocabTranslation> translates = vocabTranslationRepository.findAll(filter.toSpecification());
        Set<VocabWordDto> translations = vocabTranslationMapper.directTranslationToDto(translates);

        return Optional.of(
                VocabResponseDto.builder()
                        .translations(translations)
//                        .definitions()
//                        .examples()
//                        .synonyms()
//                        .antonyms()
//                        .slangs()
                        .build()
        );
    }

}
