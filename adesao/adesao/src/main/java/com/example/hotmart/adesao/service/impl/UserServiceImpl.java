package com.example.hotmart.adesao.service.impl;

import com.example.hotmart.adesao.exception.BadRequestException;
import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.payload.UserIdentityAvailability;
import com.example.hotmart.adesao.payload.response.ApiResponse;
import com.example.hotmart.adesao.repository.UserRepository;
import com.example.hotmart.adesao.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public User addUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Nome de usuário ja cadastrado.");
            throw new BadRequestException(apiResponse);
        }

        if (userRepository.existsByEmail(user.getEmail())) {
            ApiResponse apiResponse = new ApiResponse(Boolean.FALSE, "Email já cadastrado.");
            throw new BadRequestException(apiResponse);
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        User user = userRepository.getUserByName(username);
        return new User(user.getId(), user.getName(), user.getUsername(), user.getPassword(), user.getBirthday(), user.getDocument(),
                user.getEmail(), user.getPhoneNumber(), user.getLanguages());
    }

    @Override
    public UserIdentityAvailability checkUsernameAvailability(String username) {
        Boolean isAvailable = !userRepository.existsByUsername(username);
        return new UserIdentityAvailability(isAvailable);
    }
}
