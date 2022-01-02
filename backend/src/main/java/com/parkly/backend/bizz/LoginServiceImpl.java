package com.parkly.backend.bizz;

import com.parkly.backend.repo.UserRepository;
import com.parkly.backend.repo.domain.UserDTO;
import com.parkly.backend.rest.domain.LoginFormRest;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    private final UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<String> loginToSystem(final LoginFormRest loginForm) {
        var userOptional = userRepository.findByLoginAndPassword(loginForm.getLogin(), loginForm.getPassword());

        return userOptional.map(UserDTO::getSecurityToken);
    }
}
