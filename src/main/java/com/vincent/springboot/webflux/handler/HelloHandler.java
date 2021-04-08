package com.vincent.springboot.webflux.handler;

import com.vincent.springboot.webflux.exception.GlobalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.Optional;

/**
 * @author vincent.li
 */
@Component
public class HelloHandler {

    public Mono<ServerResponse> hello(ServerRequest request) {
        return ServerResponse.ok().contentType(MediaType.TEXT_PLAIN)
                .body(BodyInserters.fromObject("Hello, webFlux!"));
    }

    public Mono<ServerResponse> helloV2(ServerRequest request) {
        return ServerResponse.ok().body(sayHello(request), String.class);
    }

    private Mono<String> sayHello(ServerRequest request) {
        Optional<String> optional = request.queryParam("name");
        if (!optional.isPresent()) {
            throw new GlobalException(HttpStatus.INTERNAL_SERVER_ERROR, "request param name not exists");
        }

        return Mono.just("Hello," + optional.get());
    }
}
