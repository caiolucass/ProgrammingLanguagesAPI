package com.example.hotmart.adesao.service;

import com.example.hotmart.adesao.entity.ProgrammingLanguage;
import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.payload.request.ProgrammingLanguageRequest;
import com.example.hotmart.adesao.payload.response.ProgrammingLanguageResponse;
import com.example.hotmart.adesao.security.UserPrincipal;

import java.util.List;

public interface ProgrammingLanguageService {

    ProgrammingLanguageResponse addProgrammigLanguage(ProgrammingLanguageRequest request, UserPrincipal user);

    ProgrammingLanguage getProgrammingLanguage(Long id);

    List<User> findUserByLanguages(String language);

    List<User[]> countUsersByProgrammingLanguages(String language);
}
