package com.vincent.springboot.webflux.dal;

import com.vincent.springboot.webflux.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class UserDao {

    private ConcurrentMap<Long, User> repository = new ConcurrentHashMap<>();

    private static final AtomicLong idGenerator = new AtomicLong(0);

    public Long save(User user) {
        Long id = idGenerator.incrementAndGet();
        user.setId(id);
        repository.put(id, user);
        return id;
    }

    public Collection<User> findAll() {
        return repository.values();
    }


    public User findById(Long id) {
        return repository.get(id);
    }

    public Long update(User user) {
        repository.put(user.getId(), user);
        return user.getId();
    }

    public Long delete(Long id) {
        repository.remove(id);
        return id;
    }
}