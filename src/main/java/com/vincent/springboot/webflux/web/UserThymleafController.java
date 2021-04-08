package com.vincent.springboot.webflux.web;

import com.vincent.springboot.webflux.domain.User;
import com.vincent.springboot.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

/**
 * WebFlux集成Thymeleaf的demo
 * @author vincent.li
 */
@Controller
@RequestMapping("/user")
public class UserThymleafController {

    @Autowired
    private UserHandler userHandler;

    @GetMapping("/hello")
    public Mono<String> hello(final Model model) {
        model.addAttribute("name", "vincent.Li");
        model.addAttribute("curTime", LocalTime.now().toString());

        String path = "hello";
        return Mono.create(monoSink -> monoSink.success(path));
    }

    @GetMapping("/list")
    public String listUsers(final Model model) {
        final Flux<User> userFluxList = userHandler.findAll();
        model.addAttribute("userList", userFluxList);
        String path = "userList";
        return path;
    }
}
