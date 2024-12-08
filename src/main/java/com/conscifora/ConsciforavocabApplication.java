package com.conscifora;

import com.conscifora.token.service.CVTokenService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@Slf4j
public class ConsciforavocabApplication {

    private static CVTokenService cvTokenService;

    public ConsciforavocabApplication(CVTokenService cvTokenService) {
        ConsciforavocabApplication.cvTokenService = cvTokenService;
    }

    public static void main(String[] args) {
        SpringApplication.run(ConsciforavocabApplication.class, args);
    }

}
