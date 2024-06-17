package com.example.hotmart.adesao.unitary.service;

import com.example.hotmart.adesao.entity.ProgrammingLanguage;
import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.exception.ResourceNotFoundException;
import com.example.hotmart.adesao.payload.request.ProgrammingLanguageRequest;
import com.example.hotmart.adesao.payload.response.ProgrammingLanguageResponse;
import com.example.hotmart.adesao.repository.ProgrammingLanguageRepository;
import com.example.hotmart.adesao.repository.UserRepository;
import com.example.hotmart.adesao.security.UserPrincipal;
import com.example.hotmart.adesao.service.impl.ProgrammingLanguageServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(value = MockitoExtension.class)
public class ProgrammingLanguageServiceUnitaryTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private ProgrammingLanguageRepository repository;

    @InjectMocks
    private ProgrammingLanguageServiceImpl languageService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddProgrammingLanguage() {
        // Dado um usuário existente
        UserPrincipal user = new UserPrincipal(1L, "username", "teste@gmail.com", "teste@123");
        User mockUser = new User();
        mockUser.setId(1L);
        mockUser.setUsername("username");

        when(userRepository.findById(user.getId())).thenReturn(Optional.of(mockUser));

        // Dado uma solicitação de linguagem de programação
        ProgrammingLanguageRequest request = new ProgrammingLanguageRequest();

        ProgrammingLanguageResponse response = languageService.addProgrammigLanguage(request, user);

        // Então deve retornar a resposta com o título da linguagem de programação criada
        assertEquals("Java", response.getTitle());
    }

    @Test
    public void testAddProgrammingLanguage_UserNotFound() {
        // Dado um usuário inexistente
        UserPrincipal user = new UserPrincipal(1L, "username", "teste@gmail.com", "teste@123");
        when(userRepository.findById(user.getId())).thenReturn(Optional.empty());

        // Dado uma solicitação de linguagem de programação
        ProgrammingLanguageRequest request = new ProgrammingLanguageRequest();

        assertThrows(ResourceNotFoundException.class, () -> languageService.addProgrammigLanguage(request, user));
    }

    @Test
    public void testGetProgrammingLanguage() {
        // Dado um ID existente
        Long id = 1L;
        ProgrammingLanguage mockLanguage = new ProgrammingLanguage();
        mockLanguage.setId(id);
        mockLanguage.setTitle("Java");

        when(repository.findById(id)).thenReturn(Optional.of(mockLanguage));

        ProgrammingLanguage result = languageService.getProgrammingLanguage(id);

        // Então deve retornar o objeto ProgrammingLanguage correto
        assertEquals("Java", result.getTitle());
    }

    @Test
    public void testGetProgrammingLanguage_NotFound() {
        // Dado um ID inexistente
        Long id = 1L;
        when(repository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> languageService.getProgrammingLanguage(id));
    }

    @Test
    public void testFindUserByLanguages() {
        // Dado uma lista de usuários esperada
        List<User> expectedUsers = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        expectedUsers.add(user1);

        // Dado uma string de linguagens
        String languages = "Java, Python";

        // Simular o comportamento do repository
        when(languageService.findUserByLanguages(languages)).thenReturn(expectedUsers);

        List<User> actualUsers = languageService.findUserByLanguages(languages);

        // Então a lista retornada deve ser a mesma que a lista esperada
        assertEquals(expectedUsers, actualUsers);
    }

    @Test
    public void testCountUsersByProgrammingLanguages() {
        // Dado uma lista de tuplas de usuário e contagem esperada
        List<User[]> expectedCounts = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        User[] count1 = {user1}; // Por exemplo, 5 usuários para essa linguagem
        expectedCounts.add(count1);

        // Dado uma string de linguagem
        String language = "Java";

        // Simular o comportamento do repository
        when(languageService.countUsersByProgrammingLanguages(language)).thenReturn(expectedCounts);

        List<User[]> actualCounts = languageService.countUsersByProgrammingLanguages(language);

        // Então a lista retornada deve ser a mesma que a lista esperada
        assertEquals(expectedCounts, actualCounts);
    }
}

