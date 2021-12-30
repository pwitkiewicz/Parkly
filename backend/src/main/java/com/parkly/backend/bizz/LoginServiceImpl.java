package com.parkly.backend.bizz;

import com.parkly.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService{

    private UserRepository userRepository;

    @Autowired
    public LoginServiceImpl(final UserRepository userRepository)
    {
        this.userRepository=userRepository;
    }

    @Override
    public String loginToSystem(String login, String password) {
        return null;
    }
}
