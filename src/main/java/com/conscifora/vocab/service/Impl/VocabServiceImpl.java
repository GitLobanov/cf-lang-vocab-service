package com.conscifora.vocab.service.Impl;

import com.conscifora.vocab.domain.constant.LanguageCode;
import com.conscifora.vocab.domain.constant.TranslationType;
import com.conscifora.vocab.domain.dto.request.VocabCreationRequestDto;
import com.conscifora.vocab.domain.entity.Vocab;
import com.conscifora.vocab.domain.entity.VocabDefinitions;
import com.conscifora.vocab.domain.entity.VocabExamples;
import com.conscifora.vocab.domain.entity.VocabTranslation;
import com.conscifora.vocab.domain.dto.VocabAntonymDto;
import com.conscifora.vocab.domain.dto.VocabDefinitionDto;
import com.conscifora.vocab.domain.dto.VocabExampleDto;
import com.conscifora.vocab.domain.dto.VocabSlangDto;
import com.conscifora.vocab.domain.dto.VocabSynonymDto;
import com.conscifora.vocab.domain.dto.VocabWordDto;
import com.conscifora.vocab.domain.dto.request.VocabRequestDto;
import com.conscifora.vocab.domain.dto.response.VocabResponseDto;
import com.conscifora.vocab.mapper.VocabTranslationMapper;
import com.conscifora.vocab.repository.VocabDefinitionsRepository;
import com.conscifora.vocab.repository.VocabExamplesRepository;
import com.conscifora.vocab.repository.VocabRepository;
import com.conscifora.vocab.repository.VocabTranslationRepository;
import com.conscifora.vocab.service.VocabService;
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
public class VocabServiceImpl implements VocabService {

    private final VocabTranslationRepository vocabTranslationRepository;
    private final VocabDefinitionsRepository vocabDefinitionsRepository;
    private final VocabExamplesRepository vocabExamplesRepository;
    private final VocabTranslationMapper vocabTranslationMapper;
    private final VocabRepository vocabRepository;

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

    @Override
    public void createVocabTranslation(VocabCreationRequestDto vocabCreationRequestDto) {
        Vocab ru = vocabRepository.findByWord(vocabCreationRequestDto.ru())
                .orElse(Vocab.builder()
                        .word(vocabCreationRequestDto.ru())
                        .languageCode(LanguageCode.RU)
                        .build());
        Vocab en = vocabRepository.findByWord(vocabCreationRequestDto.en())
                .orElse(Vocab.builder()
                        .word(vocabCreationRequestDto.en())
                        .languageCode(LanguageCode.EN)
                        .build());

        VocabTranslation vocabTranslation = VocabTranslation.builder()
                .translationType(TranslationType.DIRECT)
                .vocabSource(ru)
                .vocabTarget(en)
                .build();

        vocabTranslationRepository.save(vocabTranslation);
    }

}