package com.conscifora.vocab.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("vocab-service/")
@RequiredArgsConstructor
public class SlangController {

    /**
     * EP-1.6 Получение слэнга
     */
    @GetMapping("/slangs/{lang}/{word}")
    public ResponseEntity<List<String>> getSlangs(
            @PathVariable String lang,
            @PathVariable String word) {

        return ResponseEntity.ok(null);
    }

}
