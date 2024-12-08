package com.conscifora.parser.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public interface ParserService <T> {


    Mono<T> parse(String word);

}
