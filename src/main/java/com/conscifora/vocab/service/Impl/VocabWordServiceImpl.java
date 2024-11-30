package com.conscifora.vocab.service.Impl;

import com.conscifora.vocab.domain.constant.TranslationType;
import com.conscifora.vocab.domain.entity.VocabDefinitions;
import com.conscifora.vocab.domain.entity.VocabExamples;
import com.conscifora.vocab.domain.entity.VocabTranslation;
import com.conscifora.vocab.dto.VocabAntonymDto;
import com.conscifora.vocab.dto.VocabDefinitionDto;
import com.conscifora.vocab.dto.VocabExampleDto;
import com.conscifora.vocab.dto.VocabSlangDto;
import com.conscifora.vocab.dto.VocabSynonymDto;
import com.conscifora.vocab.dto.VocabWordDto;
import com.conscifora.vocab.dto.request.VocabRequestDto;
import com.conscifora.vocab.dto.response.VocabResponseDto;
import com.conscifora.vocab.mapper.VocabTranslationMapper;
import com.conscifora.vocab.repository.VocabDefinitionsRepository;
import com.conscifora.vocab.repository.VocabExamplesRepository;
import com.conscifora.vocab.repository.VocabTranslationRepository;
import com.conscifora.vocab.service.VocabWordService;
import com.conscifora.vocab.specification.VocabTranslationFilter;
import jakarta.persistence.LockModeType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class VocabWordServiceImpl implements VocabWordService {

    private final VocabTranslationRepository vocabTranslationRepository;
    private final VocabDefinitionsRepository vocabDefinitionsRepository;
    private final VocabExamplesRepository vocabExamplesRepository;
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

        log.info("Create filter : {}", filter.toString());

        List<VocabTranslation> translationsFromDb = vocabTranslationRepository.findAll(filter.toSpecification());
        Set<VocabExamples> examplesFromDb = vocabExamplesRepository.findByVocabs_WordIgnoreCase(vocabRequestDto.word());
        Set<VocabDefinitions> definitionsFromDb = vocabDefinitionsRepository.findByVocab_WordIgnoreCase(vocabRequestDto.word());

        // TODO CHANGE ALSO TO ONE FILTER SPECIFICATION
        Set<VocabTranslation> antonymsFromDb =
                vocabTranslationRepository.findByTranslationTypeAndVocabSource_WordIgnoreCase(TranslationType.ANTONYM, vocabRequestDto.word());
        Set<VocabTranslation> synonymsFromDb =
                vocabTranslationRepository.findByTranslationTypeAndVocabSource_WordIgnoreCase(TranslationType.SYNONYM, vocabRequestDto.word());
        Set<VocabTranslation> slangsFromDb =
                vocabTranslationRepository.findByTranslationTypeAndVocabSource_WordIgnoreCase(TranslationType.SLANG, vocabRequestDto.word());

        log.info("Data loaded from db");

        // TODO REFACTOR THIS
        Set<VocabWordDto> translations = vocabTranslationMapper.directTranslationToDto(translationsFromDb);
        Set<VocabDefinitionDto> definitions = vocabTranslationMapper.definitionToDto(definitionsFromDb);
        Set<VocabExampleDto> examples = vocabTranslationMapper.examplesToDto(examplesFromDb);
        Set<VocabAntonymDto> antonyms = vocabTranslationMapper.antonymToDto(antonymsFromDb);
        Set<VocabSynonymDto> synonyms = vocabTranslationMapper.synonymToDto(synonymsFromDb);
        Set<VocabSlangDto> slangs = vocabTranslationMapper.slangToDto(slangsFromDb);

        log.info("Data converted to DTO");

        return Optional.of(
                VocabResponseDto.builder()
                        .translations(translations)
                        .definitions(definitions)
                        .examples(examples)
                        .synonyms(synonyms)
                        .antonyms(antonyms)
                        .slangs(slangs)
                        .build()
        );
    }

}
