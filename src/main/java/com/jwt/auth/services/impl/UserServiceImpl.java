package com.jwt.auth.services.impl;

import com.jwt.auth.domain.User;
import com.jwt.auth.services.UserService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {

    public List<User> getUsers() {
        List<User> store = new ArrayList<>();
        store.add(new User(UUID.randomUUID().toString(), "Siddarth", "sba@xyz.com"));
        store.add(new User(UUID.randomUUID().toString(), "Mahesh", "mhs@xyz.com"));
        store.add(new User(UUID.randomUUID().toString(), "Prabhat", "pgp@xyz.com"));
        store.add(new User(UUID.randomUUID().toString(), "Somansh", "smn@xyz.com"));
        return store;
    }

}
