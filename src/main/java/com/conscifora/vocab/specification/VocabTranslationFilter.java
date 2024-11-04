package com.conscifora.vocab.specification;

import com.conscifora.vocab.domain.LanguageCode;
import com.conscifora.vocab.domain.TranslationType;
import com.conscifora.vocab.domain.VocabTranslation;
import lombok.Builder;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.List;

@Builder
public record VocabTranslationFilter(TranslationType translationType, String vocabSourceWord,
                                     LanguageCode vocabSourceLanguageCode,
                                     List<LanguageCode> vocabTargetLanguageCodeIn) {
    public Specification<VocabTranslation> toSpecification() {
        return Specification.where(distinct())
                .and(translationTypeSpec())
                .and(vocabSourceWordSpec())
                .and(vocabSourceLanguageCodeSpec())
                .and(vocabTargetLanguageCodeInSpec());
    }

    private Specification<VocabTranslation> distinct() {
        return (root, query, cb) -> {
            if (query != null) {
                query.distinct(true);
            }
            return null;
        };
    }

    private Specification<VocabTranslation> translationTypeSpec() {
        return ((root, query, cb) -> translationType != null
                ? cb.equal(root.get("translationType"), translationType)
                : null);
    }

    private Specification<VocabTranslation> vocabSourceWordSpec() {
        return ((root, query, cb) -> StringUtils.hasText(vocabSourceWord)
                ? cb.equal(cb.lower(root.get("vocabSource").get("word")), vocabSourceWord.toLowerCase())
                : null);
    }

    private Specification<VocabTranslation> vocabSourceLanguageCodeSpec() {
        return ((root, query, cb) -> vocabSourceLanguageCode != null
                ? cb.equal(root.get("vocabSource").get("languageCode"), vocabSourceLanguageCode)
                : null);
    }

    private Specification<VocabTranslation> vocabTargetLanguageCodeInSpec() {
        return ((root, query, cb) -> vocabTargetLanguageCodeIn != null
                ? root.get("vocabTarget").get("languageCode").in(vocabTargetLanguageCodeIn)
                : null);
    }
}