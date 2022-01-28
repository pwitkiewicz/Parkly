package com.parkly.backend.bizz.login;

import com.parkly.backend.rest.domain.LoginFormRest;
import java.util.Optional;

public interface LoginService {

    Optional<String> loginToSystem(LoginFormRest loginForm);
}
