package com.conscifora.vocab.service;

import com.conscifora.vocab.mapper.VocabTranslationMapper;
import com.conscifora.vocab.repository.VocabTranslationRepository;
import com.conscifora.vocab.service.Impl.VocabServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class VocabServiceImplTest {
    @Mock
    VocabTranslationRepository vocabTranslationRepository;
    @Mock
    VocabTranslationMapper vocabTranslationMapper;
    @InjectMocks
    VocabServiceImpl vocabWordServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindTranslations() {

    }
}

