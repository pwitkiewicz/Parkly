package com.parkly.backend.bizz;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {

    // TODO

    @Override
    public boolean isAuthenticated(final HttpHeaders headers) {
        return true;
    }
}
