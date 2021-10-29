package br.com.wtcc.loginapi.service;

import br.com.wtcc.loginapi.dto.request.RequestLogin;
import br.com.wtcc.loginapi.dto.request.RequestRegister;
import br.com.wtcc.loginapi.dto.response.ResponseError;
import br.com.wtcc.loginapi.dto.response.ResponseSuccess;
import br.com.wtcc.loginapi.model.LoginModel;
import br.com.wtcc.loginapi.repository.LoginRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LoginService {
    @Value("${salt}")
    private Integer salt;

    @Value("${hash.pass}")
    private String pass;

    private final LoginRepository loginRepository;

    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }

    public ResponseEntity<Object> validateLogin(RequestLogin login){
        ResponseSuccess success = new ResponseSuccess();
        ResponseError error = new ResponseError();
        try {
            Optional<LoginModel> user = loginRepository.findByUserName(login.getUser());
            if(!user.isPresent()){
                error.setStatus(404);
                error.setMsg("Usuário ou senha não encontrado.");
                error.setError("error");
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }else if(BCrypt.checkpw(login.getPassword(),user.get().getUserPassword())){
                success.setStatus(200);
                success.setMsg("ACESSO PERMITIDO");
                success.setAcess(user.get().getPermission());
                List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                        .commaSeparatedStringToAuthorityList("ROLE_USER");
                String token = Jwts
                        .builder().setId("loginJWT")
                        .setSubject(login.getUser())
                        .claim("authorities",
                                grantedAuthorities.stream()
                                        .map(GrantedAuthority::getAuthority).collect(Collectors.toList()))
                        .setIssuedAt(new Date(System.currentTimeMillis()))
                        .setExpiration(new Date(System.currentTimeMillis() + 28800000))
                        .signWith(SignatureAlgorithm.ES512, pass.getBytes()).compact();
                success.setToken("Bearer " + token);

                return new ResponseEntity<>(success, HttpStatus.OK);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }

    public ResponseEntity<Object> createUser(RequestRegister newUser){
        ResponseSuccess success = new ResponseSuccess();
        ResponseError error = new ResponseError();
        try{
            if(newUser.getUserEmail().isEmpty() || newUser.getUserCpf().isEmpty() || newUser.getUserName().isEmpty()){
                Optional<LoginModel> validateEmail = loginRepository.findByUserEmail(newUser.getUserEmail());
                Optional<LoginModel> validateName = loginRepository.findByUserName(newUser.getUserName());
                Optional<LoginModel> validadeCpf = loginRepository.findByUserCpf(newUser.getUserCpf());
                if(!validateEmail.isPresent() || !validateName.isPresent() || validadeCpf.isPresent()){
                    String pw = BCrypt.hashpw(newUser.getUserPassword(), BCrypt.gensalt(salt));
                    Date now = new Date();

                    LoginModel user = new LoginModel();

                    user.setUserName(newUser.getUserName());
                    user.setUserEmail(newUser.getUserEmail());
                    user.setUserCpf(newUser.getUserCpf());
                    user.setUserPassword(pw);
                    user.setFirstName(newUser.getFirstName());
                    user.setLastName(newUser.getLastName());
                    user.setDateCreated(now);
                    user.setPermission(newUser.getUserPermission());

                    loginRepository.save(user);
                    success.setStatus(201);
                    success.setMsg("USUÁRIO CRIADO COM SUCESSO!");
                    success.setRenovar(true);

                    return new ResponseEntity<>(success, HttpStatus.OK);
                }else{
                    error.setMsg("Faild Process...");
                    error.setStatus(422);
                    return new ResponseEntity<>(error, HttpStatus.UNPROCESSABLE_ENTITY);
                }
            }else{
                return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
            }
        }catch (Exception ex){
            System.out.println(ex);
        }
        return null;
    }
}
