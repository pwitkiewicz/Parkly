package com.parkly.backend.rest;

import static com.parkly.backend.utils.LogWriter.logHeaders;

import com.parkly.backend.bizz.login.LoginService;
import com.parkly.backend.rest.domain.LoginFormRest;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping(path = "/login")
public class LoginController {

    private final LoginService loginService;

    @Autowired
    public LoginController(final LoginService loginService)
    {
        this.loginService = loginService;
    }

    @PostMapping
    @ApiOperation(value = "Login endpoint", notes = "Endpoint serving login functionality.")
    public ResponseEntity<String> login(@RequestHeader HttpHeaders headers,
                                        @ApiParam(value = "With user name and unencrypted password.")
                                        @RequestBody LoginFormRest loginForm)
    {
        logHeaders(headers);

        final Optional<String> tokenOptional = loginService.loginToSystem(loginForm);
        return tokenOptional.map(s -> ResponseEntity.ok(JSONObject.quote(s)))
            .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED).build());
    }
}