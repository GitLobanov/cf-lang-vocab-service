package com.conscifora.parser.service.impl;

import com.conscifora.parser.config.WebClientConfig;
import com.conscifora.parser.config.WebClientConfigTest;
import com.conscifora.parser.domain.NinjasResponseDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.internal.matchers.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@SpringBootTest(classes = WebClientConfigTest.class)
class NinjasParserServiceTest {

    @Autowired
    private NinjasParserService ninjasParserService;

    private static final String TEST_WORD = "code";

    @Test
    @DisplayName("Проверка правильной работы с блокировкой ожидания")
    // Нужно
    void checkingParse() {
        Mono<NinjasResponseDto> monoResponse = ninjasParserService.parse(TEST_WORD);
        NinjasResponseDto ninjasResponseDto = monoResponse.block(Duration.ofSeconds(50));
        assertNotNull(ninjasResponseDto);
    }

    @Test
    @DisplayName("Проверка сохранения в бд")
    void checkingParse_ShouldSaveToDB () {

    }
}