package com.conscifora.vocab.controller;

import com.conscifora.vocab.domain.dto.request.VocabCreationRequestDto;
import com.conscifora.vocab.domain.dto.request.VocabRequestDto;
import com.conscifora.vocab.domain.dto.response.ErrorMessage;
import com.conscifora.vocab.domain.dto.response.SuccessMessage;
import com.conscifora.vocab.domain.dto.response.VocabResponseDto;
import com.conscifora.vocab.service.VocabService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vocab-service")
@RequiredArgsConstructor
public class VocabController {

    private final VocabService vocabService;

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
                vocabService.findTranslations(vocabRequestDto)
                        .orElseThrow(() -> new RuntimeException("Vocab not found"));

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "EP1.1: Create vocab translation", tags = "vocab",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Success",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = SuccessMessage.class))),
                    @ApiResponse(responseCode = "400", description = "Bad request",
                            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PostMapping("/create")
    public ResponseEntity<String> createVocab (@RequestBody VocabCreationRequestDto vocabCreationRequestDto) {
        vocabService.createVocabTranslation(vocabCreationRequestDto);
        return ResponseEntity.ok("Data is saved");
    }

}
