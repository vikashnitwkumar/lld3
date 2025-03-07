
package com.scaler.repositories;

import com.scaler.models.User;
import com.scaler.models.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class InMemoryUserRepository implements UserRepository{
    Map<Long, User> userMap;
    private static long idCounter = 0;
    public InMemoryUserRepository(Map<Long, User> userMap) {
        this.userMap = userMap;
    }

    public InMemoryUserRepository() {
        userMap = new HashMap<>();
    }

    @Override
    public User save(User user) {
        if (user.getId() == 0) user.setId(++idCounter);
        userMap.put(user.getId(), user);
        return user;
    }

    @Override
    public Optional<User> findById(long id) {
        return Optional.ofNullable(userMap.get(id));
    }
}
