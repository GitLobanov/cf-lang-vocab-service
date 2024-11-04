package com.conscifora.vocab.mapper;

import com.conscifora.vocab.domain.VocabTranslation;
import com.conscifora.vocab.dto.VocabDTO;
import org.mapstruct.*;

import java.util.List;
import java.util.Set;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VocabTranslationMapper {
    VocabTranslation toEntity(VocabDTO vocabDTO);

    @Mapping(source = "vocabSource.languageCode", target = "languageCode")
    @Mapping(source = "vocabTarget.word", target = "word")
    VocabDTO toDto(VocabTranslation vocabTranslation);

    Set<VocabDTO> toDtos(List<VocabTranslation> vocabTranslation);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    VocabTranslation partialUpdate(VocabDTO vocabDTO, @MappingTarget VocabTranslation vocabTranslation);
}