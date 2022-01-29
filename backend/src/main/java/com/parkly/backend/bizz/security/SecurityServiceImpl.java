package com.parkly.backend.bizz.security;

import com.parkly.backend.repo.UserRepository;
import com.parkly.backend.repo.domain.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
public class SecurityServiceImpl implements SecurityService {

    private static final String SECURITY_HEADER = "security-header";

    private final UserRepository userRepository;

    @Autowired
    public SecurityServiceImpl(final UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }

    @Override
    public boolean isAuthenticated(HttpHeaders headers)
    {
        if (Objects.isNull(headers) || !headers.containsKey(SECURITY_HEADER))
        {
            return false;
        }
        return userRepository.findBySecurityToken(headers.getFirst(SECURITY_HEADER)).isPresent();
    }
}
