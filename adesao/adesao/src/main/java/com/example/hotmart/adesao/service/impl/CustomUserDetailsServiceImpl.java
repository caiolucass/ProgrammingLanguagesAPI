package com.example.hotmart.adesao.service.impl;

import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.repository.UserRepository;
import com.example.hotmart.adesao.security.UserPrincipal;
import com.example.hotmart.adesao.service.CustomUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserPrincipal loadUserByUsername(String usernameOrEmail) {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("Usuario não encontrado com o userName ou email: %s", usernameOrEmail)));
        return UserPrincipal.create(user);
    }

    @Override
    @Transactional
    public UserDetails loadUserById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(String.format("Usuario nao encontrado com id: %s", id)));
        return UserPrincipal.create(user);
    }
}
