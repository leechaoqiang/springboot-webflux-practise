package com.vincent.springboot.webflux.router;

import com.vincent.springboot.webflux.handler.HelloHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class HelloRouter {

    @Bean
    public RouterFunction<ServerResponse> routeHello(HelloHandler helloHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/v1/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), helloHandler::hello);
    }

    @Bean
    public RouterFunction<ServerResponse> routeHelloV2(HelloHandler helloHandler) {
        return RouterFunctions.route(RequestPredicates.GET("/v2/hello")
                .and(RequestPredicates.accept(MediaType.TEXT_PLAIN)), helloHandler::helloV2);
    }
}
