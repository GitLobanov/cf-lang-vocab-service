package com.conscifora.vocab.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("vocab-service/")
@RequiredArgsConstructor
public class AntonymContoller {

    /**
     * EP-1.5 Получение антонимов
     */
    @GetMapping("/antonyms/{lang}/{word}")
    public ResponseEntity<List<String>> getAntonyms(
            @PathVariable String lang,
            @PathVariable String word) {

        return ResponseEntity.ok(null);
    }

}
