package com.conscifora.vocab.controller;

import com.conscifora.vocab.dto.request.VocabRequestDto;
import com.conscifora.vocab.dto.response.VocabResponseDto;
import com.conscifora.vocab.service.VocabWordService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springdoc.api.ErrorMessage;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vocab-service")
@RequiredArgsConstructor
public class VocabController {

    private final VocabWordService vocabWordService;

    /**
     * EP-1 Получение полной информации по слову
     * <a href="https://wiki.yandex.ru/svapi/api/ep-1/">Doc</a>
     */
    @PostMapping("/word")
    @Operation(summary = "EP-1: Full info for word", tags = "vocab",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = VocabResponseDto.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    public ResponseEntity<VocabResponseDto> getVocabInfo(@RequestBody VocabRequestDto vocabRequestDto) {
        // TODO WHEN WE CAN'T FIND VOCAB IN RESPONSE EMPTY DTO, NEED EXCEPTION MESSAGE
        VocabResponseDto response =
                vocabWordService.findTranslations(vocabRequestDto)
                        .orElseThrow(() -> new RuntimeException("Vocab not found"));

        return ResponseEntity.ok(response);
    }

}
