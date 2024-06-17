package com.example.hotmart.adesao.unitary.service;

import com.example.hotmart.adesao.entity.ProgrammingLanguage;
import com.example.hotmart.adesao.entity.User;
import com.example.hotmart.adesao.payload.UserIdentityAvailability;
import com.example.hotmart.adesao.repository.UserRepository;
import com.example.hotmart.adesao.service.impl.UserServiceImpl;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(value = MockitoExtension.class)
public final class UserServiceUnitaryTest {
    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAddUser() {
        // Configurar o usuário de teste
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("test@example.com");
        user.setPassword("testpassword");

        // Configurar o comportamento do repositório simulado
        when(userRepository.existsByUsername(user.getUsername())).thenReturn(true);
        when(userRepository.existsByEmail(user.getEmail())).thenReturn(true);

        // Configurar o comportamento do encoder simulado
        when(passwordEncoder.encode(user.getPassword())).thenReturn("hashedPassword");

        // Chamar o método a ser testado
        User result = userService.addUser(user);

        // Verificar se o método retornou o usuário esperado
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("test@example.com", result.getEmail());
        assertEquals("hashedPassword", result.getPassword());

        // Verificar se o método save foi chamado no repositório
        verify(userRepository, times(1)).save(user);
    }

    @Test
    public void testGetUser() {
        // Configurar o usuário de teste
        String username = "testuser";
        User user = new User();
        user.setId(1L);
        user.setName("Test User");
        user.setUsername(username);
        user.setPassword("teste@123");
        user.setEmail("test@example.com");
        user.setBirthday("1990-01-01");
        user.setDocument("123456789");
        List<ProgrammingLanguage> languages = new ArrayList<>();
        user.setLanguages(languages);
        user.setPhoneNumber("1234567890");

        // Configurar o comportamento do repositório simulado
        when(userRepository.getUserByName(username)).thenReturn(user);

        // Chamar o método a ser testado
        User userProfile = userService.getUser(username);

        // Verificar se o perfil do usuário retornado está correto
        assertNotNull(userProfile);
        assertEquals(user.getId(), userProfile.getId());
        assertEquals(user.getName(), userProfile.getName());
        assertEquals(user.getUsername(), userProfile.getUsername());
        assertEquals(user.getEmail(), userProfile.getEmail());
        assertEquals(user.getBirthday(), userProfile.getBirthday());
        assertEquals(user.getDocument(), userProfile.getDocument());
        assertEquals(user.getLanguages(), userProfile.getLanguages());
        assertEquals(user.getPhoneNumber(), userProfile.getPhoneNumber());
    }

    @Test
    public void testCheckUsernameAvailability() {
        // Dado um nome de usuário que não existe no repositório
        String username = "username";
        when(userRepository.existsByUsername(username)).thenReturn(false);

        // Quando chamamos o método checkUsernameAvailability
        UserIdentityAvailability availability = userService.checkUsernameAvailability(username);

        // Então o resultado deve ser que o nome de usuário está disponível
        assertEquals(true, availability.getAvailable());
    }

    @Test
    public void testCheckUsernameAvailability_UsernameExists() {
        // Dado um nome de usuário que já existe no repositório
        String username = "existingUsername";
        when(userRepository.existsByUsername(username)).thenReturn(true);

        // Quando chamamos o método checkUsernameAvailability
        UserIdentityAvailability availability = userService.checkUsernameAvailability(username);

        // Então o resultado deve ser que o nome de usuário não está disponível
        assertEquals(false, availability.getAvailable());
    }
}
