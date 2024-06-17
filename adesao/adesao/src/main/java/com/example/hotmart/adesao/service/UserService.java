package com.example.hotmart.adesao.service;

import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.payload.UserIdentityAvailability;

public interface UserService {

    User addUser(User user);

    User getUser(String username);

    UserIdentityAvailability checkUsernameAvailability(String username);
}
