package com.conscifora.vocab.mapper;

import com.conscifora.vocab.domain.entity.VocabDefinitions;
import com.conscifora.vocab.domain.entity.VocabExamples;
import com.conscifora.vocab.domain.entity.VocabTranslation;
import com.conscifora.vocab.domain.dto.VocabAntonymDto;
import com.conscifora.vocab.domain.dto.VocabDefinitionDto;
import com.conscifora.vocab.domain.dto.VocabExampleDto;
import com.conscifora.vocab.domain.dto.VocabSlangDto;
import com.conscifora.vocab.domain.dto.VocabSynonymDto;
import com.conscifora.vocab.domain.dto.VocabWordDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VocabTranslationMapper {
    VocabTranslation toEntity(VocabWordDto vocabWordDto);

    @Mapping(source = "vocabTarget.languageCode", target = "languageCode")
    @Mapping(source = "vocabTarget.word", target = "word")
    VocabWordDto wordToDto(VocabTranslation vocabTranslation);

    @Mapping(source = "text", target = "text")
    VocabExampleDto exampleToDto(VocabExamples vocabTranslation);

    @Mapping(source = "definition", target = "definition")
    VocabDefinitionDto definitionToDto(VocabDefinitions vocabTranslation);

    @Mapping(source = "vocabTarget.word", target = "synonym")
    VocabSynonymDto synonymToDto(VocabTranslation vocabTranslation);

    @Mapping(source = "vocabTarget.word", target = "antonym")
    VocabAntonymDto antonymToDto(VocabTranslation vocabTranslation);

    @Mapping(source = "vocabTarget.word", target = "record")
    VocabSlangDto slangToDto(VocabTranslation vocabTranslation);

    Set<VocabWordDto> directTranslationToDto(List<VocabTranslation> vocabTranslation);
    Set<VocabExampleDto> examplesToDto(Set<VocabExamples> vocabExamples);
    Set<VocabDefinitionDto> definitionToDto(Set<VocabDefinitions> vocabDefinitions);
    Set<VocabSynonymDto> synonymToDto(Set<VocabTranslation> vocabTranslation);
    Set<VocabAntonymDto> antonymToDto(Set<VocabTranslation> vocabTranslation);
    Set<VocabSlangDto> slangToDto(Set<VocabTranslation> vocabTranslation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VocabTranslation partialUpdate(VocabWordDto vocabWordDto, @MappingTarget VocabTranslation vocabTranslation);
}