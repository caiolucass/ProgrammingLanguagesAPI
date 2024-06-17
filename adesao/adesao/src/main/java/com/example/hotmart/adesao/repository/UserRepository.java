package com.example.hotmart.adesao.repository;

import com.example.hotmart.adesao.exception.ResourceNotFoundException;
import com.example.hotmart.adesao.entity.User;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(@NotBlank String username);

    Boolean existsByEmail(@NotBlank String email);

    Boolean existsByUsername(@NotBlank String userName);

    Optional<User> findByUsernameOrEmail(String userName, String email);

    default User getUserByName(String username) {
        return findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User", "userName", username));
    }
}
