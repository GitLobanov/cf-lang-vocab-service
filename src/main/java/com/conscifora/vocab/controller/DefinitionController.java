package com.conscifora.vocab.controller;

import lombok.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("vocab/")
@RequiredArgsConstructor
public class DefinitionController {

    /**
     * EP-1.3 Получение только определений
     */
    @GetMapping("/definitions")
    public ResponseEntity<List<String>> getDefinitions(
            @RequestParam String langSource,
            @RequestParam String word) {

        return ResponseEntity.ok(null);
    }

}
