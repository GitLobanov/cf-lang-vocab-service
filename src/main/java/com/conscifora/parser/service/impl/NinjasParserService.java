package com.conscifora.parser.service.impl;

import com.conscifora.parser.domain.NinjasResponseDto;
import com.conscifora.parser.service.ParserService;
import com.conscifora.vocab.domain.constant.LanguageCode;
import com.conscifora.vocab.domain.constant.TranslationType;
import com.conscifora.vocab.domain.entity.Vocab;
import com.conscifora.vocab.domain.entity.VocabDefinitions;
import com.conscifora.vocab.domain.entity.VocabTranslation;
import com.conscifora.vocab.repository.VocabDefinitionsRepository;
import com.conscifora.vocab.repository.VocabRepository;
import com.conscifora.vocab.repository.VocabTranslationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

/**
 * Синонимы и антонимы более менее пойдут, но определения хромают
 */
@Service
@Slf4j
public class NinjasParserService implements ParserService<NinjasResponseDto> {

    private final WebClient webClient;
    private final VocabDefinitionsRepository vocabDefinitionsRepository;
    private final VocabRepository vocabRepository;
    private final VocabTranslationRepository vocabTranslationRepository;

    public NinjasParserService(@Qualifier("ninjasClient") WebClient webClient,
                               VocabDefinitionsRepository vocabDefinitionsRepository,
                               VocabRepository vocabRepository, VocabTranslationRepository vocabTranslationRepository) {
        this.webClient = webClient;
        this.vocabDefinitionsRepository = vocabDefinitionsRepository;
        this.vocabRepository = vocabRepository;
        this.vocabTranslationRepository = vocabTranslationRepository;
    }

    @Value("${parser.api.ninjas.endpoints.thesaurus}")
    private String thesaurusUrl;
    @Value("${parser.api.ninjas.endpoints.dictionary}")
    private String definitionUrl;

    // TODO NEED LangCode FOR Vocab save to DB
    @Override
    public Mono<NinjasResponseDto> parse(String word) {
        LanguageCode languageCode = LanguageCode.EN;
        Mono<NinjasResponseDto> thesaurusResponse = fetchResponse(thesaurusUrl, word);
        Mono<NinjasResponseDto> definitionResponse = fetchResponse(definitionUrl, word);

        return Mono.zip(
                thesaurusResponse.onErrorResume(e -> {
                    log.warn("Thesaurus API failed", e);
                    return Mono.empty();
                }),
                definitionResponse.onErrorResume(e -> {
                    log.warn("Definition API failed", e);
                    return Mono.empty();
                })
        ).map(tuple -> {
            NinjasResponseDto thesaurus = tuple.getT1();
            NinjasResponseDto definition = tuple.getT2();
            return NinjasResponseDto.builder()
                    .word(word)
                    .antonyms(thesaurus.getAntonyms())
                    .synonyms(thesaurus.getSynonyms())
                    .definition(definition.getDefinition())
                    .build();
        }).publishOn(Schedulers.boundedElastic()).doOnNext((result) -> {
            // TODO REFACTOR OT OTHER CLASS AND METHODS

            vocabDefinitionsRepository.save(
                    VocabDefinitions.builder()
                            .definition(result.getDefinition())
                            .build()
            );

            // TODO WHAT FUCK DUDE, WHAT HELL IS IT?
            Vocab sourceVocab = vocabRepository.findByWord(word).
                    orElse(vocabRepository.save(Vocab.builder()
                            .languageCode(languageCode)
                            .word(word)
                            .build()));

            result.getAntonyms().stream()
                    .map((antonym) -> vocabRepository.findByWord(antonym)
                            .orElse(vocabRepository.save(Vocab.builder()
                                    .languageCode(languageCode)
                                    .word(antonym)
                                    .build())))
                    .forEach((antonym) -> vocabTranslationRepository.save(
                                VocabTranslation.builder()
                                        .vocabSource(sourceVocab)
                                        .vocabTarget(antonym)
                                        .translationType(TranslationType.ANTONYM)
                                        .build()
                            )
                    );
            log.info("Antonyms is saved, count: {}", result.getAntonyms().size());

            result.getSynonyms().stream()
                    .map((synonym) -> vocabRepository.findByWord(synonym).
                            orElse(vocabRepository.save(Vocab.builder()
                                    .languageCode(languageCode)
                                    .word(synonym)
                                    .build())))
                    .forEach((synonym) -> vocabTranslationRepository.save(
                                    VocabTranslation.builder()
                                            .vocabSource(sourceVocab)
                                            .vocabTarget(synonym)
                                            .translationType(TranslationType.SYNONYM)
                                            .build()
                            )
                    );
            log.info("Synonyms is saved, count: {}", result.getSynonyms().size());
        });
    }

    /**
     * Создание запроса по переданному эндпоинту и слову
     * @param endpointUrl
     * @param word
     * @return
     */
    private Mono<NinjasResponseDto> fetchResponse(String endpointUrl, String word) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path(endpointUrl).queryParam("word", word).build())
                .retrieve()
                .bodyToMono(NinjasResponseDto.class);
    }
}

