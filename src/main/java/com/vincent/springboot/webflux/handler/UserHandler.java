package com.vincent.springboot.webflux.handler;

import com.vincent.springboot.webflux.dal.UserDao;
import com.vincent.springboot.webflux.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
public class UserHandler {

    private final UserDao userDao;

    @Autowired
    public UserHandler(UserDao userDao) {
        this.userDao = userDao;
    }

    public Mono<Long> save(User user) {
        user.setCreateTime(new Date());
        return Mono.create(userMonoSink -> userMonoSink.success(userDao.save(user)));
    }

    public Mono<User> findById(Long id) {
        return Mono.justOrEmpty(userDao.findById(id));
    }

    public Flux<User> findAll() {
        return Flux.fromIterable(userDao.findAll());
    }

    public Mono<Long> modify(User user) {
        return Mono.create(userMonoSink -> userMonoSink.success(userDao.update(user)));
    }

    public Mono<Long> delete(Long id) {
        return Mono.create(userMonoSink -> userMonoSink.success(userDao.delete(id)));
    }
}
