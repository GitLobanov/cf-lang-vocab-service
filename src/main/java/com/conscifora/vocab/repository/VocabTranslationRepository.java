package com.conscifora.vocab.repository;

import com.conscifora.vocab.domain.LanguageCode;
import com.conscifora.vocab.domain.TranslationType;
import com.conscifora.vocab.domain.VocabTranslation;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Set;

public interface VocabTranslationRepository extends JpaRepository<VocabTranslation, Long>, JpaSpecificationExecutor<VocabTranslation> {
    Set<VocabTranslation> findByVocabSource_WordIgnoreCaseAndTranslationTypeAndVocabSource_LanguageCodeAndVocabTarget_LanguageCode
            (@NonNull String word, @NonNull TranslationType translationType,
             @NonNull LanguageCode languageCodeSource, @NonNull LanguageCode languageCodeTarget);

}