package com.conscifora.vocab.controller;

import com.conscifora.vocab.dto.VocabDTO;
import com.conscifora.vocab.dto.request.VocabWordRequestDto;
import com.conscifora.vocab.dto.response.VocabWordResponseDto;
import com.conscifora.vocab.service.Impl.VocabWordServiceImpl;
import com.conscifora.vocab.service.VocabWordService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("svapi/api/v2/vocab/")
@RequiredArgsConstructor
public class VocabController {

    private final VocabWordService vocabWordService;

    /**
     * EP-1.1 Получение полной информации по слову
     */
    @GetMapping("/word")
    public ResponseEntity<VocabWordResponseDto> getVocabInfo(
            @RequestBody VocabWordRequestDto vocabWordRequestDto) {

        // TODO CUSTOM EXCEPTION
        if (vocabWordRequestDto == null) {
            return ResponseEntity.badRequest().build();
        }

        // TODO какой-то сервис, откуда мы берем по нашему запросы данные
        Optional<VocabWordResponseDto> optResponse =
                Optional.ofNullable(vocabWordService.findTranslations(vocabWordRequestDto));
        VocabWordResponseDto vocabWordResponseDto = optResponse.orElseThrow();

        return ResponseEntity.ok(vocabWordResponseDto);
    }


    /**
     * EP-1.2 Поиск слова среди примеров
     */
    @GetMapping("/examples")
    public ResponseEntity<List<String>> searchWordInExamples(
            @RequestParam String langSource,
            @RequestParam String word) {

        // Логика для поиска слова в примерах
        List<String> examples = searchInExamples(langSource, word);
        return ResponseEntity.ok(examples);
    }

    /**
     * EP-1.3 Получение только определений
     */
    @GetMapping("/definitions")
    public ResponseEntity<List<String>> getDefinitions(
            @RequestParam String langSource,
            @RequestParam String word) {

        // Логика для получения определений
        List<String> definitions = getWordDefinitions(langSource, word);
        return ResponseEntity.ok(definitions);
    }

    /**
     * EP-1.4 Получение только определений
     */
    @GetMapping("/synonyms/{lang}/{word}")
    public ResponseEntity<List<String>> getSynonyms(
            @PathVariable String lang,
            @PathVariable String word) {

        // Логика для получения синонимов
        List<String> synonyms = getWordSynonyms(lang, word);
        return ResponseEntity.ok(synonyms);
    }

    /**
     * EP-1.5 Получение антонимов
     */
    @GetMapping("/antonyms/{lang}/{word}")
    public ResponseEntity<List<String>> getAntonyms(
            @PathVariable String lang,
            @PathVariable String word) {

        // Логика для получения антонимов
        List<String> antonyms = getWordAntonyms(lang, word);
        return ResponseEntity.ok(antonyms);
    }

    /**
     * EP-1.6 Получение слэнга
     */
    @GetMapping("/slangs/{lang}/{word}")
    public ResponseEntity<List<String>> getSlangs(
            @PathVariable String lang,
            @PathVariable String word) {

        // Логика для получения слэнга
        List<String> slangs = getWordSlangs(lang, word);
        return ResponseEntity.ok(slangs);
    }

    // Вспомогательные методы для выполнения логики
    private VocabDTO getFullVocabInfo(String langSource, String word, String langTarget) {
        // Твоя логика для получения полной информации о слове
        return null;
    }

    private VocabDTO getFullVocabInfo(String langSource, String word, List<String> langTarget) {
        // Логика для обработки нескольких языков
        return null;
    }

    private List<String> searchInExamples(String langSource, String word) {
        // Логика для поиска в примерах
        return null;
    }

    private List<String> getWordDefinitions(String langSource, String word) {
        // Логика для получения определений
        return null;
    }

    private List<String> getWordSynonyms(String lang, String word) {
        // Логика для получения синонимов
        return null;
    }

    private List<String> getWordAntonyms(String lang, String word) {
        // Логика для получения антонимов
        return null;
    }

    private List<String> getWordSlangs(String lang, String word) {
        // Логика для получения слэнга
        return null;
    }

}
