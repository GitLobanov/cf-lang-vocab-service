package com.conscifora.parser.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Value("${parser.api.ninjas.base-url}")
    private String ninjasBaseUrl;
    @Value("${parser.api.ninjas.header.key-name}")
    private String ninjasApiKeyName;
    @Value("${parser.api.ninjas.header.key-value}")
    private String ninjasApiKey;

    @Value("${parser.api.urban.base-url}")
    private String urbanBaseUrl;
    @Value("${parser.api.urban.header.key-name}")
    private String urbanApiKeyName;
    @Value("${parser.api.urban.header.key-value}")
    private String urbanApiKey;

    @Bean
    public WebClient ninjasClient() {
        return WebClient.builder()
                .baseUrl(ninjasBaseUrl)
                .defaultHeader(ninjasApiKeyName, ninjasApiKey)
                .build();
    }

    @Bean
    public WebClient urbanClient() {
        return WebClient.builder()
                .baseUrl(urbanBaseUrl)
                .defaultHeader(urbanApiKeyName, urbanApiKey)
                .build();
    }

}
