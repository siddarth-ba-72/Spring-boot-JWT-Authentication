package com.jwt.auth.services;

import com.jwt.auth.domain.User;

import java.util.List;

public interface UserService {

    List<User> getUsers();

}
