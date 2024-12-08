package com.conscifora.vocab.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("vocab-service/")
@RequiredArgsConstructor
public class SynonymController {

    /**
     * EP-1.4 Получение только синонимов
     */
    @GetMapping("/synonyms/{lang}/{word}")
    public ResponseEntity<List<String>> getSynonyms(
            @PathVariable String lang,
            @PathVariable String word) {

        return ResponseEntity.ok(null);
    }

}
