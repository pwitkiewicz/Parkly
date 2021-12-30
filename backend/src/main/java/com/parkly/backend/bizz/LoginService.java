package com.parkly.backend.bizz;

import com.parkly.backend.rest.domain.LoginFormRest;
import java.util.Optional;

public interface LoginService {

    Optional<String> loginToSystem(LoginFormRest loginForm);
}
