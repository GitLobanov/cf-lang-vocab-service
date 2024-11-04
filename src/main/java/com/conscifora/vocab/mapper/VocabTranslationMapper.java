package com.conscifora.vocab.mapper;

import com.conscifora.vocab.domain.VocabTranslation;
import com.conscifora.vocab.dto.VocabWordDto;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VocabTranslationMapper {
    VocabTranslation toEntity(VocabWordDto vocabWordDto);

    @Mapping(source = "vocabTarget.languageCode", target = "languageCode")
    @Mapping(source = "vocabTarget.word", target = "word")
    VocabWordDto wordToDto(VocabTranslation vocabTranslation);

    Set<VocabWordDto> directTranslationToDto(List<VocabTranslation> vocabTranslation);
    Set<VocabWordDto> examplesToDto(List<VocabTranslation> vocabTranslation);
    Set<VocabWordDto> definitionToDto(List<VocabTranslation> vocabTranslation);
    Set<VocabWordDto> synonymToDto(List<VocabTranslation> vocabTranslation);
    Set<VocabWordDto> antonymToDto(List<VocabTranslation> vocabTranslation);
    Set<VocabWordDto> slangToDto(List<VocabTranslation> vocabTranslation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VocabTranslation partialUpdate(VocabWordDto vocabWordDto, @MappingTarget VocabTranslation vocabTranslation);
}