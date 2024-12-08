package com.conscifora.vocab.domain.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Builder;

import java.util.Map;

@Builder
public record DefinitionRequestDto(

        @Schema(description = "Definitions of various vocabulary words", example = "{\"anopheles\":\"A genus of mosquitoes...\", \"uniclinal\":\"See Nonoclinal.\"}")
        Map<String, String> definitions
) {
}
