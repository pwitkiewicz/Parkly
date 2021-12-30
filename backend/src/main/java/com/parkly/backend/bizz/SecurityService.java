package com.parkly.backend.bizz;

import org.springframework.http.HttpHeaders;

public interface SecurityService {
    boolean isAuthenticated(HttpHeaders headers);
}
