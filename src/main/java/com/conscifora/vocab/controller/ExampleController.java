package com.conscifora.vocab.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("vocab/")
@RequiredArgsConstructor
public class ExampleController {

    /**
     * EP-2.1 Поиск слова среди примеров
     */
    @GetMapping("/examples")
    public ResponseEntity<List<String>> searchWordInExamples(
            @RequestParam String langSource,
            @RequestParam String word) {

        return ResponseEntity.ok(null);
    }

}
