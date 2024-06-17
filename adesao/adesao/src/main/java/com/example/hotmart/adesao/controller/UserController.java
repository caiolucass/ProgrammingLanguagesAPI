package com.example.hotmart.adesao.controller;

import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.payload.UserIdentityAvailability;
import com.example.hotmart.adesao.service.ProgrammingLanguageService;
import com.example.hotmart.adesao.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    private final ProgrammingLanguageService languageService;

    @PostMapping("/addUser")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUSer(@PathVariable(value = "username") String username) {
        User userProfile = userService.getUser(username);
        return new ResponseEntity< >(userProfile, HttpStatus.OK);
    }

    @GetMapping("/{username}/language")
    public ResponseEntity<List<User>> getUserByLanguage(@PathVariable(value = "username") String username){
        List<User> response = languageService.findUserByLanguages(username);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/{language}")
    public ResponseEntity<List<User[]>> countUsersByProgrammingLanguages(@PathVariable(value = "languagem") String language){
        List<User[]> response = languageService.countUsersByProgrammingLanguages(language);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/checkUsernameAvailability")
    public ResponseEntity<UserIdentityAvailability> checkUsernameAvailability(@RequestParam(value = "username") String username) {
        UserIdentityAvailability userIdentityAvailability = userService.checkUsernameAvailability(username);
        return new ResponseEntity<>(userIdentityAvailability, HttpStatus.OK);
    }
}
