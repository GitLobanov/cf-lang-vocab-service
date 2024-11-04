package com.conscifora.vocab.service.Impl;

import com.conscifora.vocab.domain.VocabTranslation;
import com.conscifora.vocab.dto.VocabDTO;
import com.conscifora.vocab.dto.request.VocabWordRequestDto;
import com.conscifora.vocab.dto.response.VocabWordResponseDto;
import com.conscifora.vocab.mapper.VocabTranslationMapper;
import com.conscifora.vocab.repository.VocabTranslationRepository;
import com.conscifora.vocab.service.VocabWordService;
import com.conscifora.vocab.specification.VocabTranslationFilter;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class VocabWordServiceImpl implements VocabWordService {

    private final VocabTranslationRepository vocabTranslationRepository;
    private final VocabTranslationMapper vocabTranslationMapper;

    @Override
    @Transactional
    @Lock(LockModeType.OPTIMISTIC)
    public VocabWordResponseDto findTranslations(VocabWordRequestDto vocabWordRequestDto) {
        VocabTranslationFilter filter = VocabTranslationFilter.builder()
                .vocabSourceWord(vocabWordRequestDto.word())
                .translationType(vocabWordRequestDto.translationType())
                .vocabSourceLanguageCode(vocabWordRequestDto.langSourceCode())
                .vocabTargetLanguageCodeIn(vocabWordRequestDto.langTargetCode())
                .build();

        List<VocabTranslation> translates = vocabTranslationRepository.findAll(filter.toSpecification());
        Set<VocabDTO> dtos = vocabTranslationMapper.toDtos(translates);

        return new VocabWordResponseDto(dtos);
    }

}
