package com.vincent.springboot.webflux.web;
import com.vincent.springboot.webflux.domain.User;
import com.vincent.springboot.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalTime;

/**
 * 基于注释
 * 响应式编程接入crud的demo
 * @author vincent.li
 */
@RestController
@RequestMapping(value = "/user")
public class UserFluxController {

    @Autowired
    private UserHandler userHandler;

    @GetMapping(value = "/{id}")
    public Mono<User> findUserById(@PathVariable("id") Long id) {
        return userHandler.findById(id);
    }

    @GetMapping()
    public Flux<User> findAllUser() {
        return userHandler.findAll();
    }

    @PostMapping()
    public Mono<Long> saveUser(@RequestBody User user) {
        return userHandler.save(user);
    }

    @PutMapping()
    public Mono<Long> modifyUser(@RequestBody User user) {
        return userHandler.modify(user);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Long> deleteUser(@PathVariable("id") Long id) {
        return userHandler.delete(id);
    }

}
