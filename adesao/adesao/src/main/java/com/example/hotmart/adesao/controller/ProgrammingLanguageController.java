package com.example.hotmart.adesao.controller;

import com.example.hotmart.adesao.entity.ProgrammingLanguage;
import com.example.hotmart.adesao.payload.request.ProgrammingLanguageRequest;
import com.example.hotmart.adesao.payload.response.ProgrammingLanguageResponse;
import com.example.hotmart.adesao.security.UserPrincipal;
import com.example.hotmart.adesao.service.ProgrammingLanguageService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/programmingLanguages")
public class ProgrammingLanguageController {

    private final ProgrammingLanguageService languageService;

    @PostMapping("/addLanguages")
    public ResponseEntity<ProgrammingLanguageResponse> addPost(@Valid @RequestBody ProgrammingLanguageRequest postRequest, UserPrincipal currentUser) {
        ProgrammingLanguageResponse postResponse = languageService.addProgrammigLanguage(postRequest, currentUser);
        return new ResponseEntity< >(postResponse, HttpStatus.CREATED);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ProgrammingLanguage> getPost(@PathVariable(name = "id") Long id) {
        ProgrammingLanguage language = languageService.getProgrammingLanguage(id);
        return new ResponseEntity< >(language, HttpStatus.OK);
    }
}
