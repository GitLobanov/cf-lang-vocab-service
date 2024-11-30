package com.conscifora.vocab.repository;

import com.conscifora.vocab.domain.constant.TranslationType;
import com.conscifora.vocab.domain.entity.VocabTranslation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.Set;

public interface VocabTranslationRepository extends JpaRepository<VocabTranslation, Long>, JpaSpecificationExecutor<VocabTranslation> {

    Set<VocabTranslation> findByTranslationType(TranslationType translationType);
    Set<VocabTranslation> findByTranslationTypeAndVocabSource_WordIgnoreCase(TranslationType translationType, String word);

}