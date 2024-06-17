package com.example.hotmart.adesao.service.impl;

import com.example.hotmart.adesao.exception.ResourceNotFoundException;
import com.example.hotmart.adesao.entity.ProgrammingLanguage;
import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.payload.request.ProgrammingLanguageRequest;
import com.example.hotmart.adesao.payload.response.ProgrammingLanguageResponse;
import com.example.hotmart.adesao.repository.ProgrammingLanguageRepository;
import com.example.hotmart.adesao.repository.UserRepository;
import com.example.hotmart.adesao.security.UserPrincipal;
import com.example.hotmart.adesao.service.ProgrammingLanguageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.example.hotmart.adesao.utils.AppConstants.*;

@Service
@RequiredArgsConstructor
public class ProgrammingLanguageServiceImpl implements ProgrammingLanguageService {
    private final ProgrammingLanguageRepository languageRepository;

    private final UserRepository userRepository;

    @Override
    public ProgrammingLanguageResponse addProgrammigLanguage(ProgrammingLanguageRequest reques, UserPrincipal user) {
        User user1 = userRepository.findById(user.getId())
                .orElseThrow(() -> new ResourceNotFoundException(USER, ID, 1L));

        ProgrammingLanguage language = new ProgrammingLanguage();
        language.setTitle(language.getTitle());
        user1.setUsername(user.getUsername());

        ProgrammingLanguage language1 = languageRepository.save(language);
        ProgrammingLanguageResponse languageResponse = new ProgrammingLanguageResponse();

        languageResponse.setTitle(language1.getTitle());
        return languageResponse;
    }

    @Override
    public ProgrammingLanguage getProgrammingLanguage(Long id) {
        return languageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException(PROGRAMMING_LANGUAGE, ID, id));
    }

    /**
     * @param languages
     * @return Filtra por linguagens de programação específicas.
     */
    @Override
    public List<User> findUserByLanguages(String languages){
        return languageRepository.findUserByLanguages(languages);
    }

    /**
     * @return Retorna o número de usuários cadastrados por linguagem de programação.
     */
    @Override
    public List<User[]> countUsersByProgrammingLanguages(String language) {
        return languageRepository.countUsersByProgrammingLanguages(language);
    }
}
