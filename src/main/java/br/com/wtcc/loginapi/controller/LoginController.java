package br.com.wtcc.loginapi.controller;

import br.com.wtcc.loginapi.dto.request.RequestLogin;
import br.com.wtcc.loginapi.dto.request.RequestRegister;
import br.com.wtcc.loginapi.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class LoginController {

    @Autowired
    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }
    @PostMapping("/create")
    public ResponseEntity<Object> create(@RequestBody RequestRegister newUser){
        return loginService.createUser(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody RequestLogin login){
        return loginService.validateLogin(login);
    }
}
