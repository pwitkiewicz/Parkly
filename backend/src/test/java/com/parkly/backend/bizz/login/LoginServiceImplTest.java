package com.parkly.backend.bizz.login;

import com.parkly.backend.repo.UserRepository;
import com.parkly.backend.repo.domain.UserDTO;
import com.parkly.backend.rest.domain.LoginFormRest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class LoginServiceImplTest {

    final static LoginFormRest LOGIN_FORM_REST = new LoginFormRest();
    final static UserDTO USER_DTO = new UserDTO();

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private LoginServiceImpl loginService;

    @Test
    void loginToSystem() {
        // given
        USER_DTO.setLogin("Kocham");
        USER_DTO.setPassword("$2a$10$jn7Fjq8AE/eEDz884PHYVuqfSuBw.XOnYH7Fj/zCS4UjkWYkP/6hy");
        USER_DTO.setSecurityToken("PIWO-PIWO");

        lenient().when(userRepository.findByLogin(any())).thenReturn(Optional.of(USER_DTO));

        LOGIN_FORM_REST.setLogin("Kocham");
        LOGIN_FORM_REST.setPassword("Piwo");

        var token = loginService.loginToSystem(LOGIN_FORM_REST);

        assertThat(token).contains("PIWO-PIWO");
    }
}