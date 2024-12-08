package com.conscifora.vocab.mapper;

import com.conscifora.vocab.domain.dto.VocabWordDto;
import com.conscifora.vocab.domain.entity.Vocab;
import org.mapstruct.*;

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = MappingConstants.ComponentModel.SPRING)
public interface VocabMapper {
    Vocab toEntity(VocabWordDto vocabWordDto);

    VocabWordDto toDto(Vocab vocab);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Vocab partialUpdate(VocabWordDto vocabWordDto, @MappingTarget Vocab vocab);
}