package com.parkly.backend.bizz.login;

import com.parkly.backend.repo.UserRepository;
import com.parkly.backend.repo.domain.UserDTO;
import com.parkly.backend.rest.domain.LoginFormRest;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    @Override
    public Optional<String> loginToSystem(final LoginFormRest loginForm)
    {
        var userOptional = userRepository.findByLogin(loginForm.getLogin());

        if(userOptional.isEmpty())
        {
            log.warn("User {} doesn't exists", loginForm.getLogin());
            return Optional.empty();
        }

        if(!passwordEncoder.matches(loginForm.getPassword(), userOptional.get().getPassword())) {
            log.warn("Wrong password", loginForm.getLogin());
            return Optional.empty();
        }

        return userOptional.map(UserDTO::getSecurityToken);
    }
}
