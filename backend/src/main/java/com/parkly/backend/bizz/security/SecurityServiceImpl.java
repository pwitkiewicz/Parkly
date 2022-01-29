package com.parkly.backend.bizz.security;

import com.parkly.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final String SECURITY_HEADER = "security-header";

    private final UserRepository userRepository;

    @Autowired
    public SecurityServiceImpl(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isAuthenticated(HttpHeaders headers) {
        if (headers == null) {
            return false;
        }

        if(headers.containsKey(SECURITY_HEADER)) {
            var users = userRepository.findAll();

            for(var user : users) {
                if(user.getSecurityToken().equals(headers.getFirst(SECURITY_HEADER))) {
                    return true;
                }
            }
        }

        return false;
    }
}
