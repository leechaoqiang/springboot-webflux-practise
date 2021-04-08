package com.vincent.springboot.webflux.web;
import com.vincent.springboot.webflux.domain.User;
import com.vincent.springboot.webflux.handler.UserHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * 响应式编程接入Redis的demo
 * @author vincent.li
 */
@RestController
@RequestMapping(value = "/user2")
public class UserFluxReactiveController {

    @Autowired
    private ReactiveRedisTemplate reactiveRedisTemplate;

    @GetMapping(value = "/{id}")
    public Mono<User> findUserById(@PathVariable("id") Long id) {
        String key = "uid_" + id;
        ReactiveValueOperations<String, User> operations = reactiveRedisTemplate.opsForValue();
        Mono<User> userMono = operations.get(key);
        return userMono;
    }

    @PostMapping()
    public Mono<Boolean> saveUser(@RequestBody User user) {
        String key = "uid_" + user.getId();
        ReactiveValueOperations<String, User> operations = reactiveRedisTemplate.opsForValue();
        return operations.set(key, user);
    }

    @DeleteMapping(value = "/{id}")
    public Mono<Boolean> deleteUser(@PathVariable("id") Long id) {
        String key = "uid_" + id;
        ReactiveValueOperations<String, User> operations = reactiveRedisTemplate.opsForValue();
        Mono<Boolean> booleanMono = operations.delete(key);
        return booleanMono;
    }
}
